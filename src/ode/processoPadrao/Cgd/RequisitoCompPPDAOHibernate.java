package ode.processoPadrao.Cgd;

import ode.processoPadrao.Cdp.RequisitoCompPP;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class RequisitoCompPPDAOHibernate extends NucleoDAOBaseHibernate<RequisitoCompPP> implements RequisitoCompPPDAO{

	@Override
	protected Class<RequisitoCompPP> getClasseDominio() {
		// TODO Auto-generated method stub
		return RequisitoCompPP.class;
	}
	
	   public void salvar(RequisitoCompPP requisitoCompPP){
	        super.salvar(requisitoCompPP);
	    }

	    public void excluir(RequisitoCompPP requisitoCompPP){
	        super.excluir(requisitoCompPP);
	    }

	    /*public List obterTodos(){
	        return super.obterTodos(RequisitoCompPP.class);
	    }*/	
}
