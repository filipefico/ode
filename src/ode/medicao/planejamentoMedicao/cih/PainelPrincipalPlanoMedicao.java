package ode.medicao.planejamentoMedicao.cih;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode.alocacaoRecurso.ciu.CtrlEsforcoDespendidoCRUD;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.conhecimentoMedicao.cci.CtrlKEscalaCRUD;
import ode.conhecimentoMedicao.cci.CtrlKValorEscalaCRUD;
import ode.medicao.planejamentoMedicao.cdp.NecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicao;
import ode.medicao.planejamentoMedicao.cci.CtrlNecessidadeInformacaoCRUD;
import ode.medicao.planejamentoMedicao.cci.CtrlObjetivoEstrategicoCRUD;
import ode.medicao.planejamentoMedicao.cci.CtrlObjetivoMedicaoCRUD;
import ode.medicao.planejamentoMedicao.cci.CtrlObjetivoSoftwareCRUD;
import ode.medicao.planejamentoMedicao.cci.CtrlPlanoMedicao;
import ode.medicao.planejamentoMedicao.cci.CtrlValorReferencia;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
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

	protected CtrlPlanoMedicao ctrl;
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
	
	private class EventListenerObjetivoE implements EventListener{
		@Override
		public void onEvent(Event arg0) throws Exception {
			dispararBean(new CtrlObjetivoEstrategicoCRUD());
		}
	}
	private class EventListenerObjetivoS implements EventListener{
		@Override
		public void onEvent(Event arg0) throws Exception {
			dispararBean(new CtrlObjetivoSoftwareCRUD());
		}
	}
	private class EventListenerObjetivoM implements EventListener{
		@Override
		public void onEvent(Event arg0) throws Exception {
			dispararBean(new CtrlObjetivoMedicaoCRUD());
		}
	}
	
	private class EventListenerNecessidades implements EventListener{
		@Override
		public void onEvent(Event arg0) throws Exception {
			dispararBean(new CtrlNecessidadeInformacaoCRUD());
		}
	}
	
	private CtrlBase dispararBean(CtrlBase base){
		CtrlBase ctrlBase;
		ctrlBase = (CtrlBase) SpringUtil.getApplicationContext().getBean(base.getClass());
		ctrlBase.iniciar();
		return ctrlBase;
	}
	
	public void montar() {
		medPlan.setControlador(ctrl);
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
		
		setMenuItem(popupObjetivos, "Estrategicos", new EventListenerObjetivoE());
		setMenuItem(popupObjetivos, "Software", new EventListenerObjetivoS());
		setMenuItem(popupObjetivos, "Medição", new EventListenerObjetivoM());
		setMenuItem(popupObjetivos, "Necessidade de Informação", new EventListenerNecessidades());
		
		menubar.setParent(this);
	}
	
	protected void adicionarBarraInferior() {
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
		
		listaTabs = configurarAbas(new ArrayList<NucleoTab>());
		
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
	protected Textbox ibVersao = new Textbox();
	protected NucleoCombobox<RecursoHumano> cbResponsavel = new NucleoCombobox<RecursoHumano>();
	protected Datebox dbData = new Datebox();
	protected Textbox tbDescricao = new Textbox();
	//////
	protected ComponenteObjetivosTree compObj;
	/////
	protected Vbox boxNovasMedidas = new Vbox();
	protected ComponenteMedidasPlano medPlan = new ComponenteMedidasPlano();
	/////////////////////////////////////////////
	
	protected List<NucleoTab> configurarAbas(List<NucleoTab> listaabas) {
		List<NucleoTab> abas = listaabas;
		
		////////////////////////////
		//Controle
		///////////////////////////
		
		NucleoTab tabControle = new NucleoTab();
		
		tabControle.setNomeTab("Controle");
		
		GridDados gridControle =  new GridDados();
		
		gridControle.adicionarLinhaObrigatoria("Versão", ibVersao);
		
		cbResponsavel.setObjetos(((CtrlPlanoMedicao)this.getControlador()).getAplRecursoHumano().recuperarTodos());
		cbResponsavel.selecionarPrimeiroElemento();
		gridControle.adicionarLinhaObrigatoria("Responsável", cbResponsavel);
		
		gridControle.adicionarLinhaObrigatoria("Data", dbData);
		
		tbDescricao.setWidth("100%");
		tbDescricao.setRows(3);
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
		
		compObj.iniciar(((CtrlPlanoMedicao)ctrl).getObjetivosEstrategicos());
		compObj.setEventoMedicao(new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				medPlan.atualizar((HashSet<NecessidadeInformacao>)arg0.getData());
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
	
	public void preencherDados(PlanoMedicao objeto) {
		dbData.setValue(objeto.getData());
		tbDescricao.setText(objeto.getDescricao());
		ibVersao.setText(Float.toString(objeto.getVersao()));
		cbResponsavel.setObjetoSelecionado(objeto.getResponsavel());
		compObj.populaArvore(objeto.getObjsEstrategico(), objeto.getObjsSoftware(), objeto.getObjsMedicao(), objeto.getNecessidades(), objeto.getProcessos());
		medPlan.mostraMpm(objeto.getMpm());
		ctrl.setTitle(objeto.toString());
	}
	
	public void preencherObjetos(PlanoMedicao objeto) {
		objeto.setData(dbData.getValue());
		objeto.setDescricao(tbDescricao.getText());
		objeto.setVersao(Float.valueOf(ibVersao.getText()));
		objeto.setResponsavel(cbResponsavel.getObjetoSelecionado());
		objeto.setObjsEstrategico(compObj.getEstrategicosSelecionados());
		objeto.setObjsSoftware(new HashSet(compObj.getSoftwareSelecionados()));
		objeto.setObjsMedicao(new HashSet(compObj.getMedicaoSelecionados()));
		objeto.setNecessidades(new HashSet(compObj.getNecessidadesSelecionadas()));
		objeto.setProcessos(new HashSet(compObj.getProcessosSelecionados()));
		objeto.setMpm(medPlan.confereMpm(objeto.getMpm()));
	}
	
}
