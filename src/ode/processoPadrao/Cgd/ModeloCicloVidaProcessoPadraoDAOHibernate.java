package ode.processoPadrao.Cgd;

import java.util.Collection;

import ode.processoPadrao.Cdp.ModeloCicloVidaProcessoPadrao;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class ModeloCicloVidaProcessoPadraoDAOHibernate extends NucleoDAOBaseHibernate<ModeloCicloVidaProcessoPadrao> implements ModeloCicloVidaProcessoPadraoDAO{


		@Override
		protected Class<ModeloCicloVidaProcessoPadrao> getClasseDominio() {
			// TODO Auto-generated method stub
			return ModeloCicloVidaProcessoPadrao.class;
		}
	
	 public void salvar(ModeloCicloVidaProcessoPadrao parModeloCicloVidaProcessoPadrao){
	        super.salvar(parModeloCicloVidaProcessoPadrao);
	    }
	    
	    public void excluir(ModeloCicloVidaProcessoPadrao parModeloCicloVidaProcessoPadrao){
	        super.excluir(parModeloCicloVidaProcessoPadrao);
	    }
	    
	    public Collection<ModeloCicloVidaProcessoPadrao> recuperarTodos(){
	        return super.recuperarTodos();
	    }
}