package ode.processoPadrao.Cgd;
import java.util.List;

import org.springframework.stereotype.Repository;

import ode.nucleo.cgd.NucleoDAOBaseHibernate;
import ode.processoPadrao.Cdp.CompPP;
import ode.processoPadrao.Cdp.CompPPProcessoComplexo;

@Repository
public class CompPPDAOHibernate extends NucleoDAOBaseHibernate<CompPP> implements CompPPDAO{

	    public CompPP obterPorNome(String parNome){
	        
	        List locProc = getHibernateTemplate().find("from "+ CompPPProcessoComplexo.nomeClass + " as proc where proc.nome = '" + parNome + "'");
	        if (locProc.isEmpty())
	            return null;
	        return (CompPP)locProc.get(0);
	    }
}
