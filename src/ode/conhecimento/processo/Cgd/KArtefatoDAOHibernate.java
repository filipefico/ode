package ode.conhecimento.processo.Cgd;

import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import ode.conhecimento.processo.Cdp.KArtefato;

public class KArtefatoDAOHibernate extends NucleoDAOBaseHibernate<KArtefato>
		implements KArtefatoDAO {

	@Override
	protected Class<KArtefato> getClasseDominio() {
		return KArtefato.class;
	}


}
