package ode.nucleo.cgt;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;


import ode.nucleo.cgd.NucleoDAOBase;
import ode.nucleo.cgd.NucleoObjetoPersistenteImpl;
import ode.nucleo.cgd.ObjetoPagina;
import ode.nucleo.cgd.ResultadoPaginado;
import ode.nucleo.excecao.NucleoExcecao;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "NucleoAplCadastroBase")
@Transactional(rollbackFor = NucleoExcecao.class)
public abstract class NucleoAplCadastroBaseImpl<T extends NucleoObjetoPersistenteImpl<Long, Long>>
		implements NucleoAplCadastroBase<T> {

	public abstract NucleoDAOBase<T> getNucleoDaoBase();

	public void excluir(T objeto) throws NucleoRegraNegocioExcecao {

		// Executa as ações necessárias antes da exclusão de um objeto.
		antesExcluir(objeto);

		// Exclui o objeto
		getNucleoDaoBase().excluir(objeto);

		// Executa as ações necessárias depois da exclusão de um objeto.
		depoisExcluir(objeto);

	}

	public void excluir(Set<T> objetos) throws NucleoRegraNegocioExcecao {
		
		Iterator<T> itItensSelecionados = objetos.iterator();

		while (itItensSelecionados.hasNext()) {
			T objeto = itItensSelecionados.next();
			excluir(objeto);

		}

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

	public T recuperarPorId(Long id) {
		return getNucleoDaoBase().recuperarPorId(id);
	}

	public Collection<T> recuperarTodos() {
		return getNucleoDaoBase().recuperarTodos();
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
		getNucleoDaoBase().salvar(objeto);

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
		getNucleoDaoBase().merge(objeto);

		// Executa a ação necessária depois de alterar os dados
		depoisAlterarDados(objeto);

		return objeto;
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

}
