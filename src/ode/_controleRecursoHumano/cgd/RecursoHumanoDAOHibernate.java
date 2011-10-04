package ode._controleRecursoHumano.cgd;

import org.springframework.stereotype.Repository;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode.nucleo.crud.cgd.DAOBaseHibernate;

@Repository
public class RecursoHumanoDAOHibernate extends
		DAOBaseHibernate<RecursoHumano> implements RecursoHumanoDAO {

}
