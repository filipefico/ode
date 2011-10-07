package ode.controleProjeto.cgd;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.controleProjeto.cdp.Projeto;

import org.springframework.stereotype.Repository;

@Repository
public class ProjetoDAOImpl extends DAOBaseImpl<Projeto> implements
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
