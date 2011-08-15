package ode.controleUsuario.cgd;


import ode.controleUsuario.cdp.NucleoUserDetails;
import ode.nucleo.crud.cgd.DAOBase;

import org.acegisecurity.userdetails.UserDetails;
import org.springframework.dao.DataAccessException;


public interface UsuarioDAO extends DAOBase<NucleoUserDetails> {
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
