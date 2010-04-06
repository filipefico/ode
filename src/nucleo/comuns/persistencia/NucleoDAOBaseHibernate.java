package nucleo.comuns.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * 
 * @author Vitor Souza
 * 
 * @param <T>
 *            Tipo do objeto persistido.
 */
public abstract class NucleoDAOBaseHibernate<T extends NucleoObjetoPersistenteImpl>
		extends HibernateDaoSupport implements NucleoDAOBase<T> {

	/**
	 * Método abstrato a ser implementado pelas subclasses para que retornem a
	 * classe de domínio que é persistida por elas.
	 * 
	 * @return Classe de domínio que é persistida pelas subclasses.
	 */
	protected abstract Class getClasseDominio();

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.ufes.inf.labes.util.persistencia.DAOBase#recuperarTodos()
	 */
	@SuppressWarnings("unchecked")
	public Collection<T> recuperarTodos() {
		// Usa o suporte do Spring para recuperar todos os objetos.
		// Encapsula dentro de um new LinkedHashSet para corrigir problema
		// de retorno de objetos repetidos.
		// O encapsulamento final dentro do ArrayList visa à melhoria de
		// performance da Collection.
		List<T> todosElementos = new ArrayList<T>(new LinkedHashSet<T>(
				getHibernateTemplate().loadAll(getClasseDominio())));
		Collections.sort(todosElementos);

		return todosElementos;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.ufes.inf.labes.util.persistencia.DAOBase#recuperarPorId(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public T recuperarPorId(Long id) {
		// Usa o suporte do Spring para recuperar o objeto pelo id.
		T objeto = null;
		try {
			objeto = (T) getHibernateTemplate().load(getClasseDominio(), id);

			// Verificar se o objeto é válido.
			objeto.toString();
		} catch (Exception e) {
			return null;
		}

		return objeto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.ufes.inf.labes.util.persistencia.DAOBase#salvar(java.lang.Object)
	 */
	public void salvar(T objeto) {
		// Usa o suporte do Spring para salvar o objeto.
		getHibernateTemplate().save(objeto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.ufes.inf.labes.util.persistencia.DAOBase#excluir(java.lang.Object)
	 */
	public void excluir(T objeto) {
		// Usa o suporte do Spring para excluir o objeto.
		getHibernateTemplate().delete(objeto);
	}

}
