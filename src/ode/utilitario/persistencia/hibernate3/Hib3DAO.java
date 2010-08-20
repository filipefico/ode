/*
 * Sombra.java
 *
 * Created on June 3, 2005, 3:15 PM
 */

package ode.utilitario.persistencia.hibernate3;

import ode.utilitario.Log;
import ode.utilitario.persistencia.ExcecaoConcorrencia;
import ode.utilitario.persistencia.ExcecaoPersistencia;
import java.io.File;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;

import org.hibernate.UnresolvableObjectException;
import org.hibernate.exception.ConstraintViolationException;

/**
 * Classe Hib3DAO.
 * Implementa os métodos básicas de salvar, excluir e pesquisar por ID.
 * @author geovando
 */
public abstract class Hib3DAO {
    
    private static final Log log = Log.getLog(Hib3DAO.class);
    
    /**
     * Retorna uma sessão.
     */
    protected Session getSession(){
        log.addAppender("Persistencia.log");
        return Hibernate3DAOFactory.getSession();
    }
    
    /**
     * Persiste o objeto especificado.
     */
    protected void salvar(Object parObjeto){
        try{
            // log.info("Salvando Objeto : "+ parObjeto.getClass() + parObjeto.toString());
            getSession().saveOrUpdate(parObjeto);
            //getSession().flush();
        }catch(NonUniqueObjectException n){
            log.error("Salvando Objeto : "+ parObjeto.getClass() + parObjeto.toString());
            log.error(n.getMessage(),n);
            throw new ExcecaoConcorrencia("Objeto Desatualizado", n);
        }catch(UnresolvableObjectException e){
            log.error("Salvando Objeto : "+ parObjeto.getClass() + parObjeto.toString());
            log.error(e.getMessage(),e);
            throw new ExcecaoConcorrencia("Objeto deletado por outro usuario", e);
        }catch(StaleObjectStateException e){
            log.error("Salvando Objeto : "+ parObjeto.getClass() + parObjeto.toString());
            log.error(e.getMessage(),e);
            throw new ExcecaoConcorrencia("Objeto alterado por outro usuario", e);
        }catch (HibernateException e) {
            log.error("Salvando Objeto : "+ parObjeto.getClass() + parObjeto.toString());
            log.error(e.getMessage(),e);
            //e.printStackTrace();
            throw new ExcecaoPersistencia("Erro ao salvar objeto", e);
        }
    }
    
    /**
     * Excluir o objeto especificado. Deve ser utilizada apenas para
     * objetos que já tenham sido persistidos.
     */
    protected void excluir(Object parObjeto){
        try {
            // log.info("Excluindo Objeto : "+ parObjeto.getClass() + parObjeto.toString());
            //getSession().refresh(parObjeto);
            getSession().delete(parObjeto);
        }catch(UnresolvableObjectException e){
            log.error("Excluindo Objeto : "+ parObjeto.getClass() + parObjeto.toString());
            log.error(e.getMessage(),e);
            throw new ExcecaoConcorrencia("Objeto deletado por outro usuário!", e);
        }catch(StaleObjectStateException e){
            log.error("Excluindo Objeto : "+ parObjeto.getClass() + parObjeto.toString());
            log.error(e.getMessage(),e);
            throw new ExcecaoConcorrencia("Objeto deletado por outro usuário!", e);
        }catch (ConstraintViolationException e) {
            log.error("Excluindo Objeto : "+ parObjeto.getClass() + parObjeto.toString());
            log.error(e.getMessage(),e);
            //System.out.println("!!!!!"+getSession().createQuery("select "+e.getConstraintName()+" from " + parObjeto.getClass().getName()).list());
            throw new ExcecaoPersistencia("Não será possível efetuar esta operação, pois existem dados dependentes do objeto alterado!", e);
        }catch (HibernateException e) {
            // e.printStackTrace();
            log.error(e.getMessage(),e);
            throw new ExcecaoPersistencia("Erro ao excluir o objeto.", e);
            
        }
    }
    
    /**
     * Retorna o objeto da classe especificada que possui o ID fornecido.
     */
    protected Object obterPorID(Class parClasse, String parID) {
        try {
            // log.info("Obtendo objeto da classe "+ parClasse + " com ID ="+ parID +".");
            Object o = getSession().get(parClasse, parID);
            
            // Sincroniza o objeto com o banco
            getSession().refresh(o);
            return o;
        }catch (HibernateException e) {
            log.error("Obtendo objeto da classe "+ parClasse + " com ID ="+ parID +".");
            log.error(e.getMessage(),e);
            //  e.printStackTrace();
            throw new ExcecaoPersistencia("Erro ao buscar classe " + parClasse + " pelo ID " + parID, e);
        }
    }
    
    /**
     * Retorna uma lista com todos os objetos da classe especificada.
     */
    protected List obterTodos(Class parClasse) {
        try {
            // log.info("Obtendo todos os objetos da classe "+ parClasse + ".");
            List objetos = getSession().createQuery("from " + parClasse.getName()).list();
            for (int i=0; i<objetos.size(); i++) {
                // sincroniza o objeto com o banco.
                getSession().refresh(objetos.get(i));
            }
            return objetos;
        }catch(StaleObjectStateException e){
            log.error("Obtendo todos os objetos da classe "+ parClasse + ".");
            log.error(e.getMessage(),e);
            throw new ExcecaoConcorrencia("Erro ao consultar!", e);
        }catch (HibernateException e) {
            log.error("Obtendo todos os objetos da classe "+ parClasse + ".");
            log.error(e.getMessage(),e);
            // e.printStackTrace();
            throw new ExcecaoPersistencia("Erro consultar lista de todos os objetos da classe " + parClasse, e);
        }
    }
}
