package ode.processoPadrao.Cgd;

import java.util.Collection;

import ode.processoPadrao.Cdp.OrdemAtividadePP;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class OrdemAtividadePPDAOHibernate extends NucleoDAOBaseHibernate<OrdemAtividadePP> implements OrdemAtividadePPDAO{

	@Override
	protected Class<OrdemAtividadePP> getClasseDominio() {
		// TODO Auto-generated method stub
		return OrdemAtividadePP.class;
	}

	  public void salvar(OrdemAtividadePP parOrdemAtividade){
	        super.salvar(parOrdemAtividade);
	    }
	    
	    public void excluir(OrdemAtividadePP parOrdemAtividade){
	        super.excluir(parOrdemAtividade);
	    }
	    	    
    public Collection<OrdemAtividadePP> recuperarTodos(){
        return super.recuperarTodos();
    }
	
}
