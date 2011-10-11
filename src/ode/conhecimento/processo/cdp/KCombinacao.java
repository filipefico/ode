package ode.conhecimento.processo.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;

import ode.conhecimento.principal.cdp.Conhecimento;

/** Representa os conhecimentos sobre as Combinacoes do ambiente ODE.
**/

@Entity
public class KCombinacao extends Conhecimento {


	private static final long serialVersionUID = -5966545778651738935L;
	/** Tipos de Combinações. */
    public static final String SEQUENCIAL = "Sequencial";
    public static final String ITERATIVA = "Iterativa";
    
    /** Tipo de combinacao: sequencial ou iterativa. */
    private String tipo;
    
    /** Creates a new instance of KCombinacao */
    public KCombinacao() {
    }
    
    
    public KCombinacao(String tipo) {
        this.tipo = tipo;
    }
    
    
    /** Obtém o tipo da Combinação. 
     */
    @Column(length=30)
    public String getTipo() {
        return tipo;
    }
    
    /** Atribui o tipo da Combincação. */
    public void setTipo(String parTipo) {
        tipo = parTipo;
    }
    
    public String toString(){
        return this.tipo;
    }
    
}
