package nucleo.comuns.autenticacao.acegi.persistencia;

import nucleo.comuns.autenticacao.acegi.dominio.NucleoUserDetails;

import ode.nucleo.cgd.NucleoDAOBase;

import org.acegisecurity.userdetails.UserDetails;
import org.springframework.dao.DataAccessException;


public interface NucleoUserDetailsDAO extends NucleoDAOBase<NucleoUserDetails> {
	/**
	 * Recupera um usuário por username
	 * 
	 * @param username
	 *            Login utilizado pelo usuário para entrar no sistema
	 * @return Objeto com informações do usuário
	 * @throws DataAccessException
	 *             Exceção de acesso a dados
	 */
	public UserDetails recuperarPorUsername(String username)
			throws DataAccessException;
}
