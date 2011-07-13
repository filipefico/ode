package nucleo.comuns.autenticacao.acegi.persistencia;

import nucleo.comuns.autenticacao.acegi.dominio.NucleoUserDetails;

import ode.nucleo.cgd.NucleoDAOBase;

import org.acegisecurity.userdetails.UserDetails;
import org.springframework.dao.DataAccessException;


public interface NucleoUserDetailsDAO extends NucleoDAOBase<NucleoUserDetails> {
	/**
	 * Recupera um usu�rio por username
	 * 
	 * @param username
	 *            Login utilizado pelo usu�rio para entrar no sistema
	 * @return Objeto com informa��es do usu�rio
	 * @throws DataAccessException
	 *             Exce��o de acesso a dados
	 */
	public UserDetails recuperarPorUsername(String username)
			throws DataAccessException;
}
