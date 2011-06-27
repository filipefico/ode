package nucleo.comuns.autenticacao.acegi.persistencia;

import java.util.Collection;

import nucleo.comuns.autenticacao.acegi.dominio.NucleoOrganizacao;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

import org.springframework.stereotype.Repository;

@Repository
public class NucleoOrganizacaoDAOHibernate extends NucleoDAOBaseHibernate<NucleoOrganizacao> implements
		NucleoOrganizacaoDAO {
	
	@SuppressWarnings("unchecked")
	public NucleoOrganizacao recuperarPorNome(String nome) {
		Collection<NucleoOrganizacao> Organizacao = getHibernateTemplate()
				.findByNamedParam(
						"from NucleoOrganizacao Organizacao where Organizacao.nome = :nome ",
						new String[] { "nome" },
						new Object[] { nome });

		if (Organizacao.size() > 0) {
			return Organizacao.iterator().next();
		} else {
			return null;
		}
	}

}
