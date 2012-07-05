package ode._controleRecursoHumano.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import ode._controleRecursoHumano.cdp.Equipe;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cgd.DAOBaseImpl;

@Repository
public class RecursoHumanoDAOImpl extends
		DAOBaseImpl<RecursoHumano> implements RecursoHumanoDAO {
	
	public Collection<RecursoHumano> recuperarRecursosHumanosAtivos() {
		return entityManager.createQuery("from RecursoHumano e where e.ativo = true").getResultList();
	}

}
