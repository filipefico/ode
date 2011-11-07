package ode.processoPadrao.ciu;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.processoPadrao.cdp.CompPP;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;

public class JanEstabelecerRequisitosCompPP {
	private CtrlDefinirProcessoPadrao ctrl;
	private JanelaSimples janela;
	
	private Textbox textBoxNome;
	private Textbox textBoxDescricao;
	private Textbox textBoxObjetivos;
	private Textbox textBoxRequisito;

	public JanEstabelecerRequisitosCompPP(
			CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao,
			JanelaSimples JanelaSimples) {
		ctrl = ctrlDefinirProcessoPadrao;
		janela = JanelaSimples;

		configuracaoBasica();
		configuraElementosJanela();
		janela.mostrar();
	}

	private void configuraElementosJanela() {

		Tabpanel tabPanelPropriedades = new Tabpanel();
		Tabpanel tabPanelRequisitos = new Tabpanel();

		configuraTabBox(tabPanelPropriedades, tabPanelRequisitos);
		configuraTabPanelPropriedades(tabPanelPropriedades);
		configuraTabPanelRequisitos(tabPanelRequisitos);

		botaoSalvar();
	}

	protected void botaoSalvar() {
		Button buttonSalvar = new Button();
		buttonSalvar.setLabel("salvar");
		buttonSalvar.addEventListener("onClick", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				salvarDados();
				janela.onClose();
			}
		});
		buttonSalvar.setParent(janela);
	}
	
	private void salvarDados() {
		CompPP compPP = ctrl.getcompPPSelecionado();
		
		compPP.setNome(textBoxNome.getText());
		compPP.setDescricao(textBoxDescricao.getText());
		compPP.setObjetivo(textBoxObjetivos.getText());
		compPP.setRequisitoCompPP(textBoxRequisito.getText());

		ctrl.atualizarCompPP(compPP);
	}

	private void configuraTabPanelRequisitos(Tabpanel tabPanelRequisitos) {
		Vbox vbox = new Vbox();
		vbox.setParent(tabPanelRequisitos);
		Label labelRequisito = new Label();
		textBoxRequisito = new Textbox();

		labelRequisito.setValue("Requisitos");

		labelRequisito.setParent(vbox);
		textBoxRequisito.setParent(vbox);

		carregaDadosRequisitos();
	}

	private void carregaDadosRequisitos() {
		textBoxRequisito.setText(ctrl.getcompPPSelecionado()
				.getRequisitoCompPP());
	}

	private void configuraTabPanelPropriedades(Tabpanel tabPanelPropriedades) {

		Vbox vbox = new Vbox();
		vbox.setParent(tabPanelPropriedades);

		Label labelNome = new Label();
		Label labelDescricao = new Label();
		Label labelObjetivos = new Label();

		labelNome.setValue("Nome");
		labelDescricao.setValue("Descrição");
		labelObjetivos.setValue("Objetivos");

		textBoxNome = new Textbox();
		textBoxDescricao = new Textbox();
		textBoxObjetivos = new Textbox();

		labelNome.setParent(vbox);
		textBoxNome.setParent(vbox);
		labelDescricao.setParent(vbox);
		textBoxDescricao.setParent(vbox);
		labelObjetivos.setParent(vbox);
		textBoxObjetivos.setParent(vbox);

		carregaDadosPropriedades();

	}

	private void carregaDadosPropriedades() {
		CompPP compPP = ctrl.getcompPPSelecionado();
		textBoxNome.setText(compPP.getNome());
		textBoxDescricao.setText(compPP.getDescricao());
		textBoxObjetivos.setText(compPP.getObjetivo());
	}

	private void configuraTabBox(Tabpanel tabPanelPropriedades,
			Tabpanel tabPanelRequisitos) {
		Tabbox tabbox = new Tabbox();
		Tabs tabs = new Tabs();
		Tabpanels tabpanels = new Tabpanels();
		Tab tabPropriedades = new Tab();
		Tab tabRequisitos = new Tab();
		// Tabpanels tabpanels = new Tabpanels();

		tabPropriedades.setLabel("Propriedades");
		tabRequisitos.setLabel("Requisitos");

		tabbox.setParent(janela);
		tabs.setParent(tabbox);
		tabPropriedades.setParent(tabs);
		tabRequisitos.setParent(tabs);

		tabpanels.setParent(tabbox);

		tabPanelPropriedades.setParent(tabpanels);
		tabPanelRequisitos.setParent(tabpanels);

	}

	private void configuracaoBasica() {
		janela.setTitle("Estabelecer Requisitos de CompPP");
		janela.setWidth("600px");
		janela.setBorder("normal");
		janela.setClosable(true);
		janela.setPosition("&quot;center;&quot;;");
		janela.setSizable(true);
		janela.setMaximizable(true);
	}
}
