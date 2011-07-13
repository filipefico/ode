package ode.processoPadrao.Cgd;

import ode.nucleo.cgd.NucleoDAOBaseHibernate;
import ode.processoPadrao.Cdp.ModeloCicloVidaProcessoPadrao;

import org.springframework.stereotype.Repository;

@Repository
public class ModeloCicloVidaProcessoPadraoDAOHibernate extends NucleoDAOBaseHibernate<ModeloCicloVidaProcessoPadrao> implements ModeloCicloVidaProcessoPadraoDAO{

}