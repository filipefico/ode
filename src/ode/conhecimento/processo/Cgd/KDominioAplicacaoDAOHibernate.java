package ode.conhecimento.processo.Cgd;

import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import ode.conhecimento.processo.Cdp.KDominioAplicacao;

public class KDominioAplicacaoDAOHibernate extends NucleoDAOBaseHibernate<KDominioAplicacao> implements
		KDominioAplicacaoDAO {

	@Override
	protected Class getClasseDominio() {
		// TODO Auto-generated method stub
		return KDominioAplicacao.class;
	}
	
	
	

}
