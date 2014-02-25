package ode.agenda.cdp;

import java.util.ArrayList;
import java.util.List;



public class Agenda{

	
	private List<Calendar> calendarios = new ArrayList<Calendar>();
	
	private List<Calendar> calProprietarios = new ArrayList<Calendar>();
	
	private Owner owner;	
	
	

	public List<Calendar> getCalendarios() {
		return calendarios;
	}

	public void setCalendarios(List<Calendar> calendarios) {
		this.calendarios = calendarios;
	}

	public List<Calendar> getCalProprietarios() {
		return calProprietarios;
	}

	public void setCalProprietarios(List<Calendar> calProprietarios) {
		this.calProprietarios = calProprietarios;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

}
