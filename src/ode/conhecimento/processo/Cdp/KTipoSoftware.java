package ode.conhecimento.processo.Cdp;
import ode.conhecimento.principal.Cdp.Conhecimento;



/****************** Classe KTipoSoftware *************************/


/** Representa os conhecimentos sobre os Tipos de Software do
 * ambiente ODE
 *
 *@hibernate.joined-subclass
 *      table = "conh_proc_ktiposoftware"
 *@hibernate.joined-subclass-key
 *      column = "id"
 */

public class KTipoSoftware extends Conhecimento {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -8750817165681213347L;

/**Construtor.*/
  public KTipoSoftware() {
  }
    
}