package ode.utilitario.persistencia;

import ode.utilitario.excecao.ExcecaoRuntime;


public class ExcecaoPersistencia extends ExcecaoRuntime {
    
    /**
     * Construtor.
     * @param parMensagem Mensagem que poderia ser exibida para o usuário.
     * @param parCausa Exceção que causou o disparo desta.
     */
    public ExcecaoPersistencia(String parMensagem, Throwable parCausa) {
        super(parMensagem, parCausa);
    }
    
    /**
     * Construtor.
     */
    public ExcecaoPersistencia() {
        this(null, null);
    }
    
    /**
     * Construtor.
     * @param parMensagem Mensagem que poderia ser exibida para o usuário.
     */
    public ExcecaoPersistencia(String parMensagem) {
        this(parMensagem, null);
    }
    
    /**
     * Construtor.
     * @param parCausa Exceção que causou o disparo desta.
     */
    public ExcecaoPersistencia(Throwable parCausa) {
        this(null, parCausa);
    }
}
