package ode.controleUsuario.cgd;


import ode.controleUsuario.cdp.Usuario;
import ode.nucleo.crud.cgd.DAOBase;
import org.springframework.dao.DataAccessException;


public interface UsuarioDAO extends DAOBase<Usuario> {
	/**
	 * Recupera um usu�rio por username
	 * 
	 * @param nomeUsuario
	 *            Nome de Usu�rio utilizado pelo usu�rio para entrar no sistema
	 * @return Objeto com informa��es do usu�rio
	 * @throws DataAccessException
	 *             Exce��o de acesso a dados
	 */
	public Usuario recuperarPorNomeUsuario(String nomeUsuario)
			throws DataAccessException;
}
