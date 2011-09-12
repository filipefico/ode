package ode.controleUsuario.cgt;

import java.util.Collection;

import ode.controleUsuario.cdp.Usuario;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;


public interface AplCadastrarUsuario {
	/**
	 * Exclui o Usuario informado
	 * 
	 * @param usuario
	 *            Usuario a ser excluído do sistema
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 */
	public void excluir(Usuario usuario);	

	/**
	 * Retorna todos os Usuario cadastrados.
	 * 
	 * @return coleção com todos os Usuario.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 */
	public Collection<Usuario> recuperarTodos();

	/**
	 * Retorna o Usuario cadastrado com o ID informado.
	 * 
	 * @param id
	 *            ID do Usuario a ser recuperado.
	 * @return o Usuario com o ID.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 */
	public Usuario recuperarPorId(Long id);

	/**
	 * Salva os dados do Usuario informado. Se o
	 * Usuario não existir, inclui um novo. Caso
	 * contrário, salva seus dados
	 * 
	 * @param usuario
	 *            Usuario a ser salvo no sistema
	 * @return o Usuario salvo
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso ocorra algum erro de regra de negócio.
	 */
	public Usuario salvar(Usuario usuario)
			throws NucleoRegraNegocioExcecao;
}
