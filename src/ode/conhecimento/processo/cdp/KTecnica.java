package ode.conhecimento.processo.cdp;

import javax.persistence.Entity;

/*************************** Classe KTecnica *********************************/
/** Representa os conhecimentos sobre Técnicas do ambiente ODE.
 */

@Entity
public class KTecnica extends KProcedimento{

	private static final long serialVersionUID = 2569844792001387750L;

	/**Construtor.*/
    public KTecnica() {
    }
    
    public String obterNomeCanonico() {
        return "Técnica";
    }
    
}
