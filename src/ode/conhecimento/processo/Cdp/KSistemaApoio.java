package ode.conhecimento.processo.Cdp;

import javax.persistence.Entity;

/************************** Classe KSistemaApoio *****************************/
/** Representa os conhecimentos sobre os Sistemas de Apoio do ambiente ODE.
 * */

@Entity
public class KSistemaApoio extends KRecursoSoftware {

	private static final long serialVersionUID = -5448935193967252434L;

	/**Construtor.*/
    public KSistemaApoio() {
    }
    
    public String obterNomeCanonico() {
        return "Sistema de Apoio";
    }
}
