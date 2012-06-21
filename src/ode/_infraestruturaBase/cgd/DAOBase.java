package ode._infraestruturaBase.cgd;

import java.util.Collection;

import ode._infraestruturaBase.cdp.ObjetoPersistente;

/**
 * Interface DAOBase. Contém métodos genéricos para serem utilizados por
 * qualquer objeto persistente.
 * 
 */
public interface DAOBase<T extends ObjetoPersistente> {

	/**
	 * Salva um objeto de domínio na mídia persistente.
	 * 
	 * @param objeto
	 *            Objeto de domínio.
	 */
	public void salvar(T objeto);
	

	/**
	 * Exclui um objeto de domínio da mídia persistente.
	 * 
	 * @param objeto
	 *            Objeto de domínio.
	 */
	public void excluir(T objeto);

	/**
	 * Atualiza um objeto já existente no banco.
	 * 
	 * @param objeto
	 *            Objeto a ser atualizado.
	 */
	public T atualizar(T objeto);

	/**
	 * Recupera todos os objetos persistentes da classe de domínio T.
	 * 
	 * @return Coleção de todos os objetos persistentes da classe de domínio T.
	 */
	public Collection<T> recuperarTodos();

	public Collection<T> recuperarTodosComOrdenacao(String orderBy);

	/**
	 * Obtém um objeto persistente da classe de domínio T, de acordo com seu
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
