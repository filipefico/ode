package ode.processoPadrao.Cgd;

import java.util.Collection;

import ode.processoPadrao.Cdp.CombinacaoPP;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class CombinacaoPPDAOHibernate extends NucleoDAOBaseHibernate<CombinacaoPP> implements CombinacaoPPDAO {

	@Override
	protected Class<CombinacaoPP> getClasseDominio() {
		// TODO Auto-generated method stub
		return CombinacaoPP.class;
	}
	
	 public void salvar(CombinacaoPP parCombinacao){
	        super.salvar(parCombinacao);
	    }
	    
	    public void excluir(CombinacaoPP parCombinacao){
	        super.excluir(parCombinacao);
	    }
	    	
	public Collection<CombinacaoPP> recuperarTodos(){
        return super.recuperarTodos();
    }
}
