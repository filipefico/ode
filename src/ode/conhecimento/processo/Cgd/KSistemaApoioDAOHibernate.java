package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

import ode.conhecimento.processo.Cdp.KSistemaApoio;

	public class KSistemaApoioDAOHibernate extends NucleoDAOBaseHibernate<KSistemaApoio> implements KSistemaApoioDAO{
		@Override
		protected Class<KSistemaApoio> getClasseDominio() {
			// TODO Auto-generated method stub
			return KSistemaApoio.class;
		}
	
			public void salvar(KSistemaApoio parKSistemaApoio){
	        super.salvar(parKSistemaApoio);
	    }
	    
	    public void excluir(KSistemaApoio parKSistemaApoio){
	        super.excluir(parKSistemaApoio);
	    }
	    
	    public Collection<KSistemaApoio> recuperarTodos(){
	        return super.recuperarTodos();
	    }

}
