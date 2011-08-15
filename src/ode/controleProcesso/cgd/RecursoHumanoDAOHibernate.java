package ode.controleProcesso.cgd;

import org.springframework.stereotype.Repository;

import ode.controleProcesso.cdp.RecursoHumano;
import ode.nucleo.crud.cgd.DAOBaseHibernate;

@Repository
public class RecursoHumanoDAOHibernate extends
		DAOBaseHibernate<RecursoHumano> implements RecursoHumanoDAO {

}
