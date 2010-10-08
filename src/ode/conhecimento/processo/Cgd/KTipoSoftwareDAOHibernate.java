package ode.conhecimento.processo.Cgd;

import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import ode.conhecimento.processo.Cdp.KTipoSoftware;

public class KTipoSoftwareDAOHibernate extends
		NucleoDAOBaseHibernate<KTipoSoftware> implements KTipoSoftwareDAO {

	@Override
	protected Class<KTipoSoftware> getClasseDominio() {
		// TODO Auto-generated method stub
		return KTipoSoftware.class;
	}

}
