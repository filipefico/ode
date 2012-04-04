package ode.uml.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.controleProjeto.cdp.Projeto;
import ode.uml.cdp.CasoUso;
import ode.uml.cgd.CasoUsoDAO;

@Repository(value = "casoUsoDao")
public class CasoUsoDAOImpl extends DAOBaseImpl<CasoUso> implements CasoUsoDAO {

	@Override
	public CasoUso recuperarPorId(Long id) {
		String query = "select c from CasoUso c ";
		query += "left join fetch c.pacote ";
		query += "left join fetch c.classes ";
		query += "where c.id =  " + id;
		return (CasoUso) getEntityManager().createQuery(query)
				.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public Collection<CasoUso> obterPorProjeto(Projeto projeto) {
		String query = "select c from CasoUso c ";
		query += "left join c.pacote ";
		query += "left join c.pacote.projeto p ";
		query += "where p.id = " + projeto.getId();
		return getEntityManager().createQuery(query).getResultList();
	}

	public CasoUso obterPorNomeEProjeto(String nome, Projeto p) {
		String query = "select c from CasoUso c ";
		query += "left join c.pacote.projeto p ";
		query += "where p.id =  " + p.getId();
		query += "and c.nome = " + nome;
		return (CasoUso) getEntityManager().createQuery(query).getSingleResult();
	}

}
