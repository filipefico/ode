package ode.controleUsuario.cgd;

import ode.controleUsuario.cdp.PerfilAcesso;
import ode.nucleo.cgd.NucleoDAOBaseHibernate;

import org.springframework.stereotype.Repository;

@Repository
public class PerfilAcessoDAOHibernate extends
		NucleoDAOBaseHibernate<PerfilAcesso> implements PerfilAcessoDAO {

}
