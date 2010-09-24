package ode.processoPadrao.Cgd;

import ode.processoPadrao.Cdp.InterfaceCompPP;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import java.util.*;

public class InterfaceCompPPDAOHibernate extends NucleoDAOBaseHibernate<InterfaceCompPP> {
	
	@Override
	protected Class<InterfaceCompPP> getClasseDominio() {
		// TODO Auto-generated method stub
		return InterfaceCompPP.class;
	}
	
	public void salvar(InterfaceCompPP parInterfaceCompPP){
        super.salvar(parInterfaceCompPP);
    }

    public void excluir(InterfaceCompPP parInterfaceCompPP){
        super.excluir(parInterfaceCompPP);
    }

    /*public List obterTodos(){
        return super.obterTodos(InterfaceCompPP.class);
    }*/
	
}
