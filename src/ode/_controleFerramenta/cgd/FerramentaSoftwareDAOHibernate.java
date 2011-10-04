package ode._controleFerramenta.cgd;

import org.springframework.stereotype.Repository;

import ode._controleFerramenta.cdp.FerramentaSoftware;
import ode.nucleo.crud.cgd.DAOBaseHibernate;

@Repository
public class FerramentaSoftwareDAOHibernate extends
		DAOBaseHibernate<FerramentaSoftware> implements FerramentaSoftwareDAO {

}
