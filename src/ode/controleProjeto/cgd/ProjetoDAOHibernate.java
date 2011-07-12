package ode.controleProjeto.cgd;

import java.util.Collection;

import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

import ode.controleProjeto.cdp.Projeto;

import org.springframework.stereotype.Repository;

@Repository
public class ProjetoDAOHibernate extends NucleoDAOBaseHibernate<Projeto> implements
		ProjetoDAO {
	
	@SuppressWarnings("unchecked")
	public Projeto recuperarPorNome(String nome) {
		Collection<Projeto> Organizacao = getHibernateTemplate()
				.findByNamedParam(
						"from Projeto projeto where projeto.nome = :nome ",
						new String[] { "nome" },
						new Object[] { nome });

		if (Organizacao.size() > 0) {
			return Organizacao.iterator().next();
		} else {
			return null;
		}
	}

}