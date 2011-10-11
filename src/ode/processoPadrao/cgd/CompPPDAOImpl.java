package ode.processoPadrao.cgd;
import java.util.List;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;

import org.springframework.stereotype.Repository;

@Repository
public class CompPPDAOImpl extends DAOBaseImpl<CompPP> implements CompPPDAO{

	    public CompPP obterPorNome(String parNome){
	        
	        List locProc = getEntityManager().createQuery("from "+ CompPPProcessoComplexo.class.getName() + " as proc where proc.nome = '" + parNome + "'").getResultList();
	        
	        if (locProc.isEmpty())
	            return null;
	        return (CompPP)locProc.get(0);
	    }
}
