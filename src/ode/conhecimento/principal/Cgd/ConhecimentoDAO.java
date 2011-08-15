package ode.conhecimento.principal.Cgd;

import java.util.Collection;

import ode.conhecimento.principal.Cdp.Conhecimento;
import ode.nucleo.crud.cgd.DAOBase;

public interface ConhecimentoDAO extends DAOBase<Conhecimento>{

	 	public void salvar(Conhecimento par);
	    
	    public void excluir(Conhecimento par);
	    
	    public Collection<Conhecimento> recuperarTodos();
	
}
