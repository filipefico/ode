package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KRecursoHumano;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class KRecursoHumanoDAOHibernate extends NucleoDAOBaseHibernate<KRecursoHumano> implements KRecursoHumanoDAO {
	
		@Override
		protected Class<KRecursoHumano> getClasseDominio() {
			// TODO Auto-generated method stub
			return KRecursoHumano.class;
		}
	  	public void salvar(KRecursoHumano parKRecursoHumano){
	        super.salvar(parKRecursoHumano);
	    }
	    
	    public void excluir(KRecursoHumano parKRecursoHumano){
	        super.excluir(parKRecursoHumano);
	    }
	    
	    /** obter lista com todos Recursos Humanos*/
	    public Collection<KRecursoHumano> recuperarTodos(){
	        return super.recuperarTodos();
	    }
}
