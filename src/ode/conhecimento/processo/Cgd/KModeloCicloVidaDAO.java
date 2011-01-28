package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KModeloCicloVida;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface KModeloCicloVidaDAO extends NucleoDAOBase<KModeloCicloVida>{

	 	public void salvar(KModeloCicloVida parKMCV);
	    
	    public void excluir(KModeloCicloVida parKMCV);
	    
	    public Collection<KModeloCicloVida> recuperarTodos();
}
