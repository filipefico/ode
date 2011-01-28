package ode.conhecimento.processo.Cdp;

import javax.persistence.Entity;

/**************************** Classe KMetodo *********************************/
/** Representa os conhecimentos sobre Métodos do ambiente ODE.
 *
*/
@Entity
public class KMetodo extends KProcedimento{
	
	private static final long serialVersionUID = -3041477804694148466L;

	/** Construtor. */
    public KMetodo() {
    }
    
    public String obterNomeCanonico() {
        return "Método";
    }
}
