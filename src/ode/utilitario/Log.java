/*
 * Log.java
 *
 * Created on March 9, 2006, 2:45 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ode.utilitario;

import java.io.File;
import java.io.IOException;
import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.DateLayout;
import org.apache.log4j.spi.LoggingEvent;



/**
 * Classe responsável pelos logs (apenas encapsula o Log4j)
 * @author snbuback
 */
public final class Log {
    
    private final Logger logger;
    
    /** Creates a new instance of Log */
    private Log(Class clazz) {
        this.logger = Logger.getLogger(clazz);
        this.configure(clazz);
    }
    
    private void configure(Class clazz){
    // Adicionar ao appender Aplicacao
    //addAppender(clazz.getName()+".log");
    //addAppender();
    
    
    }
    
    public void addAppender(String nomeArquivo) {
        
        Appender fileAppender=null;      
        
        PatternLayout lay =  new PatternLayout();
        
        //System.out.println(lay.getConversionPattern());
        String patern = "%d Classe [  %c ] %n%p - %m%n%n";
        lay.setConversionPattern(patern);
        // System.out.println(lay.getConversionPattern());
        
        try {
            //fileAppender = new FileAppender(new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN), nomeArquivo);
             fileAppender = new FileAppender(lay,"log"+File.separator+nomeArquivo);
            
           
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        logger.addAppender(fileAppender);
        
    }
    
    public static Log getLog(Class clazz) {
        return new Log(clazz);
        
    }
    
    public static Log getLogFerramenta(Class clazz) {
        return new Log(clazz);
        
    }
    
    public void debug(Object m) {
        logger.debug(m);
    }
    
    public void debug(Object m, Throwable t) {
        logger.debug(m, t);
    }
    
    public void info(Object m) {
        logger.info(m);
    }
    
    public void info(Object m, Throwable t) {
        logger.info(m, t);
    }
    
    public void warn(Object m) {
        logger.warn(m);
    }
    
    public void warn(Object m, Throwable t) {
        logger.warn(m, t);
    }
    
    public void error(Object m) {
        logger.error(m);
    }
    
    public void error(Object m, Throwable t) {
        logger.error(m, t);
    }
    
    public void fatal(Object m) {
        logger.fatal(m);
    }
    
    public void fatal(Object m, Throwable t) {
        logger.fatal(m, t);
    }
}
