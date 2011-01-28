package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KTecnica;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class KTecnicaDAOHibernate extends NucleoDAOBaseHibernate<KTecnica> implements KTecnicaDAO{
	
		@Override
		protected Class<KTecnica> getClasseDominio() {
			// TODO Auto-generated method stub
			return KTecnica.class;
		}

	
	 	public void salvar(KTecnica parKTecnica){
	        super.salvar(parKTecnica);
	    }
	    
	    public void excluir(KTecnica parKTecnica){
	        super.excluir(parKTecnica);
	    }
	    
	    public Collection<KTecnica> recuperarTodos(){
	        return super.recuperarTodos();
	    }
	    
}
