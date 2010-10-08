package ode.conhecimento.processo.Cdp;

import javax.persistence.Entity;
import ode.conhecimento.principal.Cdp.Conhecimento;

@Entity
public class KFerramentaSoftware  extends Conhecimento{	
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
