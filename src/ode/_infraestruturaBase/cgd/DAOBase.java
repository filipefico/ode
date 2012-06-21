package ode._infraestruturaBase.cgd;

import java.util.Collection;

import ode._infraestruturaBase.cdp.ObjetoPersistente;

/**
 * Interface DAOBase. Cont�m m�todos gen�ricos para serem utilizados por
 * qualquer objeto persistente.
 * 
 */
public interface DAOBase<T extends ObjetoPersistente> {

	/**
	 * Salva um objeto de dom�nio na m�dia persistente.
	 * 
	 * @param objeto
	 *            Objeto de dom�nio.
	 */
	public void salvar(T objeto);
	

	/**
	 * Exclui um objeto de dom�nio da m�dia persistente.
	 * 
	 * @param objeto
	 *            Objeto de dom�nio.
	 */
	public void excluir(T objeto);

	/**
	 * Atualiza um objeto j� existente no banco.
	 * 
	 * @param objeto
	 *            Objeto a ser atualizado.
	 */
	public T atualizar(T objeto);

	/**
	 * Recupera todos os objetos persistentes da classe de dom�nio T.
	 * 
	 * @return Cole��o de todos os objetos persistentes da classe de dom�nio T.
	 */
	public Collection<T> recuperarTodos();

	public Collection<T> recuperarTodosComOrdenacao(String orderBy);

	/**
	 * Obt�m um objeto persistente da classe de dom�nio T, de acordo com seu
	 * identificador.
	 * 
	 * @param id
	 *            Identificador do objeto persistente.
	 * @return O objeto persistente cujo identificador foi dado.
	 */
	public T recuperarPorId(Long id);

	/**
	 * Recupera a classe do objeto T.
	 * 
	 * @return Classe do objeto T.
	 */
	public Class<T> getClasseDominio();
	
	/**
	 * Recupera quantidade total de objetos no banco.
	 * @return Quantidade total.
	 */
	public int recuperarQuantidadeTotal();

}
