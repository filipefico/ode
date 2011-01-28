package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KDiretriz;

import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class KDiretrizDAOHibernate extends NucleoDAOBaseHibernate<KDiretriz> implements KDiretrizDAO{

	 	
		@Override
		protected Class<KDiretriz> getClasseDominio() {
			// TODO Auto-generated method stub
			return KDiretriz.class;
		}

			public void salvar(KDiretriz parKDiretriz){
	        super.salvar(parKDiretriz);
	    }
	    
	    public void excluir(KDiretriz parKDiretriz){
	        super.excluir(parKDiretriz);
	    }
	    
	    public Collection<KDiretriz> recuperarTodos(){
	        return super.recuperarTodos();
	    }

}
