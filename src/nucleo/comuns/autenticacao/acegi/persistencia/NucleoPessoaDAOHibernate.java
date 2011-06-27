package nucleo.comuns.autenticacao.acegi.persistencia;

import java.util.Collection;

import nucleo.comuns.autenticacao.acegi.dominio.NucleoPessoa;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

import org.springframework.stereotype.Repository;

@Repository
public class NucleoPessoaDAOHibernate extends NucleoDAOBaseHibernate<NucleoPessoa> implements
		NucleoPessoaDAO {

	@SuppressWarnings("unchecked")
	public NucleoPessoa recuperarPorEmail(String email) {
		Collection<NucleoPessoa> pessoas = getHibernateTemplate()
				.findByNamedParam(
						"from NucleoPessoa pessoa where pessoa.email = :email ",
						new String[] { "email" },
						new Object[] { email });

		if (pessoas.size() > 0) {
			return pessoas.iterator().next();
		} else {
			return null;
		}
	}

}
