package ode.conhecimento.processo.cdp;

import javax.persistence.Entity;

/************************* Classe KRecursoHardware ***************************/
/** Representa os conhecimentos sobre Recursos de Hardware do ambiente ODE.
 */

@Entity
public class KRecursoHardware extends KRecurso{
	
	private static final long serialVersionUID = -8384171140587322793L;

	/**Construtor.*/
    public KRecursoHardware() {
    }
    
    public String obterNomeCanonico() {
        return "Recurso de Hardware";
    }
}
