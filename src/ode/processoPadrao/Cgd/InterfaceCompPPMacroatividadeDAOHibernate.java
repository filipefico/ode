package ode.processoPadrao.Cgd;

import java.util.Collection;

import ode.processoPadrao.Cdp.InterfaceCompPPMacroatividade;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class InterfaceCompPPMacroatividadeDAOHibernate extends NucleoDAOBaseHibernate<InterfaceCompPPMacroatividade> implements InterfaceCompPPMacroatividadeDAO{

	@Override
	protected Class<InterfaceCompPPMacroatividade> getClasseDominio() {
		// TODO Auto-generated method stub
		return InterfaceCompPPMacroatividade.class;
	}

	  public void salvar(InterfaceCompPPMacroatividade parInterfaceCompPPMacroatividade){
	        super.salvar(parInterfaceCompPPMacroatividade);
	    }

	    public void excluir(InterfaceCompPPMacroatividade parInterfaceCompPPMacroatividade){
	        super.excluir(parInterfaceCompPPMacroatividade);
	    }	        
	    
    public Collection<InterfaceCompPPMacroatividade> recuperarTodos(){
        return super.recuperarTodos();
    }
	
}
