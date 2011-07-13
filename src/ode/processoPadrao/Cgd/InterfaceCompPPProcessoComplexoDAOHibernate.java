package ode.processoPadrao.Cgd;
import ode.nucleo.cgd.NucleoDAOBaseHibernate;
import ode.processoPadrao.Cdp.InterfaceCompPPProcessoComplexo;

import org.springframework.stereotype.Repository;

@Repository
public abstract class InterfaceCompPPProcessoComplexoDAOHibernate extends NucleoDAOBaseHibernate<InterfaceCompPPProcessoComplexo> implements InterfaceCompPPProcessoComplexoDAO {
	    
/*
	    public List obterTodos(){
	        return super.obterTodos(InterfaceCompPPProcessoComplexo.class);
	    }

	    public InterfaceCompPPProcessoComplexo obterPorId(InterfaceCompPP parInterfaceCompPP){
	        List locInterface = getSession().createQuery("from InterfaceCompPPProcessoComplexo as inter where inter.id = '" + parInterfaceCompPP.getId()+ "'").list();

	        if (locInterface.isEmpty())
	            return null;

	        return (InterfaceCompPPProcessoComplexo)locInterface.get(0);
	    }*/
}
