package nucleo.comuns.autenticacao.acegi.persistencia;

import java.util.List;

import nucleo.comuns.autenticacao.acegi.dominio.NucleoUserDetails;

import ode.nucleo.cgd.NucleoDAOBaseHibernate;

import org.acegisecurity.userdetails.UserDetails;
import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

@Repository
public class NucleoUserDetailsDAOHibernate extends
		NucleoDAOBaseHibernate<NucleoUserDetails> implements NucleoUserDetailsDAO {

	@SuppressWarnings("unchecked")
	public UserDetails recuperarPorUsername(String username)
			throws DataAccessException {
		try {
			List<NucleoUserDetails> usuarios = getHibernateTemplate()
					.findByNamedParam(
							"from NucleoUserDetails ud "
									+ " where ud.username = :username",
							"username", username);

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
