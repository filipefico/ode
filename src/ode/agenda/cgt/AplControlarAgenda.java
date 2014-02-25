package ode.agenda.cgt;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import ode.agenda.cdp.Appointment;
import ode.agenda.cdp.Calendar;
import ode.agenda.cdp.Owner;
import ode.middlewareGoogle.calendar.AplCalendarServiceManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AplControlarAgenda {
	
	@Autowired
	private AplCalendarServiceManager aplCalendarServiceManager;


	public boolean existeCalendarioODE(Owner owner) {
		
		List<Calendar> listaCalendarios = recuperarCalendarios(owner);
		
		if(listaCalendarios == null) return false;
		
		for(int i = 0; i < listaCalendarios.size(); i++) {
			if(listaCalendarios.get(i).getTitulo().equals("ODE")){
				return true;
			}
		}
		return false;
	}
	
	public Appointment inserirAppointment(Owner owner, Appointment appointment) {
		return aplCalendarServiceManager.inserirEvent(owner, appointment);
	}
	
	public Appointment atualizarAppointment(Owner owner, Appointment appointment){
		return aplCalendarServiceManager.atualizarEvent(owner, appointment);
	}
	
	public void excluirAppointment(Owner owner, Appointment appointment){
		aplCalendarServiceManager.excluirEvent(owner, appointment);
	}
	
	public List<ode.agenda.cdp.Calendar> recuperarCalendariosPropietarios(Owner owner){
		return aplCalendarServiceManager.recuperarCalendariosPropietarios(owner);
	}
	
	public List<Appointment> recuperarAppointment(Owner owner, List<Calendar> listaCalendario, Date dataInicio, Date dataFim, TimeZone zone){
		
		return aplCalendarServiceManager.recuperarAppointment(owner, listaCalendario, dataInicio, dataFim, zone);
	}
	
	public List<Calendar> recuperarCalendarios(Owner owner) {
		return aplCalendarServiceManager.recuperarCalendarios(owner);
	}
	
	public void criarCalendarioODE(Owner owner){
		aplCalendarServiceManager.criarCalendarioODE(owner);
	}
}
