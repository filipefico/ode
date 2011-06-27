package ode.processoPadrao.Cgd;

import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import ode.processoPadrao.Cdp.ModeloCicloVidaProcessoPadrao;

import org.springframework.stereotype.Repository;

@Repository
public class ModeloCicloVidaProcessoPadraoDAOHibernate extends NucleoDAOBaseHibernate<ModeloCicloVidaProcessoPadrao> implements ModeloCicloVidaProcessoPadraoDAO{

}