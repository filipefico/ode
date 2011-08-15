package ode.controleUsuario.cgd;

import ode.controleUsuario.cdp.PerfilAcesso;
import ode.nucleo.crud.cgd.DAOBaseHibernate;

import org.springframework.stereotype.Repository;

@Repository
public class PerfilAcessoDAOHibernate extends
		DAOBaseHibernate<PerfilAcesso> implements PerfilAcessoDAO {

}
