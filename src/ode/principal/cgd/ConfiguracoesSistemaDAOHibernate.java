package ode.principal.cgd;

import ode.nucleo.cgd.NucleoDAOBaseHibernate;
import ode.principal.cdp.ConfiguracoesSistema;

import org.springframework.stereotype.Repository;

@Repository
public class ConfiguracoesSistemaDAOHibernate extends NucleoDAOBaseHibernate<ConfiguracoesSistema>
		implements ConfiguracoesSistemaDAO {
	
}
