package ode.nucleo.cgd;

import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Criterion;

/**
 * Interface base para todos as classes DAO do sistema.
 * 
 * </p>
 * <p>
 * Para mais informações sobre o padrão de projeto DAO, visite: <a
 * href="http://java.sun.com/blueprints/corej2eepatterns/Patterns/DataAccessObject.html"
 * target="_blank">http://java.sun.com/blueprints/corej2eepatterns/Patterns/DataAccessObject.html</a>.
 * 
 * @author Vitor Souza
 */
public interface NucleoDAOBase<T extends NucleoObjetoPersistenteImpl> {
	/**
	 * Recupera todos os objetos persistentes da classe de domínio.
	 * 
	 * @return Coleção de todos os objetos persistentes da classe de domínio.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 */
	Collection<T> recuperarTodos();
	
	public void merge(T objeto);

	/**
	 * Obtém um objeto persistente da classe de domínio dado seu identificador.
	 * 
	 * @param id
	 *            Identificador do objeto persistente.
	 * @return O objeto persistente cujo identificador foi dado.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 */
	T recuperarPorId(Long id);

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
	
		
	public Collection<T> recuperarTodosPaginado(ObjetoPagina pagina) ;
	
	public int recuperarQteTodos(ObjetoPagina pagina) ;
	
	
}
