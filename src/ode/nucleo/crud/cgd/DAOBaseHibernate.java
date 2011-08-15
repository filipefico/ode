package ode.nucleo.crud.cgd;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import ode.nucleo.cdp.ObjetoPersistente;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * 
 * @param <T>
 *            Tipo do objeto persistido.
 */

public class DAOBaseHibernate<T extends ObjetoPersistente>
implements DAOBase<T> {
	/**
     * Classe a ser tratada na sub-classe.
     */
    private Class<T> entityClass;

    private HibernateTemplate hibernateTemplate;

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

}
