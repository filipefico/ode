package ode.conhecimento.processo.Cgd;

import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import ode.conhecimento.processo.Cdp.KTipoInteracao;

public class KTipoInteracaoDAOHibernate extends NucleoDAOBaseHibernate<KTipoInteracao> 
	implements KTipoInteracaoDAO{

	@Override
	protected Class getClasseDominio() {
		// TODO Auto-generated method stub
		return KTipoInteracao.class;
	}
	
}
