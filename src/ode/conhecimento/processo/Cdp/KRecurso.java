package ode.conhecimento.processo.Cdp;
import javax.persistence.Entity;

import ode.conhecimento.principal.Cdp.Conhecimento;

@Entity
public class KRecurso extends Conhecimento {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public KRecurso() {
    }
	    
    public String obterNomeCanonico() {
        return "Recurso";
    }
}