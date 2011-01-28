package ode.processoPadrao.Cgd;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import ode.processoPadrao.Cdp.InterfaceCompPP;
import ode.processoPadrao.Cdp.InterfaceCompPPProcessoSimples;

import java.util.Collection;
import java.util.List;

public abstract class InterfaceCompPPProcessoSimplesDAOHibernate extends NucleoDAOBaseHibernate<InterfaceCompPPProcessoSimples> implements InterfaceCompPPProcessoSimplesDAO{
	
	@Override
	protected Class<InterfaceCompPPProcessoSimples> getClasseDominio() {
		// TODO Auto-generated method stub
		return InterfaceCompPPProcessoSimples.class;
	}
	
	public void salvar(InterfaceCompPPProcessoSimples parInterfaceCompPPProcessoSimples){
			  super.salvar(parInterfaceCompPPProcessoSimples);
		  }

	 public void excluir(InterfaceCompPPProcessoSimples parInterfaceCompPPProcessoSimples){
			  super.excluir(parInterfaceCompPPProcessoSimples);
		  }
	 
	 public Collection<InterfaceCompPPProcessoSimples> recuperarTodos(){
	        return super.recuperarTodos();
	    }
		  
}
/*
public List obterTodos(){
    return super.obterTodos(InterfaceCompPPProcessoSimples.class);
}

public InterfaceCompPPProcessoSimples obterPorId(InterfaceCompPP parInterfaceCompPP){
    List locInterface = getSession().createQuery("from InterfaceCompPPProcessoSimples as inter where inter.id = '" + parInterfaceCompPP.getId()+ "'").list();

    if (locInterface.isEmpty())
        return null;

    return (InterfaceCompPPProcessoSimples)locInterface.get(0);
}*/

