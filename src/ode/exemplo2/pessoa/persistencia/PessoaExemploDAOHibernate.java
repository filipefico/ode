package ode.exemplo2.pessoa.persistencia;

import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import ode.exemplo2.pessoa.dominio.PessoaExemplo;

public class PessoaExemploDAOHibernate extends NucleoDAOBaseHibernate<PessoaExemplo> implements
		PessoaExemploDAO {

	@Override
	protected Class getClasseDominio() {
		// TODO Auto-generated method stub
		return PessoaExemplo.class;
	}
	
	
	

}
