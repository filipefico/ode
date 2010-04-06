package nucleo.comuns.persistencia;

import java.util.Collection;

/**
 * Interface base para todos as classes DAO do sistema.
 * 
 * </p>
 * <p>
 * Para mais informa��es sobre o padr�o de projeto DAO, visite: <a
 * href="http://java.sun.com/blueprints/corej2eepatterns/Patterns/DataAccessObject.html"
 * target="_blank">http://java.sun.com/blueprints/corej2eepatterns/Patterns/DataAccessObject.html</a>.
 * 
 * @author Vitor Souza
 */
public interface NucleoDAOBase<T extends NucleoObjetoPersistenteImpl> {
	/**
	 * Recupera todos os objetos persistentes da classe de dom�nio.
	 * 
	 * @return Cole��o de todos os objetos persistentes da classe de dom�nio.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 */
	Collection<T> recuperarTodos();

	/**
	 * Obt�m um objeto persistente da classe de dom�nio dado seu identificador.
	 * 
	 * @param id
	 *            Identificador do objeto persistente.
	 * @return O objeto persistente cujo identificador foi dado.
	 * @throws NucleoDAOExcecao
	 *             Caso ocorra algum problema no acesso ao banco de dados.
	 */
	T recuperarPorId(Long id);

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
