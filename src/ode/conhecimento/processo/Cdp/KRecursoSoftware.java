package ode.conhecimento.processo.Cdp;
import ode.conhecimento.processo.Cdp.KRecurso;

/************************ Classe KRecursoSoftware ****************************/
/** Representa os conhecimentos sobre Recursos de Software do ambiente ODE. 
/**
 * @hibernate.joined-subclass
 *      table = "conh_proc_krecursosoftware"
 * @hibernate.joined-subclass-key
 *      column = "id"
 */
public abstract class KRecursoSoftware extends KRecurso {
    
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**Construtor.*/
  public KRecursoSoftware() {
  }
    
}