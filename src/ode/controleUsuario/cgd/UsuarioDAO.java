package ode.controleUsuario.cgd;


import ode.controleUsuario.cdp.NucleoUserDetails;
import ode.nucleo.crud.cgd.DAOBase;

import org.acegisecurity.userdetails.UserDetails;
import org.springframework.dao.DataAccessException;


public interface UsuarioDAO extends DAOBase<NucleoUserDetails> {
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
