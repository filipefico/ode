package ode.conhecimento.processo.Cgd;

import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import ode.conhecimento.processo.Cdp.KCategoriaProcesso;
import ode.conhecimento.processo.Cgd.KCategoriaProcessoDAO;

public class KCategoriaProcessoDAOHibernate extends NucleoDAOBaseHibernate<KCategoriaProcesso> implements
KCategoriaProcessoDAO{

	@Override
	protected Class getClasseDominio() {
		// TODO Auto-generated method stub
		return KCategoriaProcesso.class;
	}
	
}
