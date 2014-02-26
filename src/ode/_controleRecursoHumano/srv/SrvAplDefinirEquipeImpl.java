package ode._controleRecursoHumano.srv;

import java.util.Collection;
import java.util.Set;

import ode._controleRecursoHumano.cdp.Equipe;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgd.EquipeDAO;
import ode._controleRecursoHumano.cgt.AplDefinirEquipe;
import ode.controleProjeto.cdp.Projeto;

import org.springframework.beans.factory.annotation.Autowired;

public class SrvAplDefinirEquipeImpl implements SrvAplDefinirEquipe {
	
	@Autowired
	private AplDefinirEquipe apl;
	
	@Autowired
	private EquipeDAO dao;
	
	
	public Collection<Equipe> recuperarTodos(){
		return dao.recuperarTodos();
	}
	
	public void definirEquipe(Set<RecursoHumano> recursosSelecionados, Projeto projeto){
		apl.definirEquipe(recursosSelecionados, projeto);
	}
	
	public void atualizar(Equipe objeto){
		dao.atualizar(objeto);
	}
	
	public Equipe obterEquipePorProjeto(long id){
		return dao.obterEquipePorProjeto(id);
	}

}
