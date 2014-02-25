package ode.agenda.cdp;

import java.util.Date;
import java.util.Set;


public class MultipleAppointment extends Appointment {
	
	private Date termina;

	Set<EventualAppointement> appointements;

	public Set<EventualAppointement> getAppointements() {
		return appointements;
	}

	public void setAppointements(Set<EventualAppointement> appointements) {
		this.appointements = appointements;
	}

	public Date getTermina() {
		return termina;
	}

	public void setTermina(Date termina) {
		this.termina = termina;
	}
}
