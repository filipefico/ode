/*
 * DAOFactory.java
 *
 */

package ode.utilitario.persistencia;

import ode.utilitario.persistencia.hibernate3.Hibernate3DAOFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Classe DAOFactory. Responsável por fornecer fábrica de objetos DAO 
 *(objetos responsável pela persistência).
 *
 * Todas as fábricas devem ser inicializadas estaticamente no inicio do sistema, e
 * não devem ser mais instânciadas, ou seja, as fábricas devem ser classes singleton
 * (possuem uma única instãncia durante a execução do sistema).
 * É fundamental que as implementações suportem concorrência (synchronized)
 *
 */
public abstract class DAOFactory {
    // Declara todas as fábricas.
    public static final int HIBERNATE3_FACTORY = 1;
    
    // Declara a fábrica padrão (utilizada quando nenhuma outra for especificada).
    public static final int DEFAULT_FACTORY = HIBERNATE3_FACTORY;
    
    /* As fábricas são objetos do tipo singleton */
    private static DAOFactory hibernate3DAO = new Hibernate3DAOFactory();
    //private static DAOFactory XXXDAO = new XXXDAOFactory(); // instãncia todas as fábricas.
    
    /**
     * Cria uma fábrica de DAOs de acordo com o tipo.
     */
    public static DAOFactory getDAOFactory(int parFactory) {
        
        switch (parFactory) {
            // Fábrica Hibernate 3
            case HIBERNATE3_FACTORY:
                return hibernate3DAO;

            // Parametro inválido.
            default:
                return null;
        }
    }
    
    /**
     * Cria a fábrica padrão de DAO (atualmente HIBERNATE3_FACTORY).
     */
    public static DAOFactory getDAOFactory() {
        return getDAOFactory(DEFAULT_FACTORY);
    }
    
    /**
     * Retorna um DAO para a classe fornecida, utilizando a fábrica padrão.
     */
    public static Object getDefaultDAO(Class parClasse) {
         return getDAOFactory().getDAO(parClasse);
    }
    
    //////// Os métodos abaixo devem ser implementados por todas as fábricas. ///////
    /**
     * Retorna um DAO para a classe fornecida.
     */
    public abstract Object getDAO(Class parClasse);
    
    /**
     * Inicia uma transação.
     */
    public abstract void beginTransaction();
    
    /**
     * Grava fisicamente as alterações (commit).
     */
    public abstract void commit();
    
    /**
     * Desfaz as alterações (rollback).
     */
    public abstract void rollback();

}
