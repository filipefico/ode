package ode._controleRecursoHumano.cgt;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ode._controleRecursoHumano.cdp.Equipe;
import ode._controleRecursoHumano.cdp.ParticipacaoEquipe;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgd.EquipeDAO;
import ode._controleRecursoHumano.cgd.ParticipacaoEquipeDAO;
import ode._controleRecursoHumano.cgd.RecursoHumanoDAO;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.controleProjeto.cdp.Projeto;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplDefinirEquipe {
	
	@Autowired
	private EquipeDAO equipeDAO;
	
	@Autowired
	private ParticipacaoEquipeDAO participacaoEquipeDAO;
	
	@Autowired
	private RecursoHumanoDAO recursoHumanoDAO;
		
	public void definirEquipe(Set<RecursoHumano> recursos, KRecursoHumano krh, Projeto projeto) {
		Equipe equipe = equipeDAO.obterEquipePorProjeto(projeto.getId());
		
		List<ParticipacaoEquipe> lista = participacaoEquipeDAO.obterPorEquipe(equipe.getId());

		for (RecursoHumano rh : recursoHumanoDAO.recuperarTodos()) {
			ParticipacaoEquipe pe = obterParticipacaoEquipePersistida(rh, lista);
			//se o recurso foi selecionado
			if(recursos.contains(rh)) {
				//se o recurso ainda não está alocado à equipe, aloca
				if(pe==null) {
					pe = new ParticipacaoEquipe();
					pe.setRecursoHumano(rh);
					pe.setEquipe(equipe);
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
					//remove o cargo da lista 
					pe.getPapeis().remove(krh);
					//era o unico papel ao qual estava associado naquela equipe? 
					if(pe.getPapeis().size()==0) {
						//exclui a participação
						participacaoEquipeDAO.excluir(pe);
					}
				}
			}
		}
	}
	
	public void definirEquipe(Set<RecursoHumano> recursosSelecionados, Projeto projeto) {
		Equipe equipe = equipeDAO.obterEquipePorProjeto(projeto.getId());
		
		List<ParticipacaoEquipe> lista = participacaoEquipeDAO.obterPorEquipe(equipe.getId());

		for (RecursoHumano rh : recursoHumanoDAO.recuperarTodos()) {
			ParticipacaoEquipe pe = obterParticipacaoEquipePersistida(rh, lista); 
			
			if(recursosSelecionados.contains(rh)) {
				if(pe==null) {
					pe = new ParticipacaoEquipe();
					pe.setRecursoHumano(rh);
					Set<KRecursoHumano> papeis = new HashSet<KRecursoHumano>();
					papeis.add(rh.getCargo());
					pe.setPapeis(papeis);
					pe.setEquipe(equipe);
					participacaoEquipeDAO.salvar(pe);
				}
			}
			else {
				if(pe!=null) {
					participacaoEquipeDAO.excluir(pe);
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
