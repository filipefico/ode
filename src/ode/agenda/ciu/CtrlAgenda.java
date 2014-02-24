package ode.agenda.ciu;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.util.NucleoContexto;
import ode.agenda.cdp.Agenda;
import ode.agenda.cdp.Appointment;
import ode.agenda.cdp.Calendar;
import ode.agenda.cdp.Owner;
//import ode.agenda.cgd.AgendaDAO;
import ode.agenda.cgt.AplControlarAgenda;
import ode.agenda.cgt.AplControlarOwner;
import ode.alocacaoRecurso.cgd.AlocacaoFerramentaSoftwareDAO;
import ode.alocacaoRecurso.cgd.AlocacaoRHDAO;
import ode.controleProjeto.cdp.Projeto;
import ode.middlewareGoogle.calendar.AplConsumidorMiddlewareGoogle;
import ode.middlewareIssueTracker.mantis.AplConsumidorMiddlewareIssueTracker;
import ode.observador.cdp.ProdutorAlocaODE;
import ode.observador.cdp.ProdutorProjeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;

@Controller
public class CtrlAgenda extends CtrlBase {
	
	private static final long serialVersionUID = 1L;
	
	private JanPrincipal jp;
	private JanAutorizacaoGoogleInicial jagi;
	private JanAutorizacaoGoogleFinal   jagf;
	private JanAppointmentSimples jas;
	private JanAppointment ja;
	
	@Autowired
	AplControlarOwner aplControlarOwner;
	
	@Autowired
	AplControlarAgenda aplControlarAgenda;
		
	
	
	
	
	
	@Autowired
	public AlocacaoRHDAO alocacaoRHDAO;
	
	//@Autowired
	//public AgendaDAO agendaDAO;
	
	@Autowired
	public AlocacaoFerramentaSoftwareDAO alocacaoFerramentaSoftwareDAO;
	

	@Autowired
	AplConsumidorMiddlewareGoogle aplConsumidorMiddlewareGoogle;
	
	@Autowired
	AplConsumidorMiddlewareIssueTracker aplConsumidorMiddlewareIssueTracker;
	
	public void iniciarAutomaticamente() {
		
		
		//AQUI O OBSERVADOR SE REGISTRA NO PRODUTOR 
		ProdutorAlocaODE alocaODE = ProdutorAlocaODE.getInstance();		
		alocaODE.addObserver(aplConsumidorMiddlewareGoogle);
		
		ProdutorProjeto produtorProjeto = ProdutorProjeto.getInstance();
		produtorProjeto.addObserver(aplConsumidorMiddlewareIssueTracker);
		
		
		/*if (obterProjetosRecursoLogado().size() > 0) {
			iniciar();
		}*/
		
		//verifica se o já autorizou aplicacao do google
		boolean estaAutorizado = verificaAutorizacaoGoogleRHAtual();

		
		if(estaAutorizado == false) {
					
			if (Executions.getCurrent().getParameter("code") == null && Executions.getCurrent().getParameter("error") == null) {
				jagi = new JanAutorizacaoGoogleInicial(this);
				jagi.mostrar();
			}else{
				jagf = new JanAutorizacaoGoogleFinal(this);
				jagf.mostrar();
			}			
		}
		
		//VERIFICAR SE JÁ EXISTE ALOCACOES PARA ESSE RECURSO HUMANO
		
		
		
		//verifica se o calendario ODE ja existe
		Owner ownerAtual = aplControlarOwner.recuperarOwnerAtual();
		
		if(ownerAtual != null) {
			boolean existeCalendarioODE = aplControlarAgenda.existeCalendarioODE(ownerAtual);
			if(existeCalendarioODE == false) {
				aplControlarAgenda.criarCalendarioODE(ownerAtual);
			}
	
			//cria a agenda com as configuracoes padrao
			/*Agenda agenda = new Agenda();
			agenda.setOwner(ownerAtual);
			agenda.setPrimeiroDiaSemana(EnumDiaDaSemana.Domingo.getValor());
			agenda.setVisualizacaoPadrao(EnumVisualizacao.Semana.getValor());
		
			try {
				aplControlarAgenda.salvar(agenda);
			} catch (NucleoRegraNegocioExcecao e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			iniciar();	
		}
	}
	
	@Override
	public void iniciar() {
		//jan = new JanAgenda(this);
		//jan.mostrar();
		jp = new JanPrincipal(this);
		jp.mostrar();
	}	

	
	public void autenticacaoInicial(){
		aplControlarOwner.autenticacaoInicial();
	}
	
	public boolean autenticacaoFinal(){
		return aplControlarOwner.autenticacaoFinal();
	}
	
	
	public void redirecionarCallBack(){
		aplControlarOwner.redirecionaCallBack();
	}
	
	public boolean verificaAutorizacaoGoogleRHAtual(){
		return aplControlarOwner.verificaAutorizacaoGoogleRHAtual();
	}
	
	
	public Agenda recuperarAgenda(){
		
		Agenda agenda = new Agenda();
		
		agenda.setOwner(aplControlarOwner.recuperarOwnerAtual());
		agenda.setCalendarios( aplControlarAgenda.recuperarCalendarios(agenda.getOwner()) );
		agenda.setCalProprietarios( aplControlarAgenda.recuperarCalendariosPropietarios(agenda.getOwner()) );
		
		return agenda;
	}
	
	public Appointment inserirAppointment(Appointment appointment){
		return aplControlarAgenda.inserirAppointment(aplControlarOwner.recuperarOwnerAtual(), appointment);
	}
	
	public Appointment atualizarAppointment(Appointment appointment){
		return aplControlarAgenda.atualizarAppointment(aplControlarOwner.recuperarOwnerAtual(), appointment);
	}
	
	public void excluirEvent(Appointment appointment){
		aplControlarAgenda.excluirAppointment(aplControlarOwner.recuperarOwnerAtual(), appointment);
	}
	
	public List<Appointment> recuperarAppointment(Date dataInicio, Date dataFim, TimeZone zone, List<Calendar> listaCalendario) {
		
		//List<Calendar> listaCalendario = recuperarCalendarios();
		
		if(listaCalendario == null) return null;
		
		return aplControlarAgenda.recuperarAppointment(aplControlarOwner.recuperarOwnerAtual(), listaCalendario, dataInicio, dataFim, zone);
		
	}
	
	public List<ode.agenda.cdp.Calendar> recuperarCalendariosPropietarios(){
		return aplControlarAgenda.recuperarCalendariosPropietarios( aplControlarOwner.recuperarOwnerAtual() );
	}
	
	public List<Calendar> recuperarCalendarios() {
		return aplControlarAgenda.recuperarCalendarios(aplControlarOwner.recuperarOwnerAtual());
	}
	
	public void abrirJanelaAppointmentSimples(JanPrincipal jp, Appointment appointment, EnumCRUD enumCRUD, List<Calendar> calProprietarios){
		
		//verifica se já existe uma janela aberta, se verdadeiro fecha a janela
		int quant = jp.getChildren().size();
		for (int i = 0; i < quant; i++) {
			if(jp.getChildren().get(i) instanceof JanAppointmentSimples) {
				Component objetc = (Component) jp.getChildren().get(i);
				jp.removeChild(objetc);
			}
		}
		
		//event.stopClearGhost();
		jas = new JanAppointmentSimples(this, jp, appointment, enumCRUD, calProprietarios);
		jas.setParent(jp);
		jas.mostrar();
	}
	
	public void abrirJanelaAppointement(JanPrincipal jp, Appointment appointment, List<Calendar> listaCalendarios, EnumCRUD enumCRUD){
		jp.fechar();
		ja = new JanAppointment(this, appointment, listaCalendarios, enumCRUD);
		ja.mostrar();
	}
	
	

	public RecursoHumano getRecursoHumano() {
		return NucleoContexto.recuperarUsuarioLogado().getRecursoHumano();
	}

	public Collection<Projeto> obterProjetosRecursoLogado() {
		return alocacaoRHDAO.recuperarProjetosPorRH(getRecursoHumano().getId());
	}
	
	/*public Agenda getAgenda(){
		Owner owner = aplControlarOwner.recuperarOwnerAtual();
		return agendaDAO.recuperarAgenda(owner);
	}*/

}
