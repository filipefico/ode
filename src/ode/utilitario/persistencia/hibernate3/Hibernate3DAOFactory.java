/*
 * Hibernate3DAOFactory.java
 *
 */

package ode.utilitario.persistencia.hibernate3;
import configuracoes.PropriedadesConexao;
import ode.utilitario.excecao.ExcecaoRuntime;
import ode.utilitario.Log;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import ode.utilitario.persistencia.DAOFactory;
import ode.utilitario.persistencia.ExcecaoConcorrencia;
import ode.utilitario.persistencia.ExcecaoPersistencia;
import ode.utilitario.persistencia.ExcecaoTransacao;
import java.io.File;
import java.io.FileReader;
import java.sql.DriverManager;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
import org.hibernate.UnresolvableObjectException;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.util.JDBCExceptionReporter;

/**
 * Fábrica de DAO do hibernate 3.
 * @author snbuback
 */
public class Hibernate3DAOFactory extends DAOFactory {
    
    /** Log */
    private static Log log = Log.getLog(Hibernate3DAOFactory.class);
    
    /** Classe responsável por armazenar um contador de quantas transações estão abertas
     * e, se existirem transações abertas, qual a transação.
     */
    private static class TransactionCounter {
        public int openedTransactions = 0;
        public Transaction transaction;
    }
    
    /**
     * Importante: Somente os métodos openSession, closeSession e getSession
     * devem manipular diretamente este atributo.
     */
    private static Session fatSession;
    
    private static TransactionCounter fatTransactionCounter;
    
    private static LinkedList inactiveSessions = new LinkedList<Session>();
    
    /** Map contendo os DAOs já instânciados (singleton). */
    private final Map mapClass_DAO = new Hashtable(); // utilizar hashtable para suporte a concorrência.
    
    /** Instância do Hibernate em execução. */
    private static SessionFactory sessionFactory;
    
    
    /**
     * Construtor.
     */
    public Hibernate3DAOFactory() {
        try {
              log.addAppender("Persistencia.log");
            if (sessionFactory == null) {
                // Inicia o hibernate de acordo com as configurações do ODE
                //# Colocar as configurações do ODE, antes (PropriedadesODE)
                Configuration cfg = new Configuration();
                cfg.configure();
                System.out.println("DRIVER:"+cfg.getProperty("hibernate.connection.driver_class"));
                try{
                    PropriedadesConexao conexao = new PropriedadesConexao();
                    
                    String url = "jdbc:postgresql://"+conexao.getIp()+":"+conexao.getPorta()+"/"+conexao.getBaseDados();
                    
                    cfg.setProperty("hibernate.connection.url",url);
                    
                    System.out.println("ip: "+conexao.getIp());
                    System.out.println("porta: "+conexao.getPorta());
                    System.out.println("baseDados: "+conexao.getBaseDados());
                    
                    
                    //Verifica se o hibernate precisa criar as tabelas
                    try{
                        Connection connection;
                        //Tenta conectar com o banco
                        connection = DriverManager.getConnection("jdbc:postgresql://"+conexao.getIp()+":"+conexao.getPorta()+"/"+conexao.getBaseDados(), "ode","ode");
                        //Verifica se as tabelas criadas pelo hibernate já foram criadas. No caso, foi testada com a tabela rqst_requisito
                        connection.prepareStatement("select * from rqst_requisito").executeQuery();
                        //Fecha a conexão estabelecida para teste
                        connection.close();
                        //Diz ao hibernate que não precisa criar o esquema das tabelas
                        cfg.setProperty("hibernate.hbm2ddl.auto","");
                    }catch(Exception e){
                        System.out.println("Hibernate vai criar as tabelas ...");
                    }
                    
                    
                }catch(FileNotFoundException fnfe){
                    log.error("Não há arquivo de conexao com o banco!\nUtilizando configuração local.\n"+fnfe.getMessage(),fnfe);
                    System.out.println("FileNotFoundException - Não há arquivo de conexao com o banco!\nUtilizando configuração local...");
                }catch(IOException ioe){
                    log.error("Arquivo de conexao pode estar vazio!\n"+ioe.getMessage(),ioe);
                    //System.out.println("Arquivo de conexao pode estar vazio!");
                }
                
                //sessionFactory = new Configuration().configure().buildSessionFactory();
                //sessionFactory = cfg.configure().buildSessionFactory();
                
                sessionFactory = cfg.buildSessionFactory();
                
                //Configuration conf = new Configuration();
            }
        } catch (HibernateException e) {
            log.error("O hibernate não pode ser iniciado.\n"+ e.getMessage(),e);
            throw new ExcecaoPersistencia("O hibernate não pode ser iniciado.", e);
        }catch (Throwable ex){
            log.error(ex.getMessage(),ex);
           // ex.printStackTrace();
        }
    }
    
    /**
     * Retorna um DAO para a classe fornecida.
     */
    public Object getDAO(Class parClasse) {
        String locNomeClasseDAO = null;
        
        // Verifica os parametros.
        if (parClasse == null) {
            throw new NullPointerException("Classe não especificada.");
        }
        
        try {
            
            // Verifica se já existe uma instância deste DAO carregada.
            // Lembre-se: Os objetos DAO devem ser singleton, ou seja,
            // a fábrica deve garantir que há somente uma instância dele na memória.
            Object locDAO = mapClass_DAO.get(parClasse);
            if (locDAO != null) {
                return locDAO;
            }
            
            // O DAO ainda não foi carregado.
            // Obtém o nome da classe DAO através do nome da classe parClasse.
            String locNomeClasse = parClasse.getName();
            int locPosicaoPonto = locNomeClasse.lastIndexOf('.');
            if (locNomeClasse.substring(locPosicaoPonto-4, locPosicaoPonto+1).equals(".Cdp.")) {
                // Troca o pacote final ".Cdp." por ".Cgd." com o nome da classe adicionado do final Hib3
                locNomeClasseDAO = locNomeClasse.substring(0, locPosicaoPonto-4) + ".Cgd." +
                        locNomeClasse.substring(locPosicaoPonto+1) + "Hib3";
            } else if (locNomeClasse.substring(locPosicaoPonto-4, locPosicaoPonto+1).equals(".cdp.")) {
                // Troca o pacote final ".cdp." por ".cgd." com o nome da classe adicionado do final Hib3
                locNomeClasseDAO = locNomeClasse.substring(0, locPosicaoPonto-4) + ".cgd." +
                        locNomeClasse.substring(locPosicaoPonto+1) + "Hib3";
            } else if (locNomeClasse.substring(locPosicaoPonto-4, locPosicaoPonto+1).equals(".Cih.")) {
                // Troca o pacote final ".cdp." por ".cgd." com o nome da classe adicionado do final Hib3
                locNomeClasseDAO = locNomeClasse.substring(0, locPosicaoPonto-4) + ".Cgd." +
                        locNomeClasse.substring(locPosicaoPonto+1) + "Hib3";
            } else if (locNomeClasse.substring(locPosicaoPonto-4, locPosicaoPonto+1).equals(".cih.")) {
                // Troca o pacote final ".cdp." por ".cgd." com o nome da classe adicionado do final Hib3
                locNomeClasseDAO = locNomeClasse.substring(0, locPosicaoPonto-4) + ".cgd." +
                        locNomeClasse.substring(locPosicaoPonto+1) + "Hib3";
            } else {
                log.error("Somente classes de dominio possuem DAO. Verifique o nome do pacote.");
                throw new ExcecaoRuntime("Somente classes de dominio possuem DAO. Verifique o nome do pacote.");
            }
            
            // Obtém a classe a partir do nome
            Class locClasseDAO = Class.forName(locNomeClasseDAO);
            
            // Instância a classe.
            Object locObjetoDAO = locClasseDAO.newInstance();
            
            // Guarda a instância em mapClass_DAO para evitar mais de uma instanciação (obedecer padrão singleton).
            mapClass_DAO.put(parClasse, locObjetoDAO);
            return locObjetoDAO;
            
        } catch (ClassNotFoundException e) {
            log.error("A classe " + parClasse + " não possui DAO Hibernate de nome " + locNomeClasseDAO+".\n"+e.getMessage(),e);
            throw new ExcecaoRuntime("A classe " + parClasse + " não possui DAO Hibernate de nome " + locNomeClasseDAO, e);
        } catch (InstantiationException e) {
            log.error("Não foi possivel instânciar o DAO da classe " + parClasse +".\n"+e.getMessage(),e);
            throw new ExcecaoRuntime("Não foi possivel instânciar o DAO da classe " + parClasse, e);
        } catch (IllegalAccessException e) {
            log.error("Não foi possivel instânciar o DAO da classe " + parClasse +". \n"+e.getMessage(),e);
            throw new ExcecaoRuntime("Não foi possivel instânciar o DAO da classe " + parClasse, e);
        }
    }
    
    /**
     * Inicia uma transação.
     */
    public void beginTransaction() {
        try {
            TransactionCounter counter = fatTransactionCounter;
            if (counter.openedTransactions == 0) {
                // Guarda a transação iniciada (necessário para o commit ou rollback)
                counter.transaction = getSession().beginTransaction();
            }
            counter.openedTransactions++;
            
        }catch (StaleObjectStateException e) {
            log.error("Objeto alterado por outro usuario.\n"+e.getMessage(),e);
            //e.printStackTrace();
            throw new ExcecaoConcorrencia("Objeto alterado por outro usuario", e);
        }catch (HibernateException e) {
            log.error("Erro ao iniciar a transação.\n"+e.getMessage(),e);
            //e.printStackTrace();
            throw new ExcecaoPersistencia("Erro ao iniciar a transação.", e);
        }
    }
    
    /**
     * Grava fisicamente as alterações (commit).
     */
    public void commit(){
        try {
            TransactionCounter counter = fatTransactionCounter;
            if (counter.openedTransactions == 1) {
                counter.transaction.commit();
                counter.transaction = null;
            }
            counter.openedTransactions--;
        }catch(NonUniqueObjectException n){
            log.error("Objeto Desatualizado.\n"+n.getMessage(),n);
            throw new ExcecaoConcorrencia("Objeto Desatualizado", n);
        }catch (UnresolvableObjectException e) {
            log.error("Não será possível efetuar esta operação, pois outro usuário apagou esse registro.\n"+e.getMessage(),e);
            throw new ExcecaoConcorrencia("Não será possível efetuar esta operação, pois outro usuário apagou esse registro.", e);
        }catch (StaleObjectStateException e) {
            log.error("Não será possível efetuar esta operação, pois outro usuário modificou (ou apagou)  esse registro. Tente novamente.\n"+e.getMessage(),e);
            throw new ExcecaoConcorrencia("Não será possível efetuar esta operação, pois outro usuário modificou (ou apagou)  esse registro. Tente novamente.", e);
        }catch (ConstraintViolationException e) {
            log.error("Não será possível efetuar esta operação, pois existem dados dependentes do objeto alterado!\n"+e.getMessage(),e);
            throw new ExcecaoPersistencia("Não será possível efetuar esta operação, pois existem dados dependentes do objeto alterado!", e);
        }catch (HibernateException e) {
            log.error("Erro ao gravar fisicamente os dados.\n"+e.getMessage(),e);
            //e.printStackTrace();
            throw new ExcecaoTransacao("Erro ao gravar fisicamente os dados.", e);
        } finally {
            //closeSession(); // não fecha a sessão. Sempre 1 sessão na memória
        }
    }
    
    /**
     * Desfaz as alterações (rollback).
     */
    public void rollback() {
        TransactionCounter counter = fatTransactionCounter;
        try {
            if (counter.openedTransactions == 1) {
                counter.transaction.rollback();
                counter.transaction = null;
            }
            counter.openedTransactions--;
        } catch (HibernateException e) {
            log.error("Erro durante o rollback.\n"+e.getMessage(),e);
            throw new ExcecaoPersistencia("Erro durante o rollback.", e);
        } finally {
            closeSession();
        }
    }
    
    /**
     * Abre uma sessão.
     */
    private static void openSession() throws ExcecaoPersistencia {
        if (fatSession != null) {
            closeSession(); // Fecha a sessão aberta
        }
        
        try {
            fatSession = sessionFactory.openSession();
            fatSession.setCacheMode(CacheMode.REFRESH);
            fatSession.setFlushMode(FlushMode.AUTO);
            
            fatTransactionCounter = new TransactionCounter();
        } catch (HibernateException e) {
             log.error("Erro ao abrir uma sessão.\n"+e.getMessage(),e);
            throw new ExcecaoPersistencia("Erro ao abrir uma sessão", e);
        }
    }
    
    
    /**
     * Retorna a sessão aberta.
     * A chamada deste método deve ser rápida, pois ele é acessado muitas vezes.
     * @return Interface Session do hibernate.
     */
    public static Session getSession() throws ExcecaoPersistencia {
        
        if (fatSession == null) {
            openSession();
        }
        
        fatSession.flush();
        
        /*if (!fatSession.isConnected()) {
            // Testa se a conexão foi encerrada (por exemplo queda de banco ou timeout.
            // Caso o método isConnected não esteja apresentando problemas, pode-se obter
            // a conexão diretamente em fatSession.connection() e verificar se ela está ativa;
            fatSession.reconnect();
        }*/
        
        return fatSession;
    }
    
    /**
     * Fecha a sessão atual.
     */
    public static void closeSession() throws ExcecaoPersistencia {
        // Imprime a quantidade de memória do sistema
       // log.info("Memória MAX: " + (Runtime.getRuntime().maxMemory()/1024) + "kb");
       // log.info("Memória TOTAL: " + (Runtime.getRuntime().totalMemory()/1024) + "kb");
       // log.info("Memória LIVRE: " + (Runtime.getRuntime().freeMemory()/1024) + "kb");
       // log.info("Memória USADA: " + ((Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())/1024) + "kb");
        
        /*fatSession.setCacheMode(CacheMode.REFRESH); // Como padrão a sessão sempre atualiza dos dados do cache
        //fatSession.setFlushMode(FlushMode.ALWAYS);
        inactiveSessions.addFirst(fatSession);
         */
        fatSession.setCacheMode(CacheMode.GET);
        fatSession.setFlushMode(FlushMode.NEVER);
        //fatSession.clear();
        System.out.println("-----------------------------");
        //fatSession.disconnect(); // a sessão não é fechada.
        fatSession.close();
        fatSession = null;
        
        //fatSession = null;
        
        // Se a fila de sessões inativas estiver cheia, fecha as conexões de banco, para liberar o pool
        /*if (inactiveSessions.size() > MAX_INACTIVE_SESSIONS) {
            ((Session) inactiveSessions.removeLast()).diclose();
        }*/
    }
}
