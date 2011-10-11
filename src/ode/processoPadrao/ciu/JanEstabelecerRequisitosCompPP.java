package ode.processoPadrao.ciu;

import ode._infraestruturaCRUD.ciu.JanelaSimples;

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
	}

	private void configuraTabPanelRequisitos(Tabpanel tabPanelRequisitos) {
		Vbox vbox = new Vbox();
		vbox.setParent(tabPanelRequisitos);
		Label labelRequisito = new Label();
		Textbox textBoxRequisito = new Textbox();
		
		labelRequisito.setValue("Requisitos");
		
		labelRequisito.setParent(vbox);
		textBoxRequisito.setParent(vbox);
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
		
		Textbox textBoxNome = new Textbox();
		Textbox textBoxDescricao= new Textbox();
		Textbox textBoxObjetivos = new Textbox();
		
		
		labelNome.setParent(vbox);
		textBoxNome.setParent(vbox);
		labelDescricao.setParent(vbox);
		textBoxDescricao.setParent(vbox);
		labelObjetivos.setParent(vbox);
		textBoxObjetivos.setParent(vbox);		
		
	}

	private void configuraTabBox(Tabpanel tabPanelPropriedades,
			Tabpanel tabPanelRequisitos) {
		Tabbox tabbox = new Tabbox();
		Tabs tabs = new Tabs();
		Tabpanels tabpanels = new Tabpanels();
		Tab tabPropriedades = new Tab();
		Tab tabRequisitos = new Tab();
		//Tabpanels tabpanels = new Tabpanels();

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
