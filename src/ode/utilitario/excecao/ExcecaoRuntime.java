package ode.utilitario.excecao;

/**
 * Classe que representa uma exceção interna de ODE. Além disso, uma exceção
 * Runtime nào precisa ser checada em tempo de execução. A mensagem passada 
 * como argumento deve possuir um texto explicativo para o usuário.
 */
public class ExcecaoRuntime extends RuntimeException {
    
    /**
     * Construtor.
     * @param parMensagem Mensagem que poderia ser exibida para o usuário.
     * @param parCausa Exceção que causou o disparo desta.
     */
    public ExcecaoRuntime(String parMensagem, Throwable parCausa) {
        super(parMensagem, parCausa);
    }
    
    /**
     * Construtor.
     */
    public ExcecaoRuntime() {
        this(null, null);
    }
    
    /**
     * Construtor.
     * @param parMensagem Mensagem que poderia ser exibida para o usuário.
     */
    public ExcecaoRuntime(String parMensagem) {
        this(parMensagem, null);
    }
    
    /**
     * Construtor.
     * @param parCausa Exceção que causou o disparo desta.
     */
    public ExcecaoRuntime(Throwable parCausa) {
        this(null, parCausa);
    }
    
}
