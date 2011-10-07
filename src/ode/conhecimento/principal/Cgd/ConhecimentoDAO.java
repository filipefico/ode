package ode.conhecimento.principal.cgd;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.conhecimento.principal.cdp.Conhecimento;

public interface ConhecimentoDAO extends DAOBase<Conhecimento>{

	 	public void salvar(Conhecimento par);
	    
	    public void excluir(Conhecimento par);
	    
	    public Collection<Conhecimento> recuperarTodos();
	
}
