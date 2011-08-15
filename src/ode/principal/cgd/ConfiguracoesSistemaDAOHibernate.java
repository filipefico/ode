package ode.principal.cgd;

import ode.nucleo.crud.cgd.DAOBaseHibernate;
import ode.principal.cdp.ConfiguracoesSistema;

import org.springframework.stereotype.Repository;

@Repository
public class ConfiguracoesSistemaDAOHibernate extends DAOBaseHibernate<ConfiguracoesSistema>
		implements ConfiguracoesSistemaDAO {
	
}
