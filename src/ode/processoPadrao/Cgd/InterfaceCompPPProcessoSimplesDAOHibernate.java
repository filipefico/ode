package ode.processoPadrao.Cgd;
import ode.nucleo.crud.cgd.DAOBaseHibernate;
import ode.processoPadrao.Cdp.InterfaceCompPPProcessoSimples;

import org.springframework.stereotype.Repository;

@Repository
public abstract class InterfaceCompPPProcessoSimplesDAOHibernate extends DAOBaseHibernate<InterfaceCompPPProcessoSimples> implements InterfaceCompPPProcessoSimplesDAO{
			  
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

