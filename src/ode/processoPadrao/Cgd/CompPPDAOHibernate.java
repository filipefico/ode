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
	    
	 /*   public Collection<CompPP> recuperarTodos(){
	        return super.recuperarTodos();
	    }*/
	
	    public CompPP obterPorNome(String parNome){
	        
	        List locProc = getSession().createQuery("from "+ CompPPProcessoComplexo.nomeClass + " as proc where proc.nome = '" + parNome + "'").list();
	        if (locProc.isEmpty())
	            return null;
	        return (CompPP)locProc.get(0);
	    }
}
