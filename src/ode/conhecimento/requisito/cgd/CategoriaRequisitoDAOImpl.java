package ode.conhecimento.requisito.cgd;

import org.springframework.stereotype.Repository;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.conhecimento.requisito.cdp.CategoriaRequisito;
import ode.conhecimento.requisito.cdp.TipoRequisito;
import ode.conhecimento.requisito.cgd.CategoriaRequisitoDAO;

@Repository(value="categoriaRequisitoDao")
public class CategoriaRequisitoDAOImpl extends DAOBaseImpl<CategoriaRequisito> implements
CategoriaRequisitoDAO {
	
	@SuppressWarnings("unchecked")
	public Collection<CategoriaRequisito> obterCategoriaPorTipo (TipoRequisito tipoRequisito){
		StringBuilder consulta = new StringBuilder();
		consulta.append("select c from CategoriaRequisito c ");
		consulta.append("where c.tipoRequisito = " + tipoRequisito.ordinal());
		
		return getEntityManager().createQuery(consulta.toString()).getResultList();
	}

	@Override
	public CategoriaRequisito recuperarPorId(Long id) {
		String query = "select c from CategoriaRequisito c ";
		query += "left join fetch c.superCategoria ";
		query += "where c.id =  " + id;
		return (CategoriaRequisito) getEntityManager().createQuery(query).getSingleResult();
	}
	

}
