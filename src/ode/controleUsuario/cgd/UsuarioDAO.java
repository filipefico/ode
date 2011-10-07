package ode.controleUsuario.cgd;


import ode._infraestruturaBase.cgd.DAOBase;
import ode.controleUsuario.cdp.Usuario;

import org.springframework.dao.DataAccessException;


public interface UsuarioDAO extends DAOBase<Usuario> {
	/**
	 * Recupera um usuário por username
	 * 
	 * @param nomeUsuario
	 *            Nome de Usuário utilizado pelo usuário para entrar no sistema
	 * @return Objeto com informações do usuário
	 * @throws DataAccessException
	 *             Exceção de acesso a dados
	 */
	public Usuario recuperarPorNomeUsuario(String nomeUsuario)
			throws DataAccessException;
}
