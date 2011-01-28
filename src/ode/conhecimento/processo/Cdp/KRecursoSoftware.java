package ode.conhecimento.processo.Cdp;
import javax.persistence.Entity;

import ode.conhecimento.processo.Cdp.KRecurso;

/************************ Classe KRecursoSoftware ****************************/
/** Representa os conhecimentos sobre Recursos de Software do ambiente ODE. 
*/
@Entity
public abstract class KRecursoSoftware extends KRecurso {
    
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**Construtor.*/
  public KRecursoSoftware() {
  }
    
}