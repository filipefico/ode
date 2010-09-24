package ode.processoPadrao.Cgd;
import ode.processoPadrao.Cdp.*;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import java.util.*;

public class CompPPDAOHibernate extends NucleoDAOBaseHibernate<CompPP> implements CompPPDAO{

	
	@Override
	protected Class<CompPP> getClasseDominio() {
		// TODO Auto-generated method stub
		return CompPP.class;
	}
		
	  public void salvar(CompPP parProcessoPadrao){
	        super.salvar(parProcessoPadrao);
	    }
	    
	    public void excluir(CompPP parProcessoPadrao){
	        super.excluir(parProcessoPadrao);
	    }
	    
	    /*public List obterTodos(){
	        return super.obterTodos(CompPP.class);
	    }*/
	
}
