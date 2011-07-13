package ode.nucleo.cgt;

import java.util.Collection;
import java.util.Set;

import ode.nucleo.cgd.NucleoObjetoPersistenteImpl;
import ode.nucleo.cgd.ObjetoPagina;
import ode.nucleo.cgd.ResultadoPaginado;

import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;


/**
 * Interface para cadastros básicos (consulta, gravação e exclusão).
 * 
 * @author Alexandre G. N. Coelho
 */
public interface NucleoAplCadastroBase<T extends NucleoObjetoPersistenteImpl<Long, Long>> {

	/**
	 * Exclui o objeto passado da mídia persistente.
	 * 
	 * @param objeto
	 *            Objeto a ser excluído
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de negócio.
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
	 *             Caso haja algum erro de regra de negócio.
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
	 *             Caso haja algum erro de regra de negócio.
	 *             
	 */
	
	 
	
	
	
	public T recuperarPorId(Long id);

	/**
	 * Inclui o objeto caso ele não seja persistente ou grava suas alterações
	 * caso ele seja persistente.
	 * 
	 * @param objeto
	 *            Objeto a ser incluído/salvo.
	 * @return O objeto persistido.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de negócio.
	 */
	public T salvar(T objeto) throws NucleoRegraNegocioExcecao;
	
}
