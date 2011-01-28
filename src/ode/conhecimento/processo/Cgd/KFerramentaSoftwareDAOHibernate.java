package ode.conhecimento.processo.Cgd;

import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import ode.conhecimento.processo.Cdp.KFerramentaSoftware;

public class KFerramentaSoftwareDAOHibernate extends NucleoDAOBaseHibernate<KFerramentaSoftware> implements
			KFerramentaSoftwareDAO {

		@Override
		protected Class<KFerramentaSoftware> getClasseDominio() {
			// TODO Auto-generated method stub
			return KFerramentaSoftware.class;
		}
		
}
