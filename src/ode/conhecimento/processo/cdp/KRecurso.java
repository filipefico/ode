package ode.conhecimento.processo.cdp;
import javax.persistence.Entity;

import ode.conhecimento.principal.cdp.Conhecimento;

@Entity
public class KRecurso extends Conhecimento
{
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