package nucleo.comuns.autenticacao.acegi.aplicacao;

import java.util.Collection;

import nucleo.comuns.autenticacao.acegi.dominio.NucleoUserDetails;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;


public interface NucleoAplCadastrarNucleoUserDetails {
	/**
	 * Exclui o NucleoUserDetails informado
	 * 
	 * @param nucleoUserDetails
	 *            NucleoUserDetails a ser exclu�do do sistema
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 */
	public void excluir(NucleoUserDetails nucleoUserDetails);

	/**
	 * Retorna todos os NucleoUserDetails cadastrados.
	 * 
	 * @return cole��o com todos os NucleoUserDetails.
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
	 * NucleoUserDetails n�o existir, inclui um novo. Caso
	 * contr�rio, salva seus dados
	 * 
	 * @param nucleoUserDetails
	 *            NucleoUserDetails a ser salvo no sistema
	 * @return o NucleoUserDetails salvo
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso ocorra algum erro de regra de neg�cio.
	 */
	public NucleoUserDetails salvar(NucleoUserDetails nucleoUserDetails)
			throws NucleoRegraNegocioExcecao;
}
