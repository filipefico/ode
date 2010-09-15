package ode.conhecimento.organizacao.Cgd;

import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import ode.conhecimento.organizacao.Cdp.KDominioConhecimento;

public class KDominioConhecimentoDAOHibernate extends NucleoDAOBaseHibernate<KDominioConhecimento> implements
			KDominioConhecimentoDAO {

		@Override
		protected Class getClasseDominio() {
			// TODO Auto-generated method stub
			return KDominioConhecimento.class;
		}
		
}
