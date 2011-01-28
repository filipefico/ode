package ode.conhecimento.processo.Cgd;

import ode.conhecimento.processo.Cdp.KProcessoPadrao;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class KProcessoPadraoDAOHibernate extends NucleoDAOBaseHibernate<KProcessoPadrao> implements KProcessoPadraoDAO{

	@Override
	protected Class<KProcessoPadrao> getClasseDominio() {
		// TODO Auto-generated method stub
		return KProcessoPadrao.class;
	}
}
