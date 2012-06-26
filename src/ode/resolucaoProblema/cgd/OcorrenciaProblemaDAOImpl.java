package ode.resolucaoProblema.cgd;

import java.util.List;

import org.springframework.stereotype.Repository;
import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.resolucaoProblema.cdp.OcorrenciaProblema;

@Repository
public class OcorrenciaProblemaDAOImpl extends DAOBaseImpl<OcorrenciaProblema> implements OcorrenciaProblemaDAO {
	
	@SuppressWarnings("unchecked")
	public List<OcorrenciaProblema> recuperarTodosOrdenadoImpacto (){
		String query = "select o from OcorrenciaProblema o order by o.nivelimpactopresente, o.nivelimpactofuturo";
		return getEntityManager().createQuery(query).getResultList();
	}
	

}
