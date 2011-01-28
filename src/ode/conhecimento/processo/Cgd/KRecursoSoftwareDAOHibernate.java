package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KRecursoSoftware;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class KRecursoSoftwareDAOHibernate extends NucleoDAOBaseHibernate<KRecursoSoftware> implements KRecursoSoftwareDAO {

	
		@Override
		protected Class<KRecursoSoftware> getClasseDominio() {
			// TODO Auto-generated method stub
			return KRecursoSoftware.class;
		}
	
	 	public void salvar(KRecursoSoftware parKRecursoSoftware){
	        super.salvar(parKRecursoSoftware);
	    }
	    
	    public void excluir(KRecursoSoftware parKRecursoSoftware){
	        super.excluir(parKRecursoSoftware);
	    }
	    
	    public Collection<KRecursoSoftware> recuperarTodos(){
	        return super.recuperarTodos();
	    }

}
