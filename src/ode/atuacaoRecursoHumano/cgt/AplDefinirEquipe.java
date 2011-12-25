package ode.atuacaoRecursoHumano.cgt;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ode._controleRecursoHumano.cdp.Equipe;
import ode._controleRecursoHumano.cdp.ParticipacaoEquipe;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgd.EquipeDAO;
import ode._controleRecursoHumano.cgd.ParticipacaoEquipeDAO;
import ode._controleRecursoHumano.cgd.RecursoHumanoDAO;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode.alocacaoRecurso.cgd.AlocacaoRHDAO;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.controleProjeto.cdp.Projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AplDefinirEquipeVersaoCompleta")
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplDefinirEquipe {
	
	@Autowired
	private EquipeDAO equipeDAO;
	
	@Autowired
	private ParticipacaoEquipeDAO participacaoEquipeDAO;
	
	@Autowired
	private RecursoHumanoDAO recursoHumanoDAO;

	@Autowired
	private AlocacaoRHDAO alocacaoRHDAO;
		
	public void definirEquipe(Set<RecursoHumano> recursos, KRecursoHumano krh, Projeto projeto) throws NucleoRegraNegocioExcecao {
		
		//não tive tempo, mas recomendo alterar a implementação para utilizar o Cascade do JPA.
		
		Equipe equipe = equipeDAO.obterEquipePorProjeto(projeto.getId());
		
		List<ParticipacaoEquipe> lista = participacaoEquipeDAO.obterPorEquipeAtual(equipe.getId());

		for (RecursoHumano rh : recursoHumanoDAO.recuperarTodos()) {
			ParticipacaoEquipe pe = obterParticipacaoEquipePersistida(rh, lista);
			//se o recurso foi selecionado
			if(recursos.contains(rh)) {
				//se o recurso ainda não está alocado à equipe, aloca
				if(pe==null) {
					pe = new ParticipacaoEquipe();
					pe.setRecursoHumano(rh);
					pe.setEquipe(equipe);
					pe.setDtInicio(new Date());
					Set<KRecursoHumano> papeis = new HashSet<KRecursoHumano>();
					papeis.add(krh);
					pe.setPapeis(papeis);
					participacaoEquipeDAO.salvar(pe);
				}
				//se está, verifica se já está com aquele papel e adiciona caso necessário
				else {
					if(!pe.getPapeis().contains(krh)) {
						pe.getPapeis().add(krh);
						participacaoEquipeDAO.atualizar(pe);
					}
				}				
			}
			//se não foi selecionado
			else {
				//estava na equipe anteriormente para executar o cargo em questao?
				if(pe!=null && pe.getPapeis().contains(krh)) {
					//possui alocação para aquele cargo?
					if (alocacaoRHDAO.recuperarPorRecursoHumanoPapel(pe.getRecursoHumano().getId(), krh.getId(), pe.getEquipe().getProjeto().getId()).size()>0)
						throw new NucleoRegraNegocioExcecao("O recurso humano " + pe.getRecursoHumano().getNome() + " está alocado a uma ou mais alocações em aberto."); 
					
					//remove o cargo da lista 
					pe.getPapeis().remove(krh);
					//era o unico papel ao qual estava associado naquela equipe? 
					if(pe.getPapeis().size()==0) {
						pe.setDtFim(new Date());
					}
					participacaoEquipeDAO.atualizar(pe);
				}
			}
		}
	}
	
	private ParticipacaoEquipe obterParticipacaoEquipePersistida(RecursoHumano rh, Collection<ParticipacaoEquipe> lista) {
		if (lista != null)
			for(ParticipacaoEquipe pe : lista) {
				if (rh.equals(pe.getRecursoHumano())) {
					return pe;
				}
			}
		return null;
	}
	
}
