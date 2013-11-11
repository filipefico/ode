package ode.processoProjeto.ciu;

import java.util.Collection;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.principal.cdp.Conhecimento;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cdp.CompPPMacroatividade;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.CompPPProcessoSimples;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Label;
import org.zkoss.zul.Space;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;

public class JanEditarPropriedadesBasicasProjeto extends JanCore {
	
	private JanelaSimples janela;
	
	public JanEditarPropriedadesBasicasProjeto(CtrlDefinirProcessoProjeto ctrlDefinirProcessoProjeto) {
		super(ctrlDefinirProcessoProjeto);
		janela = this;

		configuraElementosJanela();
		janela.mostrar();
	}

	private Label labelNome = new Label("Nome");
	private Label labelDescricao = new Label("Descrição");
	private Label labelObjetivos = new Label("Objetivos");
	private Label labelRequisito = new Label("Requisitos");
	private Label labelTipo = new Label("Tipo");
	private Textbox textBoxNome = new Textbox();
	private Textbox textBoxDescricao = new Textbox();
	private Textbox textBoxObjetivos = new Textbox();
	private Textbox textBoxRequisito = new Textbox();		
	private Combobox comboboxTipo = new Combobox();	
	
	private void configuraElementosJanela() {
		janela.setTitle("Editar propriedades básicas do Componente");

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
		CompPP compPP = ctrl.getcompPPrSelecionado().getPadraoBase();

		compPP.setNome(textBoxNome.getText());
		compPP.setDescricao(textBoxDescricao.getText());
		compPP.setObjetivo(textBoxObjetivos.getText());
		compPP.setRequisitoCompPP(textBoxRequisito.getText());
		// add tipo
		if (compPP instanceof CompPPProcessoComplexo == false) {
			if (compPP instanceof CompPPProcessoSimples) {
				((CompPPProcessoSimples) compPP).setTipo((KProcesso) comboboxTipo.getSelectedItem().getValue());
			} else {
				((CompPPMacroatividade) compPP).setTipo((KAtividade) comboboxTipo.getSelectedItem().getValue());
			}
		}
		//ctrl.atualizarCompPP(compPP);
	}

	private void configuraTabPanelRequisitos(Tabpanel tabPanelRequisitos) {
		Vbox vbox = new Vbox();
		vbox.setParent(tabPanelRequisitos);
		textBoxRequisito.setWidth("100%");
		textBoxRequisito.setRows(3);
		textBoxRequisito.setMultiline(true);
		labelRequisito.setParent(vbox);
		textBoxRequisito.setParent(vbox);
		vbox.setWidth("100%");

		carregaDadosRequisitos();
	}

	private void carregaDadosRequisitos() {
		textBoxRequisito.setText(ctrl.getcompPPrSelecionado().getPadraoBase().getRequisitoCompPP());
	}

	private void configuraTabPanelPropriedades(Tabpanel tabPanelPropriedades) {

		Vbox vbox = new Vbox();
		vbox.setParent(tabPanelPropriedades);
		vbox.setWidth("100%");

		labelNome.setParent(vbox);
		textBoxNome.setParent(vbox);
		textBoxNome.setWidth("100%");
		new Space().setParent(vbox);
		
		labelDescricao.setParent(vbox);
		textBoxDescricao.setParent(vbox);
		textBoxDescricao.setWidth("100%");
		textBoxDescricao.setRows(3);
		textBoxDescricao.setMultiline(true);
		new Space().setParent(vbox);
		
		labelObjetivos.setParent(vbox);
		textBoxObjetivos.setParent(vbox);
		textBoxObjetivos.setWidth("100%");
		textBoxObjetivos.setRows(3);
		new Space().setParent(vbox);	

		labelTipo.setVisible(false);
		labelTipo.setParent(vbox);
		comboboxTipo.setParent(vbox);
		comboboxTipo.setWidth("100%");
		comboboxTipo.setVisible(false);

		carregaDadosPropriedades();
	}

	private void carregaDadosPropriedades() {
		CompPP compPP = ctrl.getcompPPrSelecionado().getPadraoBase();
		textBoxNome.setText(compPP.getNome());
		textBoxDescricao.setText(compPP.getDescricao());
		textBoxObjetivos.setText(compPP.getObjetivo());
		Collection conhecimento = null;

		if (compPP instanceof CompPPProcessoComplexo == false) {
			comboboxTipo.setVisible(true);
			labelTipo.setVisible(true);

			if (compPP instanceof CompPPProcessoSimples) {
				//conhecimento = ctrl.getAllKProcesso();
			} else {// macroatv
				//conhecimento = ctrl.getAllKAtividade();
			}

			Comboitem comboItem = null;
			Comboitem comboItemSelecionado = null;

			for (Object c : conhecimento) {
				comboItem = comboboxTipo.appendItem(((Conhecimento) c).getNome());
				comboItem.setValue(c);

				String tipoNome = null;
				if (compPP instanceof CompPPProcessoSimples) {
					//tipoNome = ((CompPPProcessoSimples) ctrl.getcompPPrSelecionado()).getTipo().getNome();
				} else {
					//tipoNome = ((CompPPMacroatividade) ctrl.getcompPPSelecionado()).getTipo().getNome();
				}
				if (((Conhecimento) c).getNome().compareTo(tipoNome) == 0) {
					comboItemSelecionado = comboItem;
				}
			}

			comboboxTipo.setSelectedItem(comboItemSelecionado);

		}
	}

	private void configuraTabBox(Tabpanel tabPanelPropriedades, Tabpanel tabPanelRequisitos) {
		Tabbox tabbox = new Tabbox();
		Tabs tabs = new Tabs();
		Tabpanels tabpanels = new Tabpanels();
		Tab tabPropriedades = new Tab("Propriedades");
		Tab tabRequisitos = new Tab("Requisitos");
		
		tabbox.setParent(janela);
		tabs.setParent(tabbox);
		tabPropriedades.setParent(tabs);
		tabRequisitos.setParent(tabs);
		
		tabpanels.setParent(tabbox);
		tabPanelPropriedades.setParent(tabpanels);
		tabPanelRequisitos.setParent(tabpanels);
	}
}

