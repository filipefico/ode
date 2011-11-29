package ode.medicao.planejamentoMedicao.cih;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.medicao.planejamentoMedicao.cdp.KNecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicao;
import ode.medicao.planejamentoMedicao.cci.CtrlPlanoMedicao;
import ode.medicao.planejamentoMedicao.cci.CtrlPlanoMedicaoOrganizacao;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.impl.XulElement;

@org.springframework.stereotype.Component
public abstract class PainelPrincipalPlanoMedicao extends Vlayout {

	private CtrlPlanoMedicao ctrl;
	private Toolbar toolbarSuperior;
	private Toolbar toolbarInferior;
	private Menubar menubar;
	
	protected List<NucleoTab> listaTabs;
	protected Tabbox tabbox = new Tabbox();
	protected Tabs tabs = new Tabs();
	protected Tabpanels tabpanels = new Tabpanels();
		
	protected abstract void novo();
	protected abstract void salvar();
	protected abstract void abrir();
	protected abstract void deletar();
	
	
	private class EventListenerNovo implements EventListener{
		@Override
		public void onEvent(Event arg0) throws Exception {
			novo();
		}
	}

	private class EventListenerAbrir implements EventListener{
		@Override
		public void onEvent(Event arg0) throws Exception {
			abrir();
		}
	}
	
	private class EventListenerExcluir implements EventListener{
		@Override
		public void onEvent(Event arg0) throws Exception {
			deletar();
		}
	}
	
	private class EventListenerSalvar implements EventListener{
		@Override
		public void onEvent(Event arg0) throws Exception {
			salvar();
		}
	}
	
	private class EventListenerObjetivo implements EventListener{
		@Override
		public void onEvent(Event arg0) throws Exception {
			
		}
	}
	
	public void montar() {
		adicionarBarraSuperior();
		tabbox.setParent(this);
		tabs.setParent(tabbox);
		tabpanels.setParent(tabbox);
		adicionarAbas();
		adicionarBarraInferior();
	}

	private void setMenuItem(Menupopup menu, String label, EventListener event){
		Menuitem item = new Menuitem();
		item.setLabel(label);
		item.addEventListener("onClick", event);
		menu.appendChild(item);
	}
	
	private void setMenuItem(Menupopup menu, String label, EventListener event, String img){
		Menuitem item = new Menuitem();
		item.setLabel(label);
		item.setImage(img);
		item.addEventListener("onClick", event);
		menu.appendChild(item);
	}
	
	private void adicionarBarraSuperior() {
		menubar = new Menubar();
		
		menubar.setStyle("border:0px;background:white;");
		
		Menu menuPlanoMedicao = new Menu("Plano de Medição");
		Menupopup popupPlanos = new Menupopup();
		menubar.appendChild(menuPlanoMedicao);
		menuPlanoMedicao.appendChild(popupPlanos);

		setMenuItem(popupPlanos, NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOVO), new EventListenerNovo(),"/imagens/filenew.png");
		setMenuItem(popupPlanos, NucleoMensagens.getMensagem(NucleoMensagens.TERMO_ABRIR), new EventListenerAbrir(),"/imagens/fileopen.png");
		setMenuItem(popupPlanos, NucleoMensagens.getMensagem(NucleoMensagens.TERMO_EXCLUIR), new EventListenerExcluir(),"/imagens/editdelete.png");
		
		
		Menu menuObjetivos = new Menu("Objetivos");
		Menupopup popupObjetivos = new Menupopup();
		menubar.appendChild(menuObjetivos);
		menuObjetivos.appendChild(popupObjetivos);
		
		setMenuItem(popupObjetivos, "Estrategicos", new EventListenerObjetivo());
		setMenuItem(popupObjetivos, "Software", new EventListenerObjetivo());
		setMenuItem(popupObjetivos, "Medição", new EventListenerObjetivo());
		
		menubar.setParent(this);
	}
	
	private void adicionarBarraInferior() {
		toolbarInferior = new Toolbar();
		
		toolbarInferior.setStyle("border:0px;background:white;");
		toolbarInferior.setAlign("end");
		
		Button buttonSalvar = new Button();
		
		buttonSalvar.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_SALVAR));
		buttonSalvar.addEventListener("onClick", new EventListenerSalvar());
		
		buttonSalvar.setParent(toolbarInferior);
		
		toolbarInferior.setParent(this);
	}


	protected void adicionarAbas() {
		
		listaTabs = configurarAbas();
		
		for (NucleoTab nucleoTab : listaTabs) {
			// Cria tab
			Tab tab = new Tab(nucleoTab.getNomeTab());
			tab.setParent(tabs);

			// Cria painel com conteúdo
			Tabpanel tabpanel = new Tabpanel();
			tabpanel.setParent(tabpanels);
			XulElement conteudo = nucleoTab.getConteudoTab();
			conteudo.setParent(tabpanel);
		}
	}

	protected CtrlPlanoMedicao getControlador() {
		return ctrl;
	}
	public void setControlador(CtrlPlanoMedicao ctrl){
		this.ctrl = ctrl;
	}

	//////////////////////////////////////////////
	protected Decimalbox ibVersao = new Decimalbox();
	protected NucleoCombobox<KRecursoHumano> cbResponsavel = new NucleoCombobox<KRecursoHumano>();
	protected Datebox dbData = new Datebox();
	protected Textbox tbDescricao = new Textbox();
	//////
	protected ComponenteObjetivosTree compObj;
	/////
	protected Vbox boxNovasMedidas = new Vbox();
	protected ComponenteMedidasPlano medPlan = new ComponenteMedidasPlano();
	/////////////////////////////////////////////
	
	protected List<NucleoTab> configurarAbas() {
		List<NucleoTab> abas = new ArrayList<NucleoTab>();
		
		////////////////////////////
		//Controle
		///////////////////////////
		
		NucleoTab tabControle = new NucleoTab();
		
		tabControle.setNomeTab("Controle");
		
		GridDados gridControle =  new GridDados();
		
		gridControle.adicionarLinhaObrigatoria("Versão", ibVersao);
		
		cbResponsavel.setObjetos(((CtrlPlanoMedicaoOrganizacao)this.getControlador()).getAplRecursoHumano().recuperarTodos());
		cbResponsavel.selecionarPrimeiroElemento();
		gridControle.adicionarLinhaObrigatoria("Responsável", cbResponsavel);
		
		gridControle.adicionarLinhaObrigatoria("Data", dbData);
		
		gridControle.adicionarLinha("Descrição", tbDescricao);
		
		tabControle.setConteudoTab(gridControle);
		
		abas.add(tabControle);
		
		///////////////////////////
		//Objetivos e Necessidade de Informacao
		//////////////////////////
		
		NucleoTab tabObjetivos = new NucleoTab();
		
		tabObjetivos.setNomeTab("Objetivos e Necessidades de Informação");
		
		Vbox caixa = new Vbox();
		
		compObj = new ComponenteObjetivosTree();
		
		compObj.iniciar(((CtrlPlanoMedicaoOrganizacao)ctrl).getObjetivosEstrategicos());
		compObj.setEventoMedicao(new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				medPlan.atualizar((HashSet<KNecessidadeInformacao>)arg0.getData());
			}
		});
		
		tabObjetivos.setConteudoTab(compObj);
		
		
		abas.add(tabObjetivos);
		
		///////////////////////////////////
		///Medidas
		//////////////////////////////////
		
		NucleoTab tabMedidas = new NucleoTab();
		
		tabMedidas.setNomeTab("Medidas");
		
		Vbox boxMedidas = new Vbox();
		
		medPlan.setParent(boxMedidas);
		
		///
		tabMedidas.setConteudoTab(boxMedidas);
		
		abas.add(tabMedidas);
		
		/////////////////////////////////
		
		return abas;
	}
	
}
