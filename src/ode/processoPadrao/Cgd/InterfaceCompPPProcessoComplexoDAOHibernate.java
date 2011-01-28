package ode.processoPadrao.Cgd;
import java.util.Collection;

import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import ode.processoPadrao.Cdp.InterfaceCompPP;
import ode.processoPadrao.Cdp.InterfaceCompPPProcessoSimples;
import ode.processoPadrao.Cdp.InterfaceCompPPProcessoComplexo;

public abstract class InterfaceCompPPProcessoComplexoDAOHibernate extends NucleoDAOBaseHibernate<InterfaceCompPPProcessoComplexo> implements InterfaceCompPPProcessoComplexoDAO {

		@Override
		protected Class<InterfaceCompPPProcessoComplexo> getClasseDominio() {
		// TODO Auto-generated method stub
		return InterfaceCompPPProcessoComplexo.class;
		}
		public void salvar(InterfaceCompPPProcessoComplexo parInterfaceCompPPProcessoComplexo){
	        super.salvar(parInterfaceCompPPProcessoComplexo);
	    }

	    public void excluir(InterfaceCompPPProcessoComplexo parInterfaceCompPPProcessoComplexo){
	        super.excluir(parInterfaceCompPPProcessoComplexo);
	    }
	    
	    public Collection<InterfaceCompPPProcessoComplexo> recuperarTodos(){
	        return super.recuperarTodos();
	    }
	    
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
