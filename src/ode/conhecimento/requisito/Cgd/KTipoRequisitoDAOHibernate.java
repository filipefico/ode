package ode.conhecimento.requisito.Cgd;

import ode.conhecimento.requisito.Cdp.KTipoRequisito;
import ode.conhecimento.requisito.Cgd.KTipoRequisitoDAO;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class KTipoRequisitoDAOHibernate extends NucleoDAOBaseHibernate<KTipoRequisito> implements KTipoRequisitoDAO{
	
	protected Class getClasseDominio() {
		return KTipoRequisito.class;
	}

}
