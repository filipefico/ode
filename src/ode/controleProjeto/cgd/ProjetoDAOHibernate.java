package ode.controleProjeto.cgd;

import java.util.Collection;

import ode.controleProjeto.cdp.Projeto;
import ode.nucleo.crud.cgd.DAOBaseHibernate;

import org.springframework.stereotype.Repository;

@Repository
public class ProjetoDAOHibernate extends DAOBaseHibernate<Projeto> implements
		ProjetoDAO {
	
	@SuppressWarnings("unchecked")
	public Projeto recuperarPorNome(String nome) {
		
		Collection<Projeto> projetos = getEntityManager().createQuery("from Projeto where nome = :nome ").setParameter("nome", nome).getResultList();

		if (projetos.size() > 0) {
			return projetos.iterator().next();
		} else {
			return null;
		}
		
	}

}
