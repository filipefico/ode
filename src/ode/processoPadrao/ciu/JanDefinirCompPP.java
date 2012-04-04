package ode.processoPadrao.ciu;

import java.util.Collection;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.processoPadrao.cdp.CompPPMacroatividade;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.CompPPProcessoSimples;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Space;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;

public class JanDefinirCompPP extends JanCore {

	private static final long serialVersionUID = 4849915971877921572L;
	private JanelaSimples janela;
	private boolean setarCompPPEmArvore;

	public JanDefinirCompPP(
			CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao,
			boolean setarCompPPEmArvore) {

		super(ctrlDefinirProcessoPadrao);
		janela = this;
		this.setarCompPPEmArvore = setarCompPPEmArvore;

		configuraElementosJanela();
		preencherCombobox();// insere os elementos nos combobox's
		janela.mostrar();
		
	}

	private Vbox vbox = new Vbox();
	private Hbox hboxGroupbox = new Hbox();
	private Vbox vboxGroupbox = new Vbox();

	private Label labelNome = new Label();
	private Label labelDescricao = new Label();
	private Label labelObjetivo = new Label();
	private Label labelTipo = new Label();

	private Textbox txtboxNome = new Textbox();
	private Textbox txtboxDescricao = new Textbox();
	private Textbox txtboxObjetivo = new Textbox();

	private Groupbox groupBNivelGranularidade = new Groupbox();

	// private Combobox comboBGranularidade = new Combobox();
	private Combobox comboBListaKProcessos = new Combobox();
	private Combobox comboBListaKAtividade = new Combobox();

	private Label labelRequisitos = new Label();
	private Textbox txtboxRequisitos = new Textbox();

	private Checkbox checkCompPPComplexo = new Checkbox();
	private Checkbox checkCompPPSimples = new Checkbox();
	private Checkbox checkCompPPMacro = new Checkbox();

	private Button buttonSalvar = new Button();
	private Button buttonCancelar = new Button();

	private void configuraElementosJanela() {
		janela.setTitle("Definir CompPP");

		labelNome.setValue("Nome");
		labelDescricao.setValue("Descrição");
		labelObjetivo.setValue("Objetivo");
		labelTipo.setValue("Tipo");
		buttonSalvar.setLabel("Salvar");
		buttonCancelar.setLabel("Cancelar");

		txtboxDescricao.setRows(3);
		txtboxDescricao.setMultiline(true);

		vbox.setParent(janela);
		vbox.setWidth("100%");
		labelNome.setParent(vbox);
		txtboxNome.setParent(vbox);
		txtboxNome.setWidth("100%");
		new Space().setParent(vbox);

		labelDescricao.setParent(vbox);
		txtboxDescricao.setParent(vbox);
		txtboxDescricao.setWidth("100%");
		new Space().setParent(vbox);

		labelObjetivo.setParent(vbox);
		txtboxObjetivo.setParent(vbox);
		txtboxObjetivo.setWidth("100%");
		txtboxObjetivo.setRows(3);
		new Space().setParent(vbox);

		groupBNivelGranularidade.setParent(vbox);
		Caption capition = new Caption();
		capition.setLabel("Granularidade");
		capition.setParent(groupBNivelGranularidade);
		vboxGroupbox.setParent(groupBNivelGranularidade);
		hboxGroupbox.setParent(vboxGroupbox);
		hboxGroupbox.setWidth("100%");

		new Space().setParent(vbox);

		// comboBGranularidade.setParent(vboxGroupbox);
		// comboBGranularidade.appendItem("Processo Complexo");
		// comboBGranularidade.appendItem("Processo Simples");
		// comboBGranularidade.appendItem("Macroatividade");

		checkCompPPComplexo.setParent(hboxGroupbox);
		checkCompPPSimples.setParent(hboxGroupbox);
		checkCompPPMacro.setParent(hboxGroupbox);

		checkCompPPComplexo.setLabel("Processo Complexo");
		checkCompPPSimples.setLabel("Processo Simples");
		checkCompPPMacro.setLabel("Macro Atividade");

		checkCompPPComplexo.setChecked(true);

		EventoCheckbox eventoCheckbox = new EventoCheckbox();
		checkCompPPComplexo.addEventListener("onCheck", eventoCheckbox);
		checkCompPPSimples.addEventListener("onCheck", eventoCheckbox);
		checkCompPPMacro.addEventListener("onCheck", eventoCheckbox);

		labelTipo.setParent(vboxGroupbox);

		comboBListaKProcessos.setParent(vboxGroupbox);
		comboBListaKAtividade.setParent(vboxGroupbox);

		labelRequisitos.setValue("Requisitos");
		labelRequisitos.setParent(vbox);
		txtboxRequisitos.setRows(3);
		txtboxRequisitos.setParent(vbox);
		txtboxRequisitos.setWidth("100%");
		txtboxRequisitos.setRows(3);

		buttonSalvar.setParent(janela);
		buttonCancelar.setParent(janela);

		// definir ação para os botões.
		buttonSalvar.addEventListener("onClick", new EventListnerSalvar());
		buttonCancelar.addEventListener("onClick", new EventListnerCancelar());

		// esconde alguns itens que serão mostrados opcionalmente no futuro
		labelTipo.setVisible(false);
		comboBListaKAtividade.setVisible(false);
		comboBListaKProcessos.setVisible(false);

	}

	class EventoCheckbox implements EventListener {
		@Override
		public void onEvent(Event checkbox) throws Exception {
			// altera o comportamento visual de acordo com o tipo selecionado
			checkCompPPComplexo.setChecked(false);
			checkCompPPSimples.setChecked(false);
			checkCompPPMacro.setChecked(false);
			((Checkbox) checkbox.getTarget()).setChecked(true);

			labelTipo.setVisible(false);
			comboBListaKAtividade.setVisible(false);
			comboBListaKProcessos.setVisible(false);

			if (((Checkbox) checkbox.getTarget()) != checkCompPPComplexo) {
				labelTipo.setVisible(true);
				if (((Checkbox) checkbox.getTarget()) == checkCompPPSimples) {
					comboBListaKProcessos.setVisible(true);
				} else {
					comboBListaKAtividade.setVisible(true);
				}
			}

		}

	}

	class EventListnerSalvar implements EventListener {
		@Override
		public void onEvent(Event arg0) throws Exception {

			Object ObjTipo = null;
			Class tipo = CompPPProcessoComplexo.class;
			if (checkCompPPSimples.isChecked()) {
				// if
				// (comboBGranularidade.getText().compareTo("Processo Simples")
				// == 0) {
				ObjTipo = comboBListaKProcessos.getSelectedItem().getValue();
				tipo = CompPPProcessoSimples.class;
			}
			if (checkCompPPMacro.isChecked()) {
				// if (comboBGranularidade.getText().compareTo("Macroatividade")
				// == 0) {
				ObjTipo = comboBListaKAtividade.getSelectedItem().getValue();
				tipo = CompPPMacroatividade.class;
			}

			ctrl.salvarCompPP(txtboxNome.getText(), txtboxDescricao.getText(),
					txtboxObjetivo.getText(), tipo, txtboxRequisitos.getText(),
					ObjTipo, setarCompPPEmArvore);
			janela.onClose();// fecha a janela
		}
	}

	class EventListnerCancelar implements EventListener {
		@Override
		public void onEvent(Event arg0) throws Exception {
			janela.onClose();// fecha a janela
		}
	}

	private void preencherCombobox() {
		Collection<KProcesso> listaKProcesso = ctrl.getAllKProcessoComOrdenacao("nome");
		Collection<KAtividade> listaKAtividade = ctrl.getAllKAtividadeComOrdenacao("nome");

		Comboitem comboItem;

		for (KProcesso kprocesso : listaKProcesso) {
			comboItem = comboBListaKProcessos.appendItem(kprocesso.getNome());
			comboItem.setValue(kprocesso);
		}

		for (KAtividade kAtividade : listaKAtividade) {
			comboItem = comboBListaKAtividade.appendItem(kAtividade.getNome());
			comboItem.setValue(kAtividade);
		}
	}

}
