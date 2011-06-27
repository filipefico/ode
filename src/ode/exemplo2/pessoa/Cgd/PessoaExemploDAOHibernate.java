package ode.exemplo2.pessoa.Cgd;

import org.springframework.stereotype.Repository;

import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import ode.exemplo2.pessoa.Cdp.PessoaExemplo;

@Repository(value = "pessoaExemploDAO")
public class PessoaExemploDAOHibernate extends
		NucleoDAOBaseHibernate<PessoaExemplo> implements PessoaExemploDAO {

}
