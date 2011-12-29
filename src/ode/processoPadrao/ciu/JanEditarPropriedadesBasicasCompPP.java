package ode.processoPadrao.ciu;

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

public class JanEditarPropriedadesBasicasCompPP extends JanCore {

	private static final long serialVersionUID = -3624797736187568796L;
	private JanelaSimples janela;

	private Textbox textBoxNome;
	private Textbox textBoxDescricao;
	private Textbox textBoxObjetivos;
	private Textbox textBoxRequisito;
	private Label labelTipo;
	private Combobox comboboxTipo;

	public JanEditarPropriedadesBasicasCompPP(
			CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao) {
		super(ctrlDefinirProcessoPadrao);
		janela = this;

		configuraElementosJanela();
		janela.mostrar();
	}

	private void configuraElementosJanela() {
		janela.setTitle("Editar propriedades básicas");

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
		// add tipo
		if (compPP instanceof CompPPProcessoComplexo == false) {
			if (compPP instanceof CompPPProcessoSimples) {
				((CompPPProcessoSimples) compPP)
						.setTipo((KProcesso) comboboxTipo.getSelectedItem()
								.getValue());
			} else {
				((CompPPMacroatividade) compPP)
						.setTipo((KAtividade) comboboxTipo.getSelectedItem()
								.getValue());
			}
		}
		ctrl.atualizarCompPP((CompPPMacroatividade) compPP);
	}

	private void configuraTabPanelRequisitos(Tabpanel tabPanelRequisitos) {
		Vbox vbox = new Vbox();
		vbox.setParent(tabPanelRequisitos);
		Label labelRequisito = new Label();
		textBoxRequisito = new Textbox();
		textBoxRequisito.setWidth("100%");

		labelRequisito.setValue("Requisitos");

		labelRequisito.setParent(vbox);
		textBoxRequisito.setParent(vbox);
		vbox.setWidth("100%");

		carregaDadosRequisitos();
	}

	private void carregaDadosRequisitos() {
		textBoxRequisito.setText(ctrl.getcompPPSelecionado()
				.getRequisitoCompPP());
	}

	private void configuraTabPanelPropriedades(Tabpanel tabPanelPropriedades) {

		Vbox vbox = new Vbox();
		vbox.setParent(tabPanelPropriedades);
		vbox.setWidth("100%");

		Label labelNome = new Label();
		Label labelDescricao = new Label();
		Label labelObjetivos = new Label();

		labelNome.setValue("Nome");
		labelDescricao.setValue("Descrição");
		labelObjetivos.setValue("Objetivos");

		textBoxNome = new Textbox();
		textBoxDescricao = new Textbox();
		textBoxObjetivos = new Textbox();
		comboboxTipo = new Combobox();

		textBoxNome.setWidth("100%");
		textBoxDescricao.setWidth("100%");
		textBoxObjetivos.setWidth("100%");

		labelNome.setParent(vbox);
		textBoxNome.setParent(vbox);
		new Space().setParent(vbox);

		labelDescricao.setParent(vbox);
		textBoxDescricao.setParent(vbox);

		new Space().setParent(vbox);
		labelObjetivos.setParent(vbox);
		textBoxObjetivos.setParent(vbox);
		new Space().setParent(vbox);

		labelTipo = new Label();
		labelTipo.setVisible(false);
		labelTipo.setParent(vbox);
		labelTipo.setValue("Tipo");
		comboboxTipo.setParent(vbox);
		comboboxTipo.setVisible(false);

		carregaDadosPropriedades();

	}

	private void carregaDadosPropriedades() {
		CompPP compPP = ctrl.getcompPPSelecionado();
		textBoxNome.setText(compPP.getNome());
		textBoxDescricao.setText(compPP.getDescricao());
		textBoxObjetivos.setText(compPP.getObjetivo());
		Collection conhecimento = null;

		if (compPP instanceof CompPPProcessoComplexo == false) {
			comboboxTipo.setVisible(true);
			labelTipo.setVisible(true);

			if (compPP instanceof CompPPProcessoSimples) {
				conhecimento = ctrl.getAllKProcesso();
			} else {// macroatv
				conhecimento = ctrl.getAllKAtividade();
			}

			Comboitem comboItem = null;
			Comboitem comboItemSelecionado = null;

			for (Object c : conhecimento) {
				comboItem = comboboxTipo.appendItem(((Conhecimento) c)
						.getNome());
				comboItem.setValue(c);

				String tipoNome = null;
				if (compPP instanceof CompPPProcessoSimples) {
					tipoNome = ((CompPPProcessoSimples) ctrl
							.getcompPPSelecionado()).getTipo().getNome();
				} else {
					tipoNome = ((CompPPMacroatividade) ctrl
							.getcompPPSelecionado()).getTipo().getNome();
				}
				if (((Conhecimento) c).getNome().compareTo(tipoNome) == 0) {
					comboItemSelecionado = comboItem;
				}
			}

			comboboxTipo.setSelectedItem(comboItemSelecionado);

		}
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

}
