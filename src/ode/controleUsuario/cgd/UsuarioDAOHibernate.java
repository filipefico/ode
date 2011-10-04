package ode.controleUsuario.cgd;

import java.util.List;

import ode.controleUsuario.cdp.Usuario;
import ode.nucleo.crud.cgd.DAOBaseHibernate;

import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOHibernate extends
		DAOBaseHibernate<Usuario> implements UsuarioDAO {

	@SuppressWarnings("unchecked")
	public Usuario recuperarPorNomeUsuario(String nomeUsuario)
			throws DataAccessException {
		try {		
			List<Usuario> usuarios = getEntityManager().
					createQuery("from Usuario ud where ud.nomeUsuario = :nomeUsuario").setParameter("nomeUsuario", nomeUsuario).getResultList();

			if (usuarios.size() == 0) {
				return null;
			} else {
				return usuarios.iterator().next();
			}

		} catch (HibernateException e) {
			throw new DataRetrievalFailureException(
					"Erro ao tentar obter usuário por nome de usuário!", e);
		}
	}
}