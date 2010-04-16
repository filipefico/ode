package nucleo.comuns.aplicacao;

import java.util.Collection;
import java.util.List;

import nucleo.comuns.excecao.NucleoExcecao;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.persistencia.NucleoDAOBase;
import nucleo.comuns.persistencia.NucleoObjetoPersistenteImpl;
import nucleo.comuns.persistencia.ObjetoPagina;

import ode.exemplo.dominio.PessoaExemplo;

import org.hibernate.criterion.Criterion;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = NucleoExcecao.class)
public abstract class NucleoAplCadastroBaseImpl<T extends NucleoObjetoPersistenteImpl<Long, Long>>
		implements NucleoAplCadastroBase<T> {

	NucleoDAOBase<T> nucleoDaoBase;

	public NucleoDAOBase<T> getNucleoDaoBase() {
		return nucleoDaoBase;
	}

	public void setNucleoDaoBase(NucleoDAOBase<T> nucleoDaoBase) {
		this.nucleoDaoBase = nucleoDaoBase;
	}

	public void excluir(T objeto) throws NucleoRegraNegocioExcecao {

		// Executa as ações necessárias antes da exclusão de um objeto.
		antesExcluir(objeto);

		// Exclui o objeto
		nucleoDaoBase.excluir(objeto);

		// Executa as ações necessárias depois da exclusão de um objeto.
		depoisExcluir(objeto);

	}

	/**
	 * Executa as ações necessárias antes da exclusão de um objeto. As
	 * aplicações que herdam desta classe devem sobrescrever este método para
	 * obter um comportamento específico antes da exclusão.
	 * 
	 * @param objeto
	 *            Objeto sendo excluído.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de negócio.
	 */
	protected void antesExcluir(T objeto) throws NucleoRegraNegocioExcecao {
	}

	/**
	 * Executa as ações necessárias depois da exclusão de um objeto. As
	 * aplicações que herdam desta classe devem sobrescrever este método para
	 * obter um comportamento específico depois da exclusão.
	 * 
	 * @param objeto
	 *            Objeto sendo excluído.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de negócio.
	 */
	protected void depoisExcluir(T objeto) throws NucleoRegraNegocioExcecao {
	}

	public T recuperarPorId(Long id) throws NucleoRegraNegocioExcecao {
		return nucleoDaoBase.recuperarPorId(id);
	}

	public Collection<T> recuperarTodos() throws NucleoRegraNegocioExcecao {
		return nucleoDaoBase.recuperarTodos();
	}

	public T salvar(T objeto) throws NucleoRegraNegocioExcecao {
		// Executa a ação necessária antes do salvamento do objeto
		antesSalvar(objeto);

		// Se for persistente, altera dados. Senão, inclui um novo
		if (objeto.isPersistente()) {
			objeto = alterarDados(objeto);
		} else {
			objeto = incluirNovo(objeto);
		}

		// Executa a ação necessária depois do salvamento do objeto
		depoisSalvar(objeto);

		return objeto;
	}

	/**
	 * Executa as ações necessárias antes da gravação (inclusão ou alteração de
	 * dados) de um objeto. As aplicações que herdam desta classe devem
	 * sobrescrever este método para obter um comportamento específico antes da
	 * gravação.
	 * 
	 * @param objeto
	 *            Objeto sendo gravado.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de negócio.
	 */
	protected void antesSalvar(T objeto) throws NucleoRegraNegocioExcecao {
	}

	/**
	 * Executa as ações necessárias depois da gravação (inclusão ou alteração de
	 * dados) de um objeto. As aplicações que herdam desta classe devem
	 * sobrescrever este método para obter um comportamento específico depois da
	 * gravação.
	 * 
	 * @param objeto
	 *            Objeto sendo gravado.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de negócio.
	 */
	protected void depoisSalvar(T objeto) throws NucleoRegraNegocioExcecao {
	}

	/**
	 * Inclui um novo Objeto no sistema. É importante observar que o Objeto não
	 * pode ser persistente, ou seja, seu método isPersistente() deve retornar
	 * falso.
	 * 
	 * @param objeto
	 *            Objeto a ser persistido
	 * @return Objeto persistido.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de negócio.
	 */
	protected T incluirNovo(T objeto) throws NucleoRegraNegocioExcecao {
		// Executa a ação necessária antes de incluir um novo objeto
		antesIncluirNovo(objeto);

		// Inclui o Principal Serviço
		nucleoDaoBase.salvar(objeto);

		// Executa a ação necessária depois de incluir um novo objeto
		depoisIncluirNovo(objeto);

		return objeto;
	}

	/**
	 * Executa as ações necessárias antes de incluir um novo objeto. As
	 * aplicações que herdam desta classe devem sobrescrever este método para
	 * obter um comportamento específico antes da inclusão.
	 * 
	 * @param objeto
	 *            Objeto sendo incluído.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de negócio.
	 */
	protected void antesIncluirNovo(T objeto) throws NucleoRegraNegocioExcecao {
	}

	/**
	 * Executa as ações necessárias depois de incluir um novo objeto. As
	 * aplicações que herdam desta classe devem sobrescrever este método para
	 * obter um comportamento específico depois da inclusão.
	 * 
	 * @param objeto
	 *            Objeto sendo incluído.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de negócio.
	 */
	protected void depoisIncluirNovo(T objeto) throws NucleoRegraNegocioExcecao {
	}

	/**
	 * Salva os dados de um objeto já persistido.
	 * 
	 * @param objeto
	 *            Objeto cujos dados devem ser alterados.
	 * @return o Objeto alterado.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de negócio.
	 */
	protected T alterarDados(T objeto) throws NucleoRegraNegocioExcecao {
		// Executa a ação necessária antes de alterar os dados
		antesAlterarDados(objeto);

		// Obtém o Principal Serviço persistido e altera seus dados
		T objetoPersistido = nucleoDaoBase.recuperarPorId(objeto.getId());
		copiarValor(objeto, objetoPersistido);

		// Executa a ação necessária depois de alterar os dados
		depoisAlterarDados(objeto);

		return objetoPersistido;
	}

	/**
	 * Executa as ações necessárias antes de alterar os dados de um objeto. As
	 * aplicações que herdam desta classe devem sobrescrever este método para
	 * obter um comportamento específico antes da alteração.
	 * 
	 * @param objeto
	 *            Objeto sendo alterado.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de negócio.
	 */
	protected void antesAlterarDados(T objeto) throws NucleoRegraNegocioExcecao {
	}

	/**
	 * Executa as ações necessárias depois de alterar os dados de um objeto. As
	 * aplicações que herdam desta classe devem sobrescrever este método para
	 * obter um comportamento específico depois da alteração.
	 * 
	 * @param objeto
	 *            Objeto sendo alterado.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de negócio.
	 */
	protected void depoisAlterarDados(T objeto)
			throws NucleoRegraNegocioExcecao {
	}

	/**
	 * Copia os valores do objetoFonte para o objetoDestino
	 * 
	 * @param objetoFonte
	 *            Objeto de onde os dados devem ser copiados
	 * @param objetoDestino
	 *            Objeto para onde os dados devem ser copiados
	 */
	protected abstract void copiarValor(T objetoFonte, T objetoDestino);

	public Collection<T> recuperarTodosPaginado(ObjetoPagina pagina)
			throws NucleoRegraNegocioExcecao {
		Collection<T>  result = nucleoDaoBase.recuperarTodosPaginado(pagina);
		return result;
	}

}
