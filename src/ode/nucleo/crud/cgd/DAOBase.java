package ode.nucleo.crud.cgd;

import java.util.Collection;

import ode.nucleo.cdp.ObjetoPersistente;


/**
 * Interface DAO do sistema.
 * 
 */
public interface DAOBase<T extends ObjetoPersistente> {
	
	/**
	 * Recupera todos os objetos persistentes da classe de dom�nio T.
	 * 
	 * @return Cole��o de todos os objetos persistentes da classe de dom�nio T.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 */
	public Collection<T> recuperarTodos();
	
	/**
	 * Atualiza um objeto j� existente no banco.
	 * @param objeto Objeto a ser atualizado.
	 */
	public void merge(T objeto);

	/**
	 * Obt�m um objeto persistente da classe de dom�nio T, de acordo com seu identificador.
	 * 
	 * @param id
	 *            Identificador do objeto persistente.
	 * @return O objeto persistente cujo identificador foi dado.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 */
	public T recuperarPorId(Long id);

	/**
	 * Salva um objeto de dom�nio na m�dia persistente.
	 * 
	 * @param objeto
	 *            Objeto de dom�nio.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 */
	void salvar(T objeto);

	/**
	 * Exclui um objeto de dom�nio da m�dia persistente.
	 * 
	 * @param objeto
	 *            Objeto de dom�nio.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 */
	void excluir(T objeto);
		
}
