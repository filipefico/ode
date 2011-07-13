package ode.conhecimento.principal.Cgd;

import java.util.Collection;

import ode.conhecimento.principal.Cdp.Conhecimento;
import ode.nucleo.cgd.NucleoDAOBase;

public interface ConhecimentoDAO extends NucleoDAOBase<Conhecimento>{

	 	public void salvar(Conhecimento par);
	    
	    public void excluir(Conhecimento par);
	    
	    public Collection<Conhecimento> recuperarTodos();
	
}
