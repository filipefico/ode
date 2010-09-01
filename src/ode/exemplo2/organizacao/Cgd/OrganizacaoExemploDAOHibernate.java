package ode.exemplo2.organizacao.Cgd;

import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import ode.exemplo2.organizacao.Cdp.OrganizacaoExemplo;

public class OrganizacaoExemploDAOHibernate extends NucleoDAOBaseHibernate<OrganizacaoExemplo> implements
		OrganizacaoExemploDAO {

	@Override
	protected Class getClasseDominio() {
		// TODO Auto-generated method stub
		return OrganizacaoExemplo.class;
	}
	
	
	

}
