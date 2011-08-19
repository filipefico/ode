package ode.controleUsuario.cgd;

import java.util.List;


import ode.controleUsuario.cdp.NucleoUserDetails;
import ode.nucleo.crud.cgd.DAOBaseHibernate;

import org.acegisecurity.userdetails.UserDetails;
import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOHibernate extends
		DAOBaseHibernate<NucleoUserDetails> implements UsuarioDAO {

	@SuppressWarnings("unchecked")
	public UserDetails recuperarPorUsername(String username)
			throws DataAccessException {
		try {
			List<NucleoUserDetails> usuarios = getEntityManager().createQuery(
					"from NucleoUserDetails ud where ud.username = :user").setParameter("user", username).getResultList();

			if (usuarios.size() == 0) {
				return null;
			} else {
				return usuarios.iterator().next();
			}

		} catch (HibernateException e) {
			throw new DataRetrievalFailureException(
					"Erro ao tentar obter usuário por username!", e);
		}
	}
}
