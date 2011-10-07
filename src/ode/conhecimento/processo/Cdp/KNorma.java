package ode.conhecimento.processo.cdp;

import javax.persistence.Entity;

/** Representa os conhecimentos sobre as Normas do ambiente ODE.
*/
@Entity
public class KNorma extends KDiretriz{

	private static final long serialVersionUID = 1851571389809546528L;

	/**Construtor.*/
    public KNorma() {
    }
    
    public String obterNomeCanonico() {
        return "Norma";
    }
    	
}
