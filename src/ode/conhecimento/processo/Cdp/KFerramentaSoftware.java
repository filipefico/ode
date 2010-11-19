package ode.conhecimento.processo.Cdp;

import javax.persistence.Entity;


@Entity
public class KFerramentaSoftware  extends KRecurso{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.getNome();
	}
}
