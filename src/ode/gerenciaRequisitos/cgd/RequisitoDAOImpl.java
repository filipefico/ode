package ode.gerenciaRequisitos.cgd;

import java.util.Collection;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.controleProjeto.cdp.Projeto;
import ode.gerenciaRequisitos.cdp.Requisito;
import ode.uml.cdp.CasoUso;
import ode.uml.cdp.Classe;
import ode.uml.cdp.Pacote;

import org.springframework.stereotype.Repository;

@Repository(value="requisitoDao")
public class RequisitoDAOImpl extends DAOBaseImpl<Requisito> implements RequisitoDAO{

	@Override
	public Requisito recuperarPorId(Long id) {
		String query = "select r from Requisito r ";
		query += "left join fetch r.categoria ";
		query += "left join fetch r.conflitos ";
		query += "left join fetch r.dependencias ";
		query += "left join fetch r.projeto ";
		query += "left join fetch r.pacotes ";
		query += "left join fetch r.casosUso ";
		query += "left join fetch r.interessados ";
		query += "left join fetch r.responsaveis ";	
		query += "where r.id =  " + id;
		
		return (Requisito) getEntityManager().createQuery(query).getResultList().get(0);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Requisito> obterPorProjeto (Projeto p){
		String query = "select r from Requisito r ";
		query += "left join r.projeto p ";
		query += "where p.id =  " + p.getId();
		query += " order by r.identificador";
		return getEntityManager().createQuery(query).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Requisito> obterPorCasoUso (CasoUso casoUso){
		String query = "select r from Requisito r ";
		query += "left join r.casosUso c ";
		query += "where c.id =  " + casoUso.getId();
		return getEntityManager().createQuery(query).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Requisito> obterPorClasse (Classe classe){
		String query = "select r from Requisito r ";
		query += "left join r.casosUso cau ";
		query += "left join cau.classes c ";
		query += "where c.id =  " + classe.getId();
		return getEntityManager().createQuery(query).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Requisito> obterPorPacote (Pacote pacote){
		String query = "select r from Requisito r ";
		query += "left join r.pacotes p ";
		query += "where p.id =  " + pacote.getId();
		return getEntityManager().createQuery(query).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Requisito> obterPorInteressado (RecursoHumano recursoHumano){
		String query = "select r from Requisito r ";
		query += "left join r.interessados i ";
		query += "where i.id =  " + recursoHumano.getId();
		return getEntityManager().createQuery(query).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Requisito> obterPorResponsavel (RecursoHumano recursoHumano){
		String query = "select r from Requisito r ";
		query += "left join r.responsaveis resp ";
		query += "where resp.id =  " + recursoHumano.getId();
		return getEntityManager().createQuery(query).getResultList();
	}
	
	public Requisito obterPorIdentificadorEProjeto (String identificador, Projeto projeto){
		String query = "select r from Requisito r ";
		query += "left join r.projeto p ";
		query += "where p.id =  " + projeto.getId();
		query += "and r.identificador = " + identificador;
		query += " order by r.identificador";
		return (Requisito) getEntityManager().createQuery(query).getSingleResult();
	}
}
