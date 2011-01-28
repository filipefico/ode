package ode.processoPadrao.Cgd;

import nucleo.comuns.persistencia.NucleoDAOBase;
import ode.processoPadrao.Cdp.CompPP;
import java.util.*;


public interface CompPPDAO extends NucleoDAOBase<CompPP> {

	 	public void salvar(CompPP parProcessoPadrao);
	    
	    public void excluir(CompPP parProcessoPadrao);
	    
	    /** Obtem todos os compPP */
	    public Collection<CompPP> recuperarTodos();
	    
	 //   public CompPP obterPorNome(String parNome);
	
}
