package ode.agenda.cdp;

import javax.persistence.ManyToOne;

import ode.alocacaoRecurso.cdp.AlocacaoRH;


public class Allocation_derivedAppointment extends Appointment {
	
	@ManyToOne
	private AlocacaoRH alocacaoRH;

	public AlocacaoRH getAlocacaoRH() {
		return alocacaoRH;
	}

	public void setAlocacaoRH(AlocacaoRH alocacaoRH) {
		this.alocacaoRH = alocacaoRH;
	}
	
	
	

}
