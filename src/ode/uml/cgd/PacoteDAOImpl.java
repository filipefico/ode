package ode.uml.cgd;

import java.util.List;

import org.springframework.stereotype.Repository;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.controleProjeto.cdp.Projeto;
import ode.uml.cdp.Pacote;

@Repository(value="pacoteDao")
public class PacoteDAOImpl extends DAOBaseImpl<Pacote> implements PacoteDAO {
	
	@SuppressWarnings("unchecked")
	public List<Pacote> obterPacotesPorProjeto (Projeto p){
		String query = "select pac from Pacote pac ";
		query += "left join pac.projeto p ";
		query += "where p.id =  " + p.getId();
		return getEntityManager().createQuery(query).getResultList();
	}

	@Override
	public Pacote recuperarPorId(Long id) {
		String query = "select p from Pacote p, Projeto ";
		query += "left join fetch p.projeto ";
		query += "where p.id =  " + id;
		return (Pacote) getEntityManager().createQuery(query).getSingleResult();
	}
	
	public Pacote recuperarPorNomeEProjeto(String nome, Projeto projeto) {
		String query = "select p from Pacote p, Projeto ";
		query += "left join fetch p.projeto ";
		query += "where p.nome =  " + nome;
		query += "and p.projeto = " + projeto;
		return (Pacote) getEntityManager().createQuery(query).getSingleResult();
	}
}
