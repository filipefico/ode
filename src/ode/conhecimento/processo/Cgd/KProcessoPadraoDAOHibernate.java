package ode.conhecimento.processo.Cgd;

import org.springframework.stereotype.Repository;

import ode.conhecimento.processo.Cdp.KProcessoPadrao;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

@Repository
public class KProcessoPadraoDAOHibernate extends NucleoDAOBaseHibernate<KProcessoPadrao> implements KProcessoPadraoDAO{

}
