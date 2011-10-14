package ode.processoPadrao.ciu;

import java.util.Collection;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KProcesso;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;

public class JanDefinirCompPP {

	private CtrlDefinirProcessoPadrao ctrl;
	private JanelaSimples janela;

	public JanDefinirCompPP(
			CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao,
			JanelaSimples JanelaSimples) {

		ctrl = ctrlDefinirProcessoPadrao;
		janela = JanelaSimples;

		configuracaoBasica();
		configuraElementosJanela();
		preencherCombobox();// insere os elementos nos combobox's
		janela.mostrar();
	}

	private void configuracaoBasica() {
		janela.setTitle("Definir CompPP");
		janela.setWidth("450px");
		janela.setBorder("normal");
		janela.setClosable(true);
		janela.setPosition("&quot;center;&quot;;");
		janela.setSizable(true);
		janela.setMaximizable(true);
	}

	private Vbox vbox = new Vbox();
	private Vbox vboxGroupbox = new Vbox();

	private Label labelNome = new Label();
	private Label labelDescricao = new Label();
	private Label labelObjetivo = new Label();
	private Label labelTipo = new Label();

	private Textbox txtboxNome = new Textbox();
	private Textbox txtboxDescricao = new Textbox();
	private Textbox txtboxObjetivo = new Textbox();

	private Groupbox groupBNivelGranularidade = new Groupbox();

	private Combobox comboBGranularidade = new Combobox();
	private Combobox comboBListaTipoDeProcessoSimples = new Combobox();
	private Combobox comboBListaTipoDeMacroAtividade = new Combobox();

	private Button buttonSalvar = new Button();
	private Button buttonCancelar = new Button();

	private void configuraElementosJanela() {
		labelNome.setValue("Nome");
		labelDescricao.setValue("Descrição");
		labelObjetivo.setValue("Objetivo");
		labelTipo.setValue("Tipo");
		buttonSalvar.setLabel("Salvar");
		buttonCancelar.setLabel("Cancelar");

		txtboxDescricao.setRows(9);
		txtboxDescricao.setMultiline(true);

		vbox.setParent(janela);
		labelNome.setParent(vbox);
		txtboxNome.setParent(vbox);

		labelDescricao.setParent(vbox);
		txtboxDescricao.setParent(vbox);

		labelObjetivo.setParent(vbox);
		txtboxObjetivo.setParent(vbox);

		groupBNivelGranularidade.setParent(vbox);
		vboxGroupbox.setParent(groupBNivelGranularidade);

		comboBGranularidade.setParent(vboxGroupbox);
		comboBGranularidade.appendItem("Processo Complexo");
		comboBGranularidade.appendItem("Processo Simples");
		comboBGranularidade.appendItem("Macroatividade");

		labelTipo.setParent(vboxGroupbox);

		comboBListaTipoDeProcessoSimples.setParent(vboxGroupbox);
		comboBListaTipoDeMacroAtividade.setParent(vboxGroupbox);

		buttonSalvar.setParent(janela);
		buttonCancelar.setParent(janela);

		// definir ação para os botões.
		buttonSalvar.addEventListener("onClick", new EventListnerSalvar());
		buttonCancelar.addEventListener("onClick", new EventListnerCancelar());
	}

	class EventListnerSalvar implements EventListener {
		@Override
		public void onEvent(Event arg0) throws Exception {

			Object ObjTipo = null;
			if (comboBGranularidade.getText().compareTo("Processo Simples") == 0) {
				ObjTipo = comboBListaTipoDeProcessoSimples.getSelectedItem()
						.getValue();
			}
			if (comboBGranularidade.getText().compareTo("Macroatividade") == 0) {
				ObjTipo = comboBListaTipoDeMacroAtividade.getSelectedItem()
						.getValue();
			}
			ctrl.salvarCompPP(txtboxNome.getText(), txtboxDescricao.getText(),
					txtboxObjetivo.getText(), comboBGranularidade.getText(),
					ObjTipo);
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
		Collection<KProcesso> listaKProcesso = ctrl.getAllKProcesso();
		Collection<KAtividade> listaKAtividade = ctrl.getAllKAtividade();

		Comboitem comboItem;

		for (KProcesso kprocesso : listaKProcesso) {
			comboItem = comboBListaTipoDeProcessoSimples.appendItem(kprocesso
					.getNome());
			comboItem.setValue(kprocesso);
		}

		for (KAtividade kAtividade : listaKAtividade) {
			comboItem = comboBListaTipoDeMacroAtividade.appendItem(kAtividade
					.getNome());
			comboItem.setValue(kAtividade);
		}
	}

}
