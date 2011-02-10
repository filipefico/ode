package ode.conhecimento.processo.Cgd;

import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import ode.conhecimento.processo.Cdp.KResultadoProcesso;
import ode.conhecimento.processo.Cgd.KResultadoProcessoDAO;

public class KResultadoProcessoDAOHibernate extends NucleoDAOBaseHibernate<KResultadoProcesso> implements KResultadoProcessoDAO {
	
	@Override
	protected Class getClasseDominio() {
		// TODO Auto-generated method stub
		return KResultadoProcesso.class;
	}
}
