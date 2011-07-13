package ode.controleProcesso.cgd;

import org.springframework.stereotype.Repository;

import ode.controleProcesso.cdp.RecursoHumano;
import ode.nucleo.cgd.NucleoDAOBaseHibernate;

@Repository
public class RecursoHumanoDAOHibernate extends
		NucleoDAOBaseHibernate<RecursoHumano> implements RecursoHumanoDAO {

}
