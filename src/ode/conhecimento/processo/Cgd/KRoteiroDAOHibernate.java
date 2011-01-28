package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KRoteiro;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class KRoteiroDAOHibernate extends NucleoDAOBaseHibernate<KRoteiro> implements KRoteiroDAO{

		@Override
		protected Class<KRoteiro> getClasseDominio() {
			// TODO Auto-generated method stub
			return KRoteiro.class;
		}
	
	 	public void salvar(KRoteiro parKRoteiro){
	        super.salvar(parKRoteiro);
	    }
	    
	    public void excluir(KRoteiro parKRoteiro){
	        super.excluir(parKRoteiro);
	    }
	    
	    public Collection<KRoteiro> recuperarTodos(){
	        return super.recuperarTodos();
	    }

}
