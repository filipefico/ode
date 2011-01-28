package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import ode.conhecimento.processo.Cdp.KRecurso;

	public class KRecursoDAOHibernate extends NucleoDAOBaseHibernate<KRecurso> implements KRecursoDAO {
		@Override
		protected Class<KRecurso> getClasseDominio() {
			// TODO Auto-generated method stub
			return KRecurso.class;
	}
		public void salvar(KRecurso parKRecurso){
	        super.salvar(parKRecurso);
	    }
	    
	    public void excluir(KRecurso parKRecurso){
	        super.excluir(parKRecurso);
	    }
	    
	    public Collection<KRecurso> recuperarTodos(){
	        return super.recuperarTodos();
	    }
}
