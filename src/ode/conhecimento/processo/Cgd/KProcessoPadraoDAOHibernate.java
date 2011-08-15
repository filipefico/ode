package ode.conhecimento.processo.Cgd;

import org.springframework.stereotype.Repository;

import ode.conhecimento.processo.Cdp.KProcessoPadrao;
import ode.nucleo.crud.cgd.DAOBaseHibernate;

@Repository
public class KProcessoPadraoDAOHibernate extends DAOBaseHibernate<KProcessoPadrao> implements KProcessoPadraoDAO{

}
