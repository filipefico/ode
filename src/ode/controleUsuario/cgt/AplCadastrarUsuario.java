package ode.controleUsuario.cgt;

import java.util.Collection;

import ode.controleUsuario.cdp.NucleoUserDetails;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;


public interface AplCadastrarUsuario {
	/**
	 * Exclui o NucleoUserDetails informado
	 * 
	 * @param nucleoUserDetails
	 *            NucleoUserDetails a ser excluído do sistema
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 */
	public void excluir(NucleoUserDetails nucleoUserDetails);

	/**
	 * Retorna todos os NucleoUserDetails cadastrados.
	 * 
	 * @return coleção com todos os NucleoUserDetails.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 */
	public Collection<NucleoUserDetails> recuperarTodos();

	/**
	 * Retorna o NucleoUserDetails cadastrado com o ID informado.
	 * 
	 * @param id
	 *            ID do NucleoUserDetails a ser recuperado.
	 * @return o NucleoUserDetails com o ID.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 */
	public NucleoUserDetails recuperarPorId(Long id);

	/**
	 * Salva os dados do NucleoUserDetails informado. Se o
	 * NucleoUserDetails não existir, inclui um novo. Caso
	 * contrário, salva seus dados
	 * 
	 * @param nucleoUserDetails
	 *            NucleoUserDetails a ser salvo no sistema
	 * @return o NucleoUserDetails salvo
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso ocorra algum erro de regra de negócio.
	 */
	public NucleoUserDetails salvar(NucleoUserDetails nucleoUserDetails)
			throws NucleoRegraNegocioExcecao;
}
