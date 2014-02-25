package ode.middlewareGoogle.calendar;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import ode.agenda.cdp.Allocation_derivedAppointment;
import ode.agenda.cdp.Appointment;
import ode.agenda.cdp.Owner;
import ode.alocacaoRecurso.cdp.AlocacaoRH;
import ode.alocacaoRecurso.cgd.AlocacaoRHDAO;
import ode.middlewareGoogle.autenticacao.AplCredenciais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Event.ExtendedProperties;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;


@Service
public class AplCalendarServiceManager {
	
	@Autowired
	private AplCredenciais credenciais;
	
	@Autowired
	private AlocacaoRHDAO alocacaoRHDAO;
	
	
	private Calendar criaServico(Owner owner){
		
		GoogleCredential googleCredenciais = credenciais.recuperaGoogleCredential(owner);
				
		Calendar servico = new Calendar
						.Builder(credenciais.getTRANSPORT(), credenciais.getJSON_FACTORY(), googleCredenciais)
						.setApplicationName(credenciais.getAPPLICATION_NAME())
						.build();
				
		return servico;
	}
	
	public void criarCalendarioODE(Owner owner) {
		
		try {
			
			Calendar servico = criaServico(owner);
			
			com.google.api.services.calendar.model.Calendar calEntry = new com.google.api.services.calendar.model.Calendar();
			calEntry.setSummary("ODE");
			calEntry.setDescription("Agenda destinada a compromissos do ambiente ODE");
			
			calEntry = servico.calendars().insert(calEntry).execute();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
	}
	
	public List<ode.agenda.cdp.Calendar> recuperarCalendarios(Owner owner){
		
		try {
			
			Calendar servico = criaServico(owner);
			
			CalendarList listaCalendarios = servico.calendarList().list().setFields("items(id,summary,description,location,timeZone,backgroundColor,accessRole, primary)").execute();
			
			List<ode.agenda.cdp.Calendar> listaCalendariosODE = new ArrayList<ode.agenda.cdp.Calendar>();
			
			for(CalendarListEntry calendarEntry: listaCalendarios.getItems()) {
				ode.agenda.cdp.Calendar novoCalendario = new ode.agenda.cdp.Calendar();
				
				
				novoCalendario.setId(calendarEntry.getId());
				novoCalendario.setTitulo(calendarEntry.getSummary());
				novoCalendario.setDescricao(calendarEntry.getDescription());
				novoCalendario.setLocalizacao(calendarEntry.getLocation());
				novoCalendario.setTimeZone(calendarEntry.getTimeZone());
				novoCalendario.setCorFundo(calendarEntry.getBackgroundColor());
				
				if(calendarEntry.getPrimary() != null) {
					novoCalendario.setPrimary(true);
				}else{
					novoCalendario.setPrimary(false);					
				}
				
				String acesso = calendarEntry.getAccessRole();
				
				if(acesso.equals("writer") || acesso.equals("owner")){
					novoCalendario.setReadOnly(false);
				}else{
					novoCalendario.setReadOnly(true);
				}
				
				listaCalendariosODE.add(novoCalendario);				
			}			

			return listaCalendariosODE;
			
		} catch (IOException e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	public List<ode.agenda.cdp.Calendar> recuperarCalendariosPropietarios(Owner owner){
		
		List<ode.agenda.cdp.Calendar> listaCalendariosODE = recuperarCalendarios(owner);
		
		Iterator<ode.agenda.cdp.Calendar> iterator = listaCalendariosODE.iterator();
		while(iterator.hasNext()) {
			ode.agenda.cdp.Calendar atual = iterator.next();
			
			if(atual.isReadOnly()){
				iterator.remove();
			}
		}
		
		return listaCalendariosODE;
	}
	
	public ode.agenda.cdp.Calendar reucperarCalendarioODE(Owner owner) {

		List<ode.agenda.cdp.Calendar> listaCalendariosODE = recuperarCalendarios(owner);
		
		
		Iterator<ode.agenda.cdp.Calendar> iterator = listaCalendariosODE.iterator();
		while(iterator.hasNext()) {
			ode.agenda.cdp.Calendar atual = iterator.next();
			
			if(atual.getTitulo().equals("ODE")){
				ode.agenda.cdp.Calendar novo = atual;
				return novo;
			}
		}
		
		return null;
	}
	
	
	public Appointment construirAppointment(Event event, String color, boolean isReadOnly, ode.agenda.cdp.Calendar calendar) {
		if(event.getStart() == null || event.getEnd() == null) {
			return null;
		}
		
		Appointment appointment = null;
		
		if(event.getExtendedProperties() != null){
			ExtendedProperties extendedProperties = event.getExtendedProperties();
			
			Map<String, String> map = extendedProperties.getPrivate();
						
			Allocation_derivedAppointment derivedAppointment = new Allocation_derivedAppointment();
			
			AlocacaoRH alocacaoRH = alocacaoRHDAO.recuperarPorId(Long.valueOf(map.get("AlocacaoRH")));
			
			derivedAppointment.setAlocacaoRH(alocacaoRH);
			
			appointment =  derivedAppointment;
			
		}else{
			appointment = new Appointment();
		}
		
		appointment.setId(event.getId());

		appointment.setContent(event.getSummary());
		appointment.setDescription(event.getDescription());
		
		appointment.setWhere(event.getLocation());
		appointment.setContentColor(color);
		appointment.setHeaderColor(color);
		appointment.setLocked(isReadOnly);
		appointment.setCalendar(calendar);
		
		
		java.util.Calendar ci = java.util.Calendar.getInstance();
		java.util.Calendar cf = java.util.Calendar.getInstance();
		
		SimpleDateFormat padraoDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat padraoDateTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
		
		if(event.getStart() != null) {
			try {
				ci.setTime(padraoDate.parse(event.getStart().getDate().toStringRfc3339()) );
				
				cf.setTime(padraoDate.parse(event.getEnd().getDate().toStringRfc3339()));
				
				appointment.setAllDay(true);
				appointment.setBeginDate(ci.getTime());
				appointment.setEndDate(cf.getTime());
				
			} catch (NullPointerException e) {
				// TODO: handle exception
				try {
					ci.setTime( padraoDateTime.parse(event.getStart().getDateTime().toStringRfc3339()) );
					
					cf.setTime( padraoDateTime.parse(event.getEnd().getDateTime().toStringRfc3339()) );
					
					appointment.setAllDay(false);
					appointment.setBeginDate(ci.getTime());
					appointment.setEndDate(cf.getTime());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return appointment;
	}
	
	
	public Event construirEvent(Appointment appointment) {
		
		Event event = new Event();
		
		event.setId(appointment.getId());
		event.setSummary(appointment.getContent());
		event.setDescription(appointment.getDescription());
		event.setLocation(appointment.getWhere());
		
		if(appointment.isAllDay()) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			String startDate = dateFormat.format(appointment.getBeginDate());			
			DateTime start = new DateTime(startDate);
			event.setStart(new EventDateTime().setDate(start));
			
			String endDate = dateFormat.format(appointment.getEndDate());
			DateTime end = new DateTime(endDate);
			event.setEnd(new EventDateTime().setDate(end));
			
		}else{
			DateTime start = new DateTime(appointment.getBeginDate());
			start = DateTime.parseRfc3339(start.toStringRfc3339());
			event.setStart( new EventDateTime().setDateTime(start) );
			
			DateTime end = new DateTime(appointment.getEndDate());
			end = DateTime.parseRfc3339(end.toStringRfc3339());
			event.setEnd( new EventDateTime().setDateTime(end) );
		}
		
		
		return event;
	}
	
	public Event construirEvent(AlocacaoRH alocacaoRH, String nomeProjeto) {
		
		Event event = new Event();
		
		event.setSummary(nomeProjeto + " - " + alocacaoRH.getAtividade().getNome());
		
		ExtendedProperties extendedProperties = new ExtendedProperties();
		
		Map<String, String> privateExtendedProperties = new HashMap<String, String>();
		privateExtendedProperties.put("AlocacaoRH", alocacaoRH.getId().toString());
		
		extendedProperties.setPrivate(privateExtendedProperties);		
		
		event.setExtendedProperties(extendedProperties);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String startDate = dateFormat.format(alocacaoRH.getDtInicioPrevisto());			
		DateTime start = new DateTime(startDate);
		event.setStart(new EventDateTime().setDate(start));
		
		String endDate = dateFormat.format(alocacaoRH.getDtFimPrevisto());
		DateTime end = new DateTime(endDate);
		event.setEnd(new EventDateTime().setDate(end));
		
		
		return event;
	}
	
	public Appointment inserirEvent(Owner owner, Appointment appointment){
		
		Appointment novoAppointment = null;
		
		Calendar servico = criaServico(owner);
		
		Event event = construirEvent(appointment);
		
		try {
			event = servico.events().insert(appointment.getCalendar().getId(), event).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		novoAppointment = construirAppointment(event, appointment.getContentColor(), appointment.isLocked(), appointment.getCalendar());
		return novoAppointment;
	}
	
	public Appointment inserirEvent(Owner owner, AlocacaoRH alocacaoRH, String nomeProjeto){
		
	
		Calendar servico = criaServico(owner);
		
		Event event = construirEvent(alocacaoRH, nomeProjeto);
		
		ode.agenda.cdp.Calendar calendar = reucperarCalendarioODE(owner);
		
		try {
			event = servico.events().insert(calendar.getId(), event).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public Appointment atualizarEvent(Owner owner, Appointment appointment){
		
		Calendar servico = criaServico(owner);
		
		Event event = construirEvent(appointment);
		
		try {
			event = servico.events().patch(appointment.getCalendar().getId(), event.getId(), event).execute();			
			
			Appointment appoint = construirAppointment(event, appointment.getContentColor(), appointment.isLocked(), appointment.getCalendar());
			
			return appoint;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void excluirEvent(Owner owner, Appointment appointment) {
		
		Calendar servico = criaServico(owner);
		
		Event event = construirEvent(appointment);
		
		try {
			servico.events().delete(appointment.getCalendar().getId(), event.getId()).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Appointment> recuperarAppointment(Owner owner, List<ode.agenda.cdp.Calendar> listaCalendario, Date dataInicio, Date dataFim, TimeZone zone){		
		
	//	try {
			
			DateTime dtInicio = new DateTime(dataInicio, zone);
			DateTime dtFim = new DateTime(dataFim, zone);
			List<Appointment> listaAppointment = new ArrayList<Appointment>();
			
			Calendar servico = criaServico(owner);
						
			//percore a lista de calendarios
			for(int i = 0; i < listaCalendario.size(); i++) {
				
				
				Events eventos;
				try {
					eventos = servico.events().list(listaCalendario.get(i).getId()).setTimeMin(dtInicio).setTimeMax(dtFim).setFields("items(etag, id, summary, start, end, description, location, recurrence, extendedProperties, recurringEventId)").execute();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					eventos = null; 
				}
				
				
				List<Event> items = eventos.getItems();
				for(Event evento : items) {
					//verifica se o evento e um evento recorrente
					if(evento.getRecurrence() != null){
						
						DateTime dateTime;
						if(evento.getStart().getDate() != null){
							//System.out.println("aqui");
							java.util.Calendar c = java.util.Calendar.getInstance();
							c.setTime(dataFim);
							c.setTimeInMillis(c.getTimeInMillis() - 86400000);
							
							dateTime = new DateTime(c.getTime());
						}else{
							dateTime = dtFim;
						}
						
						
						Events instancias;
						try {
							instancias = servico.events().instances(listaCalendario.get(i).getId(), evento.getId()).setTimeMin(dtInicio).setTimeMax(dateTime).setFields("items(etag, id, summary, start, end, description, location, recurrence, extendedProperties,recurringEventId)").execute();
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							instancias = null;
						}

						List<Event> itemsInstancias = instancias.getItems();
						
						for(Event instancia : itemsInstancias){
							Appointment appointment = construirAppointment(instancia, listaCalendario.get(i).getCorFundo(), listaCalendario.get(i).isReadOnly(), listaCalendario.get(i));
							if(appointment != null) {
								listaAppointment.add(appointment);
							}
						}
					}else{					
						
						if(evento.getRecurringEventId() == null) {
							
							Appointment appointment = construirAppointment(evento, listaCalendario.get(i).getCorFundo(), listaCalendario.get(i).isReadOnly(), listaCalendario.get(i));
							if(appointment != null) {
								listaAppointment.add(appointment);
							}
						}
					}
					
				}
			}
			
			return listaAppointment;
	}
	
}
