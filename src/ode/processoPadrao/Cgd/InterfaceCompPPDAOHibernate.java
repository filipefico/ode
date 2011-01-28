package ode.processoPadrao.Cgd;

import ode.processoPadrao.Cdp.InterfaceCompPP;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import java.util.*;

public class InterfaceCompPPDAOHibernate extends NucleoDAOBaseHibernate<InterfaceCompPP> implements InterfaceCompPPDAO {
	
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

   /* public Collection<InterfaceCompPP> recuperarTodos(){
        return super.recuperarTodos();
    }*/
	
}
