package ode.conhecimento.processo.Cdp;

import javax.persistence.Entity;

@Entity
public class KFerramentaSoftware extends KRecurso {	
	
	private static final long serialVersionUID = 1L;
	
	/** Tipo do Conhecimento Ferramenta Software. */
    //private String tipo;
        
    /** Construtor. */
    public KFerramentaSoftware() {
    }
    

    public String obterNomeCanonico() {
        return "Ferramenta de Software";
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.getNome();
	}
    
}
