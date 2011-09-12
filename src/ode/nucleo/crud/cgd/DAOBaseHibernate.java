package ode.nucleo.crud.cgd;

import java.io.Console;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.zkoss.util.logging.Log;

import ode.nucleo.cdp.ObjetoPersistente;

/**
 * 
 * @param <T>
 *            Tipo do objeto persistido.
 */

public class DAOBaseHibernate<T extends ObjetoPersistente> implements
		DAOBase<T> {

	@PersistenceContext
	EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ode.nucleo.crud.cgd.DAOBase#salvar(ode.nucleo.cdp.ObjetoPersistente)
	 */
	public void salvar(T objeto) {
		entityManager.persist(objeto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ode.nucleo.crud.cgd.DAOBase#atualizar(ode.nucleo.cdp.ObjetoPersistente)
	 */
	public void atualizar(T objeto) {
		entityManager.merge(objeto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ode.nucleo.crud.cgd.DAOBase#excluir(ode.nucleo.cdp.ObjetoPersistente)
	 */
	public void excluir(T objeto) {
		entityManager.remove(entityManager.find(this.getClasseDominio(),
				objeto.getId()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ode.nucleo.crud.cgd.DAOBase#recuperarTodos()
	 */
	public Collection<T> recuperarTodos() {
		return entityManager.createQuery("from " + getClasseDominio().getSimpleName()).getResultList();			
	}
	
	public Collection<T> recuperarTodosComOrdenacao(String orderBy) {
		return entityManager.createQuery("from " + getClasseDominio().getSimpleName() + " order by "+ orderBy).getResultList();			
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ode.nucleo.crud.cgd.DAOBase#recuperarPorId(java.lang.Long)
	 */
	public T recuperarPorId(Long id) {
		
		T objeto = null;
		objeto = (T) entityManager.find(getClasseDominio(), id);

		return objeto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ode.nucleo.crud.cgd.DAOBase#getClasseDominio()
	 */
	public Class<T> getClasseDominio() {

		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();

		return (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
