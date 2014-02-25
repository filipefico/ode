package ode.agenda.ciu;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import ode._infraestruturaBase.util.NucleoContexto;
import ode.agenda.cdp.Agenda;
import ode.agenda.cdp.Appointment;

import org.zkoss.calendar.Calendars;
import org.zkoss.calendar.event.CalendarsEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;


public class JanPrincipal extends Window {

	private static final long serialVersionUID = 1L;
	
	private Calendars zkCalendar;
	
	private CtrlAgenda ctrlAgenda;
	private JanPrincipal jp;
	
	private Agenda agenda;
	
	private Button btDia;
	private Button btSemana;
	private Button btMes;
	
	private boolean autenticado;


	
	public JanPrincipal(CtrlAgenda ctrl){
		super();
		
		this.setWidth("100%");
		this.setHeight("90%");
		this.setPosition("center,bottom");
		this.setShadow(false);
		
		this.setParent(NucleoContexto.recuperarJanelaPrincipal());
		
		this.ctrlAgenda = ctrl;	
		this.jp = this;
		this.autenticado = ctrlAgenda.verificaAutorizacaoGoogleRHAtual();

		this.agenda = ctrlAgenda.recuperarAgenda();
		

		
		/*Hbox hbox = new Hbox();
		hbox.setWidth("100%");
		hbox.setHeight("100%");
		
		Vbox vbox = new Vbox();
		
			org.zkoss.zul.Calendar cal = new org.zkoss.zul.Calendar();
			cal.setParent(vbox);
		
		vbox.setParent(hbox);*/
		
		
		Div geral = new Div();
		geral.setStyle("height: 100%; margin:0px; padding:0px;");
					//setStyle("background-color:#000;");
			zkCalendar = new Calendars();
			zkCalendar.setId("zkCalendar");
			zkCalendar.setFirstDayOfWeek(1);
			zkCalendar.setHeight("100%");
			//zkCalendar.setStyle("height: 100%");
			
			Toolbar toolbar = new Toolbar();
			toolbar.setStyle("padding-left: 45px; padding-top: 14px; padding-bottom: 5px;");
			
			zkCalendar.getBeginDate();									
			
				//cria um novo compromisso
				Button btCriar = new Button("Criar Evento");
				btCriar.setStyle("margin-right: 50px;");
					btCriar.addEventListener("onClick", new EventListener() {
						
						@Override
						public void onEvent(Event arg0) throws Exception {
							// TODO Auto-generated method stub
							//onClose();
							Calendar c = Calendar.getInstance();
							
							//passou de meia hora
							if(c.get(Calendar.MINUTE) - 30 > 0) {
								c.set(Calendar.MINUTE, 0);
								c.set(Calendar.HOUR, c.get(Calendar.HOUR) + 1);
								c.set(Calendar.SECOND, 0);
							}else{
								c.set(Calendar.MINUTE, 30);
								c.set(Calendar.SECOND, 0);
							}
							
							Appointment appointment = new Appointment();
							appointment.setBeginDate(c.getTime());
							c.set(Calendar.HOUR, c.get(Calendar.HOUR) + 1);
							appointment.setEndDate(c.getTime());
							
							appointment.setContent("Compromisso sem título");
							
							ctrlAgenda.abrirJanelaAppointement(jp, appointment, agenda.getCalProprietarios(), EnumCRUD.Criar);
							
						}
					});
				btCriar.setParent(toolbar);
				
				Button btAnterior = new Button("<< Ante");
				btAnterior.addEventListener("onClick", new EventListener() {
					
					@Override
					public void onEvent(Event arg0) throws Exception {
						// TODO Auto-generated method stub
						zkCalendar.previousPage();
						
						if(autenticado) {
							//insere os compromissos
							List<Appointment> listaAppointments = ctrlAgenda.recuperarAppointment(zkCalendar.getBeginDate(), zkCalendar.getEndDate(), zkCalendar.getDefaultTimeZone(), agenda.getCalendarios());
							if(listaAppointments != null) {
								insereAppointmesnts(listaAppointments);
							}
							}
					}
				});
				btAnterior.setParent(toolbar);
				
				//Contrle da posicao do calendario
				Button btHoje = new Button("Hoje");
				btHoje.addEventListener("onClick", new EventListener() {
					
					@Override
					public void onEvent(Event arg0) throws Exception {
						// TODO Auto-generated method stub
						TimeZone timeZone = zkCalendar.getDefaultTimeZone();
						//zkCalendar.setCurrentDate(Calendar.getInstance(timeZone).getTime());
						zkCalendar.setCurrentDate( Calendar.getInstance(timeZone).getTime() );
						
						if(autenticado) {
							List<Appointment> listaAppointments = ctrlAgenda.recuperarAppointment(zkCalendar.getBeginDate(), zkCalendar.getEndDate(), zkCalendar.getDefaultTimeZone(), agenda.getCalendarios());
							if(listaAppointments != null) {
								insereAppointmesnts(listaAppointments);
							}
						}
					}
				});
				btHoje.setParent(toolbar);
				
				Button btProximo = new Button("Prox >>");
				btProximo.setStyle("margin-right: 50px;");
				btProximo.addEventListener("onClick", new EventListener() {
					
					@Override
					public void onEvent(Event arg0) throws Exception {
						// TODO Auto-generated method stub
						zkCalendar.nextPage();
						
						if(autenticado) {
							//insere os compromissos
							List<Appointment> listaAppointments = ctrlAgenda.recuperarAppointment(zkCalendar.getBeginDate(), zkCalendar.getEndDate(), zkCalendar.getDefaultTimeZone(), agenda.getCalendarios());
							if(listaAppointments != null) {
								insereAppointmesnts(listaAppointments);
							}
						}
					}
				});
				btProximo.setParent(toolbar);
				
				btDia = new Button("Dia");
				btDia.addEventListener("onClick", new EventListener() {
					
					@Override
					public void onEvent(Event arg0) throws Exception {
						// TODO Auto-generated method stub
						zkCalendar.setMold("default");
						zkCalendar.setDays(1);
						
						btDia.setDisabled(true);
						btSemana.setDisabled(false);
						btMes.setDisabled(false);
						
						if(autenticado) {
							//insere os compromissos
							List<Appointment> listaAppointments = ctrlAgenda.recuperarAppointment(zkCalendar.getBeginDate(), zkCalendar.getEndDate(), zkCalendar.getDefaultTimeZone(), agenda.getCalendarios());
							if(listaAppointments != null) {
								insereAppointmesnts(listaAppointments);
							}
						}
					}
				});
				btDia.setParent(toolbar);
				
				
				btSemana =  new Button("Semana");
				btSemana.setDisabled(true);
				btSemana.addEventListener("onClick", new EventListener() {
					
					@Override
					public void onEvent(Event arg0) throws Exception {
						// TODO Auto-generated method stub
						zkCalendar.setMold("default");
						zkCalendar.setDays(7);
						
						btSemana.setDisabled(true);
						btDia.setDisabled(false);
						btMes.setDisabled(false);
						
						if(autenticado) {
						//insere os compromissos
							List<Appointment> listaAppointments = ctrlAgenda.recuperarAppointment(zkCalendar.getBeginDate(), zkCalendar.getEndDate(), zkCalendar.getDefaultTimeZone(), agenda.getCalendarios());
							if(listaAppointments != null) {
								insereAppointmesnts(listaAppointments);
							}
						}
					}
				});
				btSemana.setParent(toolbar);
				
				btMes =  new Button("Mes");
				btMes.setStyle("margin-right: 50px;");
				btMes.addEventListener("onClick", new EventListener() {
					
					@Override
					public void onEvent(Event arg0) throws Exception {
						// TODO Auto-generated method stub
						zkCalendar.setMold("month");
						
						btMes.setDisabled(true);
						btDia.setDisabled(false);
						btSemana.setDisabled(false);
						
						if(autenticado) {
							//insere os compromissos
							List<Appointment> listaAppointments = ctrlAgenda.recuperarAppointment(zkCalendar.getBeginDate(), zkCalendar.getEndDate(), zkCalendar.getDefaultTimeZone(), agenda.getCalendarios());
							if(listaAppointments != null) {
								insereAppointmesnts(listaAppointments);
							}
						}
					}
				});
				btMes.setParent(toolbar);

			toolbar.setParent(zkCalendar);
			
			zkCalendar.addEventListener("onEventCreate", new EventListener() {
				
				@Override
				public void onEvent(Event arg0) throws Exception {
					// TODO Auto-generated method stub
					
					CalendarsEvent event = (CalendarsEvent) arg0;
					
					Appointment appointment = new Appointment();
					appointment.setBeginDate(event.getBeginDate());
					appointment.setEndDate(event.getEndDate());
					appointment.setEvent(event);
					event.stopClearGhost();
					
					
					ctrlAgenda.abrirJanelaAppointmentSimples(jp, appointment, EnumCRUD.Criar, agenda.getCalProprietarios());
					
				}
			});
			
			//Este evento é acionado quando o usuário clica em uma célula na grade de horário que já está ocupado por um evento programado.
			zkCalendar.addEventListener("onEventEdit", new EventListener() {
				
				@Override
				public void onEvent(Event arg0) throws Exception {
					// TODO Auto-generated method stub
					
					CalendarsEvent event = (CalendarsEvent) arg0;
					
					Appointment appointment = (Appointment) event.getCalendarEvent();
					appointment.setEvent(event);
					event.stopClearGhost();
					
					ctrlAgenda.abrirJanelaAppointmentSimples(jp, appointment, EnumCRUD.Editar, agenda.getCalProprietarios());
				}
			});
			
			zkCalendar.addEventListener("onEventUpdate", new EventListener() {
				
				@Override
				public void onEvent(Event arg0) throws Exception {
					// TODO Auto-generated method stub
					CalendarsEvent event = (CalendarsEvent) arg0;
					Appointment appointment = (Appointment) event.getCalendarEvent();
					appointment.setBeginDate(event.getBeginDate());
					appointment.setEndDate(event.getEndDate());
					
					zkCalendar.removeDayEvent(event.getCalendarEvent());
					
					appointment = ctrlAgenda.atualizarAppointment(appointment);
					
					zkCalendar.addDayEvent(appointment);
				}
			});		
			
			
			if(autenticado) {
			//insere os compromissos da semana
				List<Appointment> listaAppointments = ctrlAgenda.recuperarAppointment(zkCalendar.getBeginDate(), zkCalendar.getEndDate(), zkCalendar.getDefaultTimeZone(), agenda.getCalendarios());
								
				if(listaAppointments != null) {
					insereAppointmesnts(listaAppointments);
				}
			}
		
			zkCalendar.setParent(geral);
		geral.setParent(this);
		
		//hbox.setParent(this);
		
	}
	
	public void insereAppointmesnts(List<Appointment> listaAppointments){
		
		for(Appointment appointment : listaAppointments) {
			zkCalendar.addDayEvent(appointment);
		}		
	}
	
	public void mostrar(){
		doOverlapped();
	}
	
	public void fechar() {
		onClose();
	}

}
