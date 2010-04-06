package ode.exemplo.persistencia;

import ode.exemplo.dominio.PessoaExemplo;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class PessoaExemploDAOHibernate extends NucleoDAOBaseHibernate<PessoaExemplo> implements
		PessoaExemploDAO {

	@Override
	protected Class getClasseDominio() {
		// TODO Auto-generated method stub
		return PessoaExemplo.class;
	}

}
