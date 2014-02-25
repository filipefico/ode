package ode.agenda.ciu;

import java.util.Date;
import java.util.List;

import ode._infraestruturaBase.util.NucleoContexto;
import ode.agenda.cdp.Appointment;
import ode.agenda.cdp.Calendar;
import ode.agenda.cdp.DailyAppointment;
import ode.agenda.cdp.MonthlyAppointment;
import ode.agenda.cdp.WeeklyAppointment;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

public class JanAppointment extends Window {

	private static final long serialVersionUID = 1L;
	private CtrlAgenda ctrlAgenda;
	private JanAppointment ja;
	private EnumCRUD crud;
	
	
	private Textbox textTitulo;
	private Datebox dtInicio;
	private Timebox horaInicio;
	private Datebox dtFim;
	private Timebox horaFim;
	private Checkbox cbDiaInteiro;
	private Checkbox cbRepetir;
	private Listbox listRepetir;
	private Textbox textOnde;
	private Listbox listCalendario;
	private Textbox textDescricao;
	
	private Appointment novoAppointment;
	
	private Vbox vRepetir;
	private Radiogroup rgTiposTermina;
	private Datebox dataInicio = new Datebox();
	private Datebox dataTermina = new Datebox(new Date());
	
	
	public JanAppointment(CtrlAgenda ctrl, Appointment appointment, List<Calendar> calProprietarios, EnumCRUD enumCRUD){
		super();
		this.ctrlAgenda = ctrl;
		this.ja = this;
		this.novoAppointment = appointment;
		this.crud = enumCRUD;
		
		
		this.setWidth("100%");
		this.setHeight("90%");
		this.setBorder(false);
		
		this.setParent(NucleoContexto.recuperarJanelaPrincipal());
		
		
		Vbox geral = new Vbox();
		
			Div divBotoes = new Div();
			divBotoes.setWidth("100%");
			divBotoes.setStyle("padding-left: 45px; padding-top: 14px; padding-bottom: 14px;");
			// border-bottom: 1px solid #ebebeb
			
				Button btSalvar = new Button("Salvar");
				btSalvar.setStyle("margin-right: 5px;");
				btSalvar.addEventListener("onClick", new EventListener() {
					
					@Override
					public void onEvent(Event arg0) throws Exception {
						// TODO Auto-generated method stub
						montarAppointment();
						
						if(crud == EnumCRUD.Criar) {
						ctrlAgenda.inserirAppointment(novoAppointment);
						}
							
						if(crud == EnumCRUD.Editar) {
							ctrlAgenda.atualizarAppointment(novoAppointment);
						}
						
						onClose();
						ctrlAgenda.iniciar();
					}
				});
				btSalvar.setParent(divBotoes);
				
				Button btDescartar = new Button("Descartar");
				btDescartar.addEventListener("onClick", new EventListener() {
					
					@Override
					public void onEvent(Event arg0) throws Exception {
						// TODO Auto-generated method stub
						onClose();
						ctrlAgenda.iniciar();
					}
				});
				btDescartar.setParent(divBotoes);
				

				if(enumCRUD == EnumCRUD.Editar) {
					Button btExcluir = new Button("Excluir");
					btExcluir.addEventListener("onClick", new EventListener() {
						
						@Override
						public void onEvent(Event arg0) throws Exception {
							// TODO Auto-generated method stub
							ctrlAgenda.excluirEvent(novoAppointment);
							onClose();
							ctrlAgenda.iniciar();
						}
					});
					btExcluir.setParent(divBotoes);
				}
			
			divBotoes.setParent(geral);
			
			//titulo do compromisso
			Hbox hTitulo = new Hbox();
			hTitulo.setStyle("padding-left: 45px; padding-top: 10px;");
			
				textTitulo = new Textbox(appointment.getContent());
				textTitulo.setWidth("460px");
				textTitulo.setStyle("padding: 5px;");
				textTitulo.setParent(hTitulo);
			
			hTitulo.setParent(geral);
			
			//data do compromisso
			Hbox hData = new Hbox();
			hData.setStyle("padding-left: 45px; padding-top: 10px;");
			
				dtInicio = new Datebox(appointment.getBeginDate());
				dtInicio.setStyle("margin-right: 4px;");
				dtInicio.addEventListener("onChange", new EventListener() {
					
					@Override
					public void onEvent(Event arg0) throws Exception {
						// TODO Auto-generated method stub
						dataInicio.setValue(dtInicio.getValue());
						if(dtInicio.getValue().getTime() >= dtFim.getValue().getTime()) {
							dtFim.setValue(dtInicio.getValue());
						}
					}
				});
				dtInicio.setParent(hData);
				
				horaInicio = new Timebox(appointment.getBeginDate());
				horaInicio.setFormat("short");
				horaInicio.setStyle("margin-right: 4px;");
				horaInicio.setParent(hData);
				
				Label labelAte = new Label("até");
				labelAte.setStyle("margin-right: 4px;");
				labelAte.setParent(hData);
				
				horaFim = new Timebox(appointment.getEndDate());
				horaFim.setFormat("short");
				horaFim.setStyle("margin-right: 4px;");
				horaFim.setParent(hData);
				
				dtFim = new Datebox(appointment.getEndDate());
				dtFim.setStyle("margin-right: 4px;");
				dtFim.addEventListener("onChange", new EventListener() {
					
					@Override
					public void onEvent(Event arg0) throws Exception {
						// TODO Auto-generated method stub
						if(dtFim.getValue().getTime() < dtInicio.getValue().getTime()) {
							dtInicio.setValue(dtFim.getValue());
						}
					}
				});
				dtFim.setParent(hData);
			
			hData.setParent(geral);
			
			
			Hbox hCheck = new Hbox();
			hCheck.setStyle("padding-left: 45px; padding-top: 10px;");
			
			    cbDiaInteiro = new Checkbox("dia Inteiro");
				cbDiaInteiro.setStyle("margin-right: 4px;");
				if(appointment.isAllDay()) {
					cbDiaInteiro.setChecked(true);
					
					horaInicio.setVisible(false);
					horaFim.setVisible(false);
				}
				cbDiaInteiro.addEventListener("onClick", new EventListener() {					
					@Override
					public void onEvent(Event arg0) throws Exception {
						// TODO Auto-generated method stub
						if(cbDiaInteiro.isChecked()) {
							horaInicio.setVisible(false);
							horaFim.setVisible(false);
						}else{
							horaInicio.setVisible(true);
							horaFim.setVisible(true);
						}
					}
				});
				cbDiaInteiro.setParent(hCheck);
				
				cbRepetir = new Checkbox("repetir");
				cbRepetir.setStyle("margin-right: 4px;");
				cbRepetir.addEventListener("onClick", new EventListener() {
					
					@Override
					public void onEvent(Event arg0) throws Exception {
						// TODO Auto-generated method stub
						if(cbRepetir.isChecked()){
							vRepetir.setVisible(true);
							dataInicio.setValue(dtInicio.getValue());
						}else{
							vRepetir.setVisible(false);
						}
					}
				});
				cbRepetir.setParent(hCheck);
			
			hCheck.setParent(geral);

			
			vRepetir = new Vbox();
			vRepetir.setStyle("padding-left: 90px; padding-top: 10px;");
			vRepetir.setVisible(false);
			
				Hbox hTipoRepeticao = new Hbox();
			
					Label repeticao = new Label("Repetição:");
					repeticao.setParent(hTipoRepeticao);
					
					listRepetir = new Listbox();
					listRepetir.setMold("select");
					
						Listitem diario = new Listitem(EnumTipoAppointment.DIARIO.getDescricao());
						diario.setSelected(true);
						diario.setParent(listRepetir);
						
						Listitem semanal = new Listitem(EnumTipoAppointment.SEMANAL.getDescricao());
						semanal.setParent(listRepetir);
						
						Listitem mensal = new Listitem(EnumTipoAppointment.MENSAL.getDescricao());
						mensal.setParent(listRepetir);
					
					
					listRepetir.setParent(hTipoRepeticao);
					
				hTipoRepeticao.setParent(vRepetir);
				
				
				Hbox hInicio = new Hbox();
				
					Label labelInicio = new Label("Início em:");
					labelInicio.setParent(hInicio);
					
					//Label dataInicio = new Label(dtInicio.getValue().toString());
					dataInicio.setDisabled(true);
					dataInicio.setParent(hInicio);
				
				hInicio.setParent(vRepetir);
				
				Hbox hTermina = new Hbox();
				
					Label labelTermina = new Label("Termina:");
					labelTermina.setParent(hTermina);
					
					//Vbox vTiposTermina = new Vbox();
					
					rgTiposTermina = new Radiogroup();
					rgTiposTermina.setOrient("vertical");
					
						Radio radioSempre = new Radio("Sempre");
						radioSempre.setChecked(true);
						radioSempre.setParent(rgTiposTermina);
						
						Radio radioEm = new Radio("Em");
						radioEm.setParent(rgTiposTermina);
					
					rgTiposTermina.addEventListener("onClick", new EventListener() {
						
						@Override
						public void onEvent(Event arg0) throws Exception {
							// TODO Auto-generated method stub
							if(rgTiposTermina.getSelectedItem().getLabel().equals("Em")){
								dataTermina.setVisible(true);
								
							}else{
								dataTermina.setVisible(false);
							}
							
						}
					});
					rgTiposTermina.setParent(hTermina);
				
					//vTiposTermina.setParent(hTermina);
					
					dataTermina.setVisible(false);
					dataTermina.setParent(hTermina);
				
				hTermina.setParent(vRepetir);
			
			vRepetir.setParent(geral);
			
			
			
			Hbox hOnde = new Hbox();
			hOnde.setStyle("padding-left: 45px; padding-top: 10px;");
			
				Label labelOnde = new Label("Onde:");
				labelOnde.setStyle("padding: 5px; margin-left: 15px;");
				labelOnde.setParent(hOnde);
				
				textOnde = new Textbox(appointment.getWhere());
				textOnde.setWidth("398px");
				textOnde.setStyle("padding: 5px;");
				textOnde.setParent(hOnde);
			
			hOnde.setParent(geral);
			
			Hbox hLista = new Hbox();
			hLista.setStyle("padding-left: 45px; padding-top: 10px;");
			
				Label labelAgenda = new Label("Agenda: ");
				labelAgenda.setStyle("margin-left: 10px");
				labelAgenda.setParent(hLista);
				
				listCalendario = new Listbox();
				listCalendario.setMold("select");
				
					for(Calendar calendar : calProprietarios) {
						Listitem listitem = new Listitem(calendar.getTitulo());
						listitem.setId(calendar.getId());
						if(calendar.isPrimary()){
							listitem.setSelected(true);
						}
						listitem.setParent(listCalendario);
					}
				
				listCalendario.setParent(hLista);
			
			hLista.setParent(geral);
			
			
			Hbox hDescricao = new Hbox();
			hDescricao.setStyle("padding-left: 45px; padding-top: 10px;");
			
				Label labelDescricao = new Label("Descrição:");
				labelDescricao.setStyle("border-color: #0f0");
				labelDescricao.setParent(hDescricao);
			
				textDescricao = new Textbox(appointment.getDescription());
				textDescricao.setMultiline(true);
				textDescricao.setRows(4);
				textDescricao.setWidth("404px");
				textDescricao.setParent(hDescricao);
							
			hDescricao.setParent(geral);
			
			
			
		
		geral.setParent(this);
		
	}
	
	
	public void montarAppointment(){
		novoAppointment.setContent(textTitulo.getValue());
		novoAppointment.setAllDay(cbDiaInteiro.isChecked());
		
			java.util.Calendar c = java.util.Calendar.getInstance();
			c.setTime(dtInicio.getValue());
			if(novoAppointment.isAllDay() == false) {
				c.setTime(horaInicio.getValue());
			}
		novoAppointment.setBeginDate(c.getTime());
		
			c.setTime(dtFim.getValue());
			if(novoAppointment.isAllDay() == false) {
				c.setTime(horaFim.getValue());
			}
		novoAppointment.setEndDate(c.getTime());
		novoAppointment.setWhere(textOnde.getValue());
			Calendar calendar = new Calendar();
			calendar.setId(listCalendario.getSelectedItem().getId());
		novoAppointment.setCalendar(calendar);
		novoAppointment.setDescription(textDescricao.getValue());
		
		
		if(cbRepetir.isChecked()){
			if(listRepetir.getSelectedItem().getLabel().equals(EnumTipoAppointment.DIARIO.getDescricao())){
				DailyAppointment diario = new DailyAppointment();
				diario = (DailyAppointment) novoAppointment;
				diario.setTermina(dataTermina.getValue());
				
				novoAppointment = diario;
			}
			if(listRepetir.getSelectedItem().getLabel().equals(EnumTipoAppointment.SEMANAL.getDescricao())){
				WeeklyAppointment semanal = new WeeklyAppointment();
				semanal = (WeeklyAppointment) novoAppointment;
				semanal.setTermina(dataTermina.getValue());
				
				novoAppointment = semanal;
			}
			if(listRepetir.getSelectedItem().getLabel().equals(EnumTipoAppointment.MENSAL.getDescricao())){
				MonthlyAppointment mensal = new MonthlyAppointment();
				mensal = (MonthlyAppointment) novoAppointment;
				mensal.setTermina(dataTermina.getValue());
				
				novoAppointment = mensal;
			}
		}
	}
	
	public void mostrar(){
		doOverlapped();
	}
	
	public void fechar() {
		onClose();
	}

}
