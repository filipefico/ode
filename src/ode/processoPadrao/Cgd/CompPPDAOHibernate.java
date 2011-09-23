package ode.processoPadrao.Cgd;
import java.util.List;

import org.springframework.stereotype.Repository;

import ode.nucleo.crud.cgd.DAOBaseHibernate;
import ode.processoPadrao.Cdp.CompPP;
import ode.processoPadrao.Cdp.CompPPProcessoComplexo;

@Repository
public class CompPPDAOHibernate extends DAOBaseHibernate<CompPP> implements CompPPDAO{

	    public CompPP obterPorNome(String parNome){
	        
	        List locProc = getEntityManager().createQuery("from "+ CompPPProcessoComplexo.class.getName() + " as proc where proc.nome = '" + parNome + "'").getResultList();
	        
	        if (locProc.isEmpty())
	            return null;
	        return (CompPP)locProc.get(0);
	    }
}
