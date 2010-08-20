/*
 * ExcecaoConcorrencia.java
 *
 * Created on 11 de Agosto de 2005, 01:11
 *
 */

package ode.utilitario.persistencia;



/**
 * Esta exceção ocorre quando ocorre acessos concorrentes e conflitantes durante
 * uma transação (concorrência de escrita ao mesmo dado do banco de dados).
 */
public class ExcecaoConcorrencia extends ExcecaoTransacao {
    
    /**
     * Construtor.
     * @param parMensagem Mensagem que poderia ser exibida para o usuário.
     * @param parCausa Exceção que causou o disparo desta.
     */
    public ExcecaoConcorrencia(String parMensagem, Throwable parCausa) {
        super(parMensagem, parCausa);
    }
    
    /**
     * Construtor.
     */
    public ExcecaoConcorrencia() {
        this(null, null);
    }
    
    /**
     * Construtor.
     * @param parMensagem Mensagem que poderia ser exibida para o usuário.
     */
    public ExcecaoConcorrencia(String parMensagem) {
        this(parMensagem, null);
    }
    
    /**
     * Construtor.
     * @param parCausa Exceção que causou o disparo desta.
     */
    public ExcecaoConcorrencia(Throwable parCausa) {
        this(null, parCausa);
    }
    
}
