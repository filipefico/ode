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
	
	public List<RecursoHumano> obterMembrosPorProjeto(Long id) {
		return participacaoEquipeDAO.obterMembrosPorProjeto(id);
	}
	
	public void definirEquipe(Set<RecursoHumano> recursosSelecionados, Projeto projeto) {
		Equipe equipe = equipeDAO.obterEquipePorProjeto(projeto.getId());
		
		List<ParticipacaoEquipe> lista = null;
		
		if(equipe == null) {
			equipe = new Equipe();
			equipe.setProjeto(projeto);
			equipeDAO.salvar(equipe);
		} else {
			lista = participacaoEquipeDAO.obterPorEquipe(equipe.getId());
		}

		for (RecursoHumano rh : recursoHumanoDAO.recuperarTodos()) {
			if(recursosSelecionados.contains(rh)) {
				if(obterParticipacaoEquipePersistida(rh, lista)==null) {
					ParticipacaoEquipe pe = new ParticipacaoEquipe();
					pe.setRecursoHumano(rh);
					Set<KRecursoHumano> papeis = new HashSet<KRecursoHumano>();
					papeis.add(rh.getCargo());
					pe.setPapeis(papeis);
					pe.setEquipe(equipe);
					participacaoEquipeDAO.salvar(pe);
				}
			}
			else {
				ParticipacaoEquipe pe = obterParticipacaoEquipePersistida(rh, lista); 
				if(pe!=null) {
					participacaoEquipeDAO.excluir(pe);
				}
			}
			
		}
		
		if(equipe != null && recursosSelecionados.size()==0)
			equipeDAO.excluir(equipe);
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
