package ode.conhecimento.processo.Cgd;

import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import ode.conhecimento.processo.Cdp.KParadigma;

public class KParadigmaDAOHibernate extends NucleoDAOBaseHibernate<KParadigma> implements
		KParadigmaDAO {

	@Override
	protected Class<KParadigma> getClasseDominio() {
		// TODO Auto-generated method stub
		return KParadigma.class;
	}	

}
