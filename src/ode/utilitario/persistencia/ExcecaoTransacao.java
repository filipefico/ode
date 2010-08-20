/*
 * ExcecaoTransacao.java
 *
 * Created on June 7, 2005, 3:40 PM
 */

package ode.utilitario.persistencia;

import ode.utilitario.persistencia.ExcecaoPersistencia;

/**
 *
 * @author geovando
 */
public class ExcecaoTransacao extends ExcecaoPersistencia {
    
    /**
     * Construtor.
     * @param parMensagem Mensagem que poderia ser exibida para o usuário.
     * @param parCausa Exceção que causou o disparo desta.
     */
    public ExcecaoTransacao(String parMensagem, Throwable parCausa) {
        super(parMensagem, parCausa);
    }
    
    /**
     * Construtor.
     */
    public ExcecaoTransacao() {
        this(null, null);
    }
    
    /**
     * Construtor.
     * @param parMensagem Mensagem que poderia ser exibida para o usuário.
     */
    public ExcecaoTransacao(String parMensagem) {
        this(parMensagem, null);
    }
    
    /**
     * Construtor.
     * @param parCausa Exceção que causou o disparo desta.
     */
    public ExcecaoTransacao(Throwable parCausa) {
        this(null, parCausa);
    }
    
}
