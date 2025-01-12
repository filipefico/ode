package ode._controleRecursoHumano.cgt;

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
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.controleProjeto.cdp.Projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplDefinirEquipe {
	
	@Autowired
	private EquipeDAO equipeDAO;
	
	@Autowired
	private ParticipacaoEquipeDAO participacaoEquipeDAO;
	
	@Autowired
	private RecursoHumanoDAO recursoHumanoDAO;
		
	public void definirEquipe(Set<RecursoHumano> recursosSelecionados, Projeto projeto) {
		Equipe equipe = equipeDAO.obterEquipePorProjeto(projeto.getId());
		
		List<ParticipacaoEquipe> lista = participacaoEquipeDAO.obterPorEquipeAtual(equipe.getId());

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
					pe.setDtInicio(new Date());
					participacaoEquipeDAO.salvar(pe);
				}
			}
			else {
				if(pe!=null) {
					pe.setDtFim(new Date());
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
