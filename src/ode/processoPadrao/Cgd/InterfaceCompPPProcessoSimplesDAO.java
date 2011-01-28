package ode.processoPadrao.Cgd;
import java.util.Collection;

import nucleo.comuns.persistencia.NucleoDAOBase;

import ode.processoPadrao.Cdp.InterfaceCompPP;
import ode.processoPadrao.Cdp.InterfaceCompPPProcessoSimples;

public interface InterfaceCompPPProcessoSimplesDAO extends NucleoDAOBase< InterfaceCompPPProcessoSimples>{
	
		public void salvar(InterfaceCompPPProcessoSimples parInterfaceCompPPProcessoSimples);

	    public void excluir(InterfaceCompPPProcessoSimples parInterfaceCompPPProcessoSimples);
	    
	    public Collection<InterfaceCompPPProcessoSimples> recuperarTodos();

	   // public InterfaceCompPPProcessoSimples obterPorId(InterfaceCompPP parInterfaceCompPP);
}
