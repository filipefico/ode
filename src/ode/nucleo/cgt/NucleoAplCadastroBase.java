package ode.nucleo.cgt;

import java.util.Collection;
import java.util.Set;

import ode.nucleo.cgd.NucleoObjetoPersistenteImpl;
import ode.nucleo.cgd.ObjetoPagina;
import ode.nucleo.cgd.ResultadoPaginado;

import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;


/**
 * Interface para cadastros b�sicos (consulta, grava��o e exclus�o).
 * 
 * @author Alexandre G. N. Coelho
 */
public interface NucleoAplCadastroBase<T extends NucleoObjetoPersistenteImpl<Long, Long>> {

	/**
	 * Exclui o objeto passado da m�dia persistente.
	 * 
	 * @param objeto
	 *            Objeto a ser exclu�do
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de neg�cio.
	 */
	public void excluir(T objeto) throws NucleoRegraNegocioExcecao;
	
	public void excluir(Set<T> objeto) throws NucleoRegraNegocioExcecao;

	/**
	 * Recupera todos os objetos T persistidos
	 * 
	 * @return Todos os objetos T persistidos
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de neg�cio.
	 */
	public Collection<T> recuperarTodos();

	/**
	 * Recupera o objeto T com o id passado
	 * 
	 * @param id
	 *            Identificador do objeto a ser recuperado
	 * @return O objeto T com o id passado
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de neg�cio.
	 *             
	 */
	
	 
	
	
	
	public T recuperarPorId(Long id);

	/**
	 * Inclui o objeto caso ele n�o seja persistente ou grava suas altera��es
	 * caso ele seja persistente.
	 * 
	 * @param objeto
	 *            Objeto a ser inclu�do/salvo.
	 * @return O objeto persistido.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de neg�cio.
	 */
	public T salvar(T objeto) throws NucleoRegraNegocioExcecao;
	
}
