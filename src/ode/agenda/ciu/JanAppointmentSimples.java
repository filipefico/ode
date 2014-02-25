package ode.agenda.ciu;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.ciu.NucleoTabbox;
import ode._infraestruturaBase.util.NucleoUtil;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode.agenda.cdp.Allocation_derivedAppointment;
import ode.agenda.cdp.Appointment;
import ode.alocacaoRecurso.cdp.AlocacaoRH;
import ode.alocacaoRecurso.ciu.CtrlEsforcoDespendidoCRUD;
import ode.alocacaoRecurso.ciu.PainelCRUDEsforcoDespendido;

import org.zkoss.calendar.event.CalendarsEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;

import com.ibm.icu.text.SimpleDateFormat;

public class JanAppointmentSimples extends Window {

	private static final long serialVersionUID = 1L;
	private CtrlAgenda ctrlAgenda;
	private JanPrincipal janPrincipal;
	private JanAppointmentSimples janAppointementSimples;
	
	private Appointment appointment;
	private List<ode.agenda.cdp.Calendar> listaCalProprietarios;
	
	private Textbox txtTitulo = new Textbox();
	Listbox listaCalendario = null;
	private EnumCRUD crud;
	
	public JanAppointmentSimples(CtrlAgenda ctrl, JanPrincipal janP, Appointment a, EnumCRUD enumCRUD, List<ode.agenda.cdp.Calendar> calProprietarios) {
		super();
		
		ctrlAgenda = ctrl;
		janPrincipal = janP;
		janAppointementSimples = this;
		appointment = a;
		listaCalProprietarios = calProprietarios;
		this.crud = enumCRUD;
		
		this.setTitle("Informações");
		this.setWidth("600px");
		this.setPosition("&quot;center;&quot;;");
		this.setClosable(true);
		
		
		NucleoTabbox tabbox  = new NucleoTabbox();
		tabbox.setParent(this);
		
		NucleoTab tabAppointment = new NucleoTab("Compromisso");
		tabbox.addTab(tabAppointment);
		
			
			Vlayout vlayout = new Vlayout();
			tabAppointment.setConteudoTab(vlayout);

			GridDados gridDadosAppointment = new GridDados();
			gridDadosAppointment.setLarguras("30%", "70%");
			gridDadosAppointment.setAlinhamento("right", "left");
			
			if(enumCRUD ==  EnumCRUD.Criar) {
				
				gridDadosAppointment.adicionarLinha("Quando", ajustaData(appointment.getBeginDate(), appointment.getEndDate()));
				txtTitulo.setWidth("100%");
				gridDadosAppointment.adicionarLinha("Título", txtTitulo);
				
				listaCalendario = new Listbox();
				listaCalendario.setMold("select");
				
					//List<ode.agenda.cdp.Calendar> lista = ctrlAgenda.recuperarCalendariosPropietarios();
					
					for(ode.agenda.cdp.Calendar atual : calProprietarios) {
						Listitem listitem = new Listitem(atual.getTitulo());
						listitem.setId(atual.getId());
						if(atual.isPrimary()) {
							listitem.setSelected(true);
						}
						listitem.setParent(listaCalendario);
					}
				gridDadosAppointment.adicionarLinha("Agenda", listaCalendario);
				
				
				
			}else{
							
				gridDadosAppointment.adicionarLinha("Quando", ajustaData(appointment.getBeginDate(), appointment.getEndDate()));
				txtTitulo.setValue(appointment.getContent());
				gridDadosAppointment.adicionarLinha("Título", appointment.getContent());
				if(appointment.getWhere() != null) {
					gridDadosAppointment.adicionarLinha("Onde", appointment.getWhere());
				}
			}
			
			if(!appointment.isLocked() && !(a instanceof Allocation_derivedAppointment)){
				
				Button btEditarEvento = new Button("Editar Evento");
				btEditarEvento.addEventListener("onClick", new EventListener() {
					
					@Override
					public void onEvent(Event arg0) throws Exception {
						// TODO Auto-generated method stub
						ajustaAppointment();
						ctrlAgenda.abrirJanelaAppointement(janPrincipal, appointment, listaCalProprietarios, crud);
					}
				});
				
				if(enumCRUD == EnumCRUD.Criar) {
					Button btCriarEvento = new Button("Salvar");
					btCriarEvento.addEventListener("onClick", new EventListener() {
						
						@Override
						public void onEvent(Event arg0) throws Exception {
							// TODO Auto-generated method stub
							ajustaAppointment();
							ctrlAgenda.inserirAppointment(appointment);
							janAppointementSimples.fechar();
							//appointment.getEvent().clearGhost();
							janPrincipal.fechar();
							ctrlAgenda.iniciar();
						}
					});
					
					gridDadosAppointment.adicionarLinha(btCriarEvento, btEditarEvento);
				}
				
				if(enumCRUD == EnumCRUD.Editar) {
					Button btExcluirEvento = new Button("Excluir");
					btExcluirEvento.addEventListener("onClick", new EventListener() {
						
						@Override
						public void onEvent(Event arg0) throws Exception {
							// TODO Auto-generated method stub
							ctrlAgenda.excluirEvent(appointment);
							janAppointementSimples.fechar();
							janPrincipal.fechar();
							ctrlAgenda.iniciar();
						}
					});

					gridDadosAppointment.adicionarLinha(btExcluirEvento, btEditarEvento);
				}
				
				
				
			}else{
				
				Button btMais = new Button("Mais Informações");
				
				gridDadosAppointment.adicionarLinhaUnica(btMais);
			}
	
	
			gridDadosAppointment.setParent(vlayout);
	
		
		if(a instanceof Allocation_derivedAppointment) {
			NucleoTab tabEsforco = new NucleoTab("Esforço Despendido");
			tabbox.addTab(tabEsforco);
			NucleoTab tabAlocacao = new NucleoTab("Alocação");
			//tabAlocacao.getTabpanel().setHeight("265px");
			tabbox.addTab(tabAlocacao);
			
			
			//PainelCRUDEsforcoDespendido painelEsforco = SpringUtil.getApplicationContext().getBean(CtrlEsforcoDespendidoCRUD.class).iniciar(alocacaoRH);
			//tabEsforco.setConteudoTab(painelEsforco);
			
			AlocacaoRH alocacaoRH = ((Allocation_derivedAppointment) a).getAlocacaoRH();
			
			PainelCRUDEsforcoDespendido painelEsforco = SpringUtil.getApplicationContext().getBean(CtrlEsforcoDespendidoCRUD.class).iniciar(alocacaoRH);
			tabEsforco.setConteudoTab(painelEsforco);
			
			GridDados gridDados = new GridDados();
			tabAlocacao.setConteudoTab(gridDados);
			

			gridDados.adicionarLinha("Papel", alocacaoRH.getkRecursoHumano().getNome());
			gridDados.adicionarLinha("Estado", alocacaoRH.getEstado().toString());
			gridDados.adicionarLinha("Data de Início Previsto", NucleoUtil.formataData(alocacaoRH.getDtInicioPrevisto()));
			gridDados.adicionarLinha("Data de Fim Previsto", NucleoUtil.formataData(alocacaoRH.getDtFimPrevisto()));
			gridDados.adicionarLinha("Data de Início Efetivo", NucleoUtil.formataData(alocacaoRH.getDtInicioEfetivo()));
			gridDados.adicionarLinha("Data de Fim Efetivo", NucleoUtil.formataData(alocacaoRH.getDtFimEfetivo()));
			gridDados.adicionarLinha("Dedicação", (alocacaoRH.getDedicacao() != null) ? alocacaoRH.getDedicacao().toString() : "");
	
		}
		
			
		
		
		//limpa a sombra do evento caso a janela seja fechada
		this.addEventListener("onClose", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				CalendarsEvent event = appointment.getEvent();
				event.clearGhost();
			}
		});
		
		
	}
	
	public void ajustaAppointment(){
		if(txtTitulo.getValue() == null || txtTitulo.getValue() == "") {
			appointment.setContent("Compromisso sem título");
		}else{
			appointment.setContent(txtTitulo.getValue());
		}
		
		if(listaCalendario != null){
			ode.agenda.cdp.Calendar novoCalendar = new ode.agenda.cdp.Calendar();
			novoCalendar.setId( listaCalendario.getSelectedItem().getId() );
			appointment.setCalendar(novoCalendar);
		}
		
	}
	
	public String ajustaData(Date star, Date end){
		
		
		String sQuando = new String();
		//sQuando = appointment.getBeginDate().toString() + " até " + appointment.getEndDate().toString();
		
		Calendar c = Calendar.getInstance();
		
		c.setTime(appointment.getBeginDate());
							
		//Pega o dia da semana
		switch (c.get(Calendar.DAY_OF_WEEK)) {
		case 1:
			sQuando = "Dom, ";
			break;
		case 2:
			sQuando = "Seg, ";
			break;
		case 3:
			sQuando = "Ter, ";
			break;
		case 4:
			sQuando = "Qua, ";
			break;
		case 5:
			sQuando = "Qui, ";
			break;
		case 6:
			sQuando = "Sex, ";
			break;
		default:
			sQuando = "Sáb, ";
			break;
		}
		
		
		//Pega o dia do Mes
		sQuando = sQuando + String.valueOf(c.get(Calendar.DAY_OF_MONTH))+" ";
		
		//Pega o Mes
		switch (c.get(Calendar.MONTH)) {
		case 0:
			sQuando = sQuando + "de Janeiro";
			break;
		case 1:
			sQuando = sQuando + "de Fevereiro";
			break;
		case 2:
			sQuando = sQuando + "de Março";
			break;
		case 3:
			sQuando = sQuando + "de Abril";
			break;
		case 4:
			sQuando = sQuando + "de Maio";
			break;
		case 5:
			sQuando = sQuando + "de Junho";
			break;
		case 6:
			sQuando = sQuando + "de Julho";
			break;
		case 7:
			sQuando = sQuando + "de Agosto";
			break;
		case 8:
			sQuando = sQuando + "de Setembro";
			break;
		case 9:
			sQuando = sQuando + "de Outubro";
			break;
		case 10:
			sQuando = sQuando + "de Novembro";
			break;

		default:
			sQuando = sQuando + "de Dezembro";
			break;
		}
		
		SimpleDateFormat padrao = new SimpleDateFormat("HH:mm");
		
		sQuando = sQuando + ", ";						
		sQuando = sQuando + padrao.format(c.getTime());
		sQuando = sQuando + " - ";	
		c.setTime(appointment.getEndDate());
		sQuando = sQuando + padrao.format(c.getTime());
		
		
		
		
		return sQuando;
	}
	
	public void mostrar(){
		doOverlapped();	
	}
	
	public void fechar(){
		onClose();
	}

}
