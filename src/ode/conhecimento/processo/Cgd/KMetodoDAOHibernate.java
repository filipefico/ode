package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KMetodo;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class KMetodoDAOHibernate extends NucleoDAOBaseHibernate<KMetodo> implements KMetodoDAO{
	

		@Override
		protected Class<KMetodo> getClasseDominio() {
			// TODO Auto-generated method stub
			return KMetodo.class;
		}
	
		public void salvar(KMetodo parKMetodo){
	       super.salvar(parKMetodo);
	    }
	    
	    public void excluir(KMetodo parKMetodo){
	        super.excluir(parKMetodo);
	    }
	    
	    public Collection<KMetodo> recuperarTodos(){
	        return super.recuperarTodos();
	    }
}
