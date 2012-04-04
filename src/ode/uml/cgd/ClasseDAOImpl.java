package ode.uml.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.controleProjeto.cdp.Projeto;
import ode.uml.cdp.Classe;
import ode.uml.cdp.Pacote;

@Repository(value="classeDao")
public class ClasseDAOImpl extends DAOBaseImpl<Classe> implements
ClasseDAO {
	@SuppressWarnings("unchecked")
	public Collection<Classe> obterPorProjeto(Projeto p){
		String query = "select c from Classe c ";
		query += "left join c.pacote left join c.pacote.projeto p ";
		query += "where p.id =  " + p.getId();
		return getEntityManager().createQuery(query).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Classe> obterPorPacote (Pacote pacote){
		String query = "select c from Classe c ";
		query += "left join c.pacote p ";
		query += "where p.id =  " + pacote.getId();
		return getEntityManager().createQuery(query).getResultList();
	}

	@Override
	public Classe recuperarPorId(Long id) {
		String query = "select c from Classe c, Pacote ";
		query += "left join fetch c.pacote ";
		query += "where c.id =  " + id;
		return (Classe) getEntityManager().createQuery(query).getSingleResult();
	}
	
	public Classe recuperarPorNomeEProjeto (String nome, Projeto p){
		String query = "select c from Classe c ";
		query += "left join c.pacote.projeto p ";
		query += "where p.id =  " + p.getId();
		query += "and c.nome = " + nome;
		return (Classe) getEntityManager().createQuery(query).getSingleResult();
	}
}
