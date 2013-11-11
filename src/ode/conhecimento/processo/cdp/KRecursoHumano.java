package ode.conhecimento.processo.cdp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

/*********************** Classe KRecursoHumano *******************************/
/** Representa os conhecimentos sobre os Recursos Humanos do ambiente ODE.
 */
@Entity
public class KRecursoHumano extends KRecurso{
	
	private static final long serialVersionUID = -6378924545320164789L;

	/**Construtor.*/
    public KRecursoHumano() {
    }
    
    public String obterNomeCanonico() {
        return "Recurso Humano";
    }
    
}
