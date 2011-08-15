package ode.nucleo.crud.cgd;

import java.util.Collection;

import ode.nucleo.cdp.ObjetoPersistente;


/**
 * Interface DAO do sistema.
 * 
 */
public interface DAOBase<T extends ObjetoPersistente> {
	
	/**
	 * Recupera todos os objetos persistentes da classe de domínio T.
	 * 
	 * @return Coleção de todos os objetos persistentes da classe de domínio T.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 */
	public Collection<T> recuperarTodos();
	
	/**
	 * Atualiza um objeto já existente no banco.
	 * @param objeto Objeto a ser atualizado.
	 */
	public void merge(T objeto);

	/**
	 * Obtém um objeto persistente da classe de domínio T, de acordo com seu identificador.
	 * 
	 * @param id
	 *            Identificador do objeto persistente.
	 * @return O objeto persistente cujo identificador foi dado.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 */
	public T recuperarPorId(Long id);

	/**
	 * Salva um objeto de domínio na mídia persistente.
	 * 
	 * @param objeto
	 *            Objeto de domínio.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 */
	void salvar(T objeto);

	/**
	 * Exclui um objeto de domínio da mídia persistente.
	 * 
	 * @param objeto
	 *            Objeto de domínio.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 */
	void excluir(T objeto);
		
}
