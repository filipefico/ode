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

		// Executa as a��es necess�rias antes da exclus�o de um objeto.
		antesExcluir(objeto);

		// Exclui o objeto
		nucleoDaoBase.excluir(objeto);

		// Executa as a��es necess�rias depois da exclus�o de um objeto.
		depoisExcluir(objeto);

	}

	/**
	 * Executa as a��es necess�rias antes da exclus�o de um objeto. As
	 * aplica��es que herdam desta classe devem sobrescrever este m�todo para
	 * obter um comportamento espec�fico antes da exclus�o.
	 * 
	 * @param objeto
	 *            Objeto sendo exclu�do.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de neg�cio.
	 */
	protected void antesExcluir(T objeto) throws NucleoRegraNegocioExcecao {
	}

	/**
	 * Executa as a��es necess�rias depois da exclus�o de um objeto. As
	 * aplica��es que herdam desta classe devem sobrescrever este m�todo para
	 * obter um comportamento espec�fico depois da exclus�o.
	 * 
	 * @param objeto
	 *            Objeto sendo exclu�do.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de neg�cio.
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
		// Executa a a��o necess�ria antes do salvamento do objeto
		antesSalvar(objeto);

		// Se for persistente, altera dados. Sen�o, inclui um novo
		if (objeto.isPersistente()) {
			objeto = alterarDados(objeto);
		} else {
			objeto = incluirNovo(objeto);
		}

		// Executa a a��o necess�ria depois do salvamento do objeto
		depoisSalvar(objeto);

		return objeto;
	}

	/**
	 * Executa as a��es necess�rias antes da grava��o (inclus�o ou altera��o de
	 * dados) de um objeto. As aplica��es que herdam desta classe devem
	 * sobrescrever este m�todo para obter um comportamento espec�fico antes da
	 * grava��o.
	 * 
	 * @param objeto
	 *            Objeto sendo gravado.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de neg�cio.
	 */
	protected void antesSalvar(T objeto) throws NucleoRegraNegocioExcecao {
	}

	/**
	 * Executa as a��es necess�rias depois da grava��o (inclus�o ou altera��o de
	 * dados) de um objeto. As aplica��es que herdam desta classe devem
	 * sobrescrever este m�todo para obter um comportamento espec�fico depois da
	 * grava��o.
	 * 
	 * @param objeto
	 *            Objeto sendo gravado.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de neg�cio.
	 */
	protected void depoisSalvar(T objeto) throws NucleoRegraNegocioExcecao {
	}

	/**
	 * Inclui um novo Objeto no sistema. � importante observar que o Objeto n�o
	 * pode ser persistente, ou seja, seu m�todo isPersistente() deve retornar
	 * falso.
	 * 
	 * @param objeto
	 *            Objeto a ser persistido
	 * @return Objeto persistido.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de neg�cio.
	 */
	protected T incluirNovo(T objeto) throws NucleoRegraNegocioExcecao {
		// Executa a a��o necess�ria antes de incluir um novo objeto
		antesIncluirNovo(objeto);

		// Inclui o Principal Servi�o
		nucleoDaoBase.salvar(objeto);

		// Executa a a��o necess�ria depois de incluir um novo objeto
		depoisIncluirNovo(objeto);

		return objeto;
	}

	/**
	 * Executa as a��es necess�rias antes de incluir um novo objeto. As
	 * aplica��es que herdam desta classe devem sobrescrever este m�todo para
	 * obter um comportamento espec�fico antes da inclus�o.
	 * 
	 * @param objeto
	 *            Objeto sendo inclu�do.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de neg�cio.
	 */
	protected void antesIncluirNovo(T objeto) throws NucleoRegraNegocioExcecao {
	}

	/**
	 * Executa as a��es necess�rias depois de incluir um novo objeto. As
	 * aplica��es que herdam desta classe devem sobrescrever este m�todo para
	 * obter um comportamento espec�fico depois da inclus�o.
	 * 
	 * @param objeto
	 *            Objeto sendo inclu�do.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de neg�cio.
	 */
	protected void depoisIncluirNovo(T objeto) throws NucleoRegraNegocioExcecao {
	}

	/**
	 * Salva os dados de um objeto j� persistido.
	 * 
	 * @param objeto
	 *            Objeto cujos dados devem ser alterados.
	 * @return o Objeto alterado.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de neg�cio.
	 */
	protected T alterarDados(T objeto) throws NucleoRegraNegocioExcecao {
		// Executa a a��o necess�ria antes de alterar os dados
		antesAlterarDados(objeto);

		// Obt�m o Principal Servi�o persistido e altera seus dados
		T objetoPersistido = nucleoDaoBase.recuperarPorId(objeto.getId());
		copiarValor(objeto, objetoPersistido);

		// Executa a a��o necess�ria depois de alterar os dados
		depoisAlterarDados(objeto);

		return objetoPersistido;
	}

	/**
	 * Executa as a��es necess�rias antes de alterar os dados de um objeto. As
	 * aplica��es que herdam desta classe devem sobrescrever este m�todo para
	 * obter um comportamento espec�fico antes da altera��o.
	 * 
	 * @param objeto
	 *            Objeto sendo alterado.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de neg�cio.
	 */
	protected void antesAlterarDados(T objeto) throws NucleoRegraNegocioExcecao {
	}

	/**
	 * Executa as a��es necess�rias depois de alterar os dados de um objeto. As
	 * aplica��es que herdam desta classe devem sobrescrever este m�todo para
	 * obter um comportamento espec�fico depois da altera��o.
	 * 
	 * @param objeto
	 *            Objeto sendo alterado.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de neg�cio.
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
