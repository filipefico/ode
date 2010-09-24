package ode.conhecimento.processo.Cgd;

import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import ode.conhecimento.processo.Cdp.TipoKArtefato;

public class TipoKArtefatoDAOHibernate extends NucleoDAOBaseHibernate<TipoKArtefato> implements TipoKArtefatoDAO{

	@Override
	protected Class<TipoKArtefato> getClasseDominio() {
		return TipoKArtefato.class;
	}

	

}
