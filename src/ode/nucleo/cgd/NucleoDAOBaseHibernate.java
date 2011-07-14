package ode.nucleo.cgd;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * 
 * @param <T>
 *            Tipo do objeto persistido.
 */

public class NucleoDAOBaseHibernate<T extends NucleoObjetoPersistenteImpl>
implements NucleoDAOBase<T> {

	/**
	 * Classe a ser tratada na sub-classe.
	 */
	private Class<T> entityClass;

	private HibernateTemplate hibernateTemplate;

	public NucleoDAOBaseHibernate() {
		super();
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public HibernateTemplate getHibernateTemplate(){
		return this.hibernateTemplate;
	}

	/**
	 * Recupera a classe de domínio que é persistida.
	 * 
	 * @return Classe de domínio que é persistida pelas subclasses.
	 */

	public Class<T> getClasseDominio() {

		ParameterizedType parameterizedType = (ParameterizedType) getClass()
		.getGenericSuperclass();

		return (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

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
		// O encapsulamento final dentro do ArrayList visa ï¿½ melhoria de
		// performance da Collection.
		List<T> todosElementos = new ArrayList<T>(new LinkedHashSet<T>(
				getHibernateTemplate().loadAll(getClasseDominio())));
		// Collections.sort(todosElementos);

		return todosElementos;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.ufes.inf.labes.util.persistencia.DAOBase#recuperarPorId(java.lang.
	 * Long)
	 */
	@SuppressWarnings("unchecked")
	public T recuperarPorId(Long id) {
		// Usa o suporte do Spring para recuperar o objeto pelo id.
		T objeto = null;
		try {
			objeto = (T) getHibernateTemplate().load(getClasseDominio(), id);

			// Verificar se o objeto ï¿½ vï¿½lido.
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

	public void merge(T objeto) {
		getHibernateTemplate().merge(objeto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.ufes.inf.labes.util.persistencia.DAOBase#excluir(java.lang.Object)
	 */
	public void excluir(T objeto) {
		// Usa o suporte do Spring para excluir o objeto.
		getHibernateTemplate().delete(objeto);
	}

	@SuppressWarnings("unchecked")
	public Collection<T> recuperarTodosPaginado(List<Criterion> criterios) {

		if (criterios != null) {
			DetachedCriteria d = DetachedCriteria.forClass(getClasseDominio());

			for (Criterion criterion : criterios) {
				d.add(criterion);
			}
			return getHibernateTemplate().findByCriteria(d);
		} else
			return recuperarTodos();

	}

	public Collection<T> recuperarTodosPaginado(ObjetoPagina pagina) {

		DetachedCriteria detaCriteria = getDetachedCriteria(pagina);
		Collection<T> result = null;
		if (pagina.isPaginada()) {
			result = getHibernateTemplate().findByCriteria(detaCriteria,
					pagina.getFirstResults(), pagina.getMaxResults());

		} else {
			result = getHibernateTemplate().findByCriteria(detaCriteria);
		}

		return result;

	}

	public int recuperarQteTodos(ObjetoPagina pagina) {
		DetachedCriteria detaCriteria = getDetachedCriteria(pagina);
		Collection<T> result = getHibernateTemplate().findByCriteria(
				detaCriteria);
		return result.size();
	}

	public DetachedCriteria getDetachedCriteria(ObjetoPagina parPagina) {

		DetachedCriteria detaCriteria = DetachedCriteria
		.forClass(getClasseDominio());
		List<Criterion> criterios = parPagina.getCriterios();
		// adiciono os criterios da pesquisa
		if (criterios != null) {
			for (Criterion criterion : criterios) {
				detaCriteria.add(criterion);
			}
		}
		if (parPagina.getCriterioOrdenacao() != null)
			detaCriteria.addOrder(parPagina.getCriterioOrdenacao());

		return detaCriteria;

	}

}
