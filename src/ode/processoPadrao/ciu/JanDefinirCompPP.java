package ode.processoPadrao.ciu;

import java.util.Collection;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.processoPadrao.cdp.AtividadeProcessoPadrao;
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
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Space;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;

public class JanDefinirCompPP extends JanCore {

	// Declaração de variáveis:
	
	private static final long serialVersionUID = 4849915971877921572L;
	private JanelaSimples janela;
	private boolean setarCompPPEmArvore;
	private Listbox listaEngenhariaSimOuNao;

	public JanDefinirCompPP(CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao, boolean setarCompPPEmArvore) {

		super(ctrlDefinirProcessoPadrao);
		janela = this;
		this.setarCompPPEmArvore = setarCompPPEmArvore;
		listaEngenhariaSimOuNao = new Listbox();

		configuraElementosJanela();
		preencherCombobox(); // Insere os elementos nos combobox's
		janela.mostrar();
		
	}

	// Declaração de variáveis e instanciação:
	
	private Vbox vbox = new Vbox();
	private Hbox hboxGroupbox = new Hbox();
	private Vbox vboxGroupbox = new Vbox();

	private Label labelNome = new Label("Nome");
	private Label labelDescricao = new Label("Descrição");
	private Label labelObjetivo = new Label("Objetivo");
	private Label labelAtividadePadrao = new Label("Atividade Padrão");
	private Label labelTipo = new Label("Tipo");
	private Label labelRequisitos = new Label("Requisitos");
	private Label labelEngenharia = new Label("Este processo simples é de Engenharia?");

	private Textbox txtboxNome = new Textbox();
	private Textbox txtboxDescricao = new Textbox();
	private Textbox txtboxObjetivo = new Textbox();

	private Groupbox groupBNivelGranularidade = new Groupbox();

	private Combobox comboBListaKProcessos = new Combobox();
	private Combobox comboBListaKAtividade = new Combobox();
	
	private Textbox txtboxRequisitos = new Textbox();

	private Checkbox checkCompPPComplexo = new Checkbox("Processo Complexo");
	private Checkbox checkCompPPSimples = new Checkbox("Processo Simples");
	private Checkbox checkCompPPMacro = new Checkbox("Macroatividade");

	private Button buttonSalvar = new Button("Salvar");
	private Button buttonCancelar = new Button("Cancelar");

	
	//Métodos:
	
	private void configuraElementosJanela() {
		janela.setTitle("Definir Componente de Processo Padrão");

		vbox.setParent(janela);
		vbox.setWidth("100%");
		
		// Granularidade:
		groupBNivelGranularidade.setParent(vbox);
		Caption caption = new Caption("Granularidade");
		caption.setParent(groupBNivelGranularidade);
		vboxGroupbox.setParent(groupBNivelGranularidade);
		hboxGroupbox.setParent(vboxGroupbox);
		hboxGroupbox.setWidth("100%");
		new Space().setParent(vbox);

		checkCompPPComplexo.setParent(hboxGroupbox);
		checkCompPPSimples.setParent(hboxGroupbox);
		checkCompPPMacro.setParent(hboxGroupbox);

		checkCompPPComplexo.setChecked(true);
		
		// Nome:
		labelNome.setParent(vbox);
		txtboxNome.setParent(vbox);
		txtboxNome.setWidth("100%");
		new Space().setParent(vbox);

		// Descrição:
		labelDescricao.setParent(vbox);
		txtboxDescricao.setParent(vbox);
		txtboxDescricao.setWidth("100%");
		txtboxDescricao.setRows(3);
		txtboxDescricao.setMultiline(true);
		new Space().setParent(vbox);

		// Objetivo:
		labelObjetivo.setParent(vbox);
		txtboxObjetivo.setParent(vbox);
		txtboxObjetivo.setWidth("100%");
		txtboxObjetivo.setRows(3);
		new Space().setParent(vbox);

		// Eventos:
		EventoCheckbox eventoCheckbox = new EventoCheckbox();
		checkCompPPComplexo.addEventListener("onCheck", eventoCheckbox);
		checkCompPPSimples.addEventListener("onCheck", eventoCheckbox);
		checkCompPPMacro.addEventListener("onCheck", eventoCheckbox);

		labelAtividadePadrao.setParent(vboxGroupbox);
		labelTipo.setParent(vboxGroupbox);

		comboBListaKProcessos.setParent(vboxGroupbox);
		comboBListaKAtividade.setParent(vboxGroupbox);

		
		// Requisitos:
		labelRequisitos.setParent(vbox);
		txtboxRequisitos.setRows(3);
		txtboxRequisitos.setParent(vbox);
		txtboxRequisitos.setWidth("100%");
		txtboxRequisitos.setRows(3);

		buttonSalvar.setParent(janela);
		buttonCancelar.setParent(janela);

		// Definir ação para os botões.
		buttonSalvar.addEventListener("onClick", new EventListenerSalvar());
		buttonCancelar.addEventListener("onClick", new EventListenerCancelar());

		// Esconde alguns itens que serão mostrados opcionalmente no futuro
		labelAtividadePadrao.setVisible(false);
		labelTipo.setVisible(false);
		comboBListaKAtividade.setVisible(false);
		comboBListaKProcessos.setVisible(false);
		listaEngenhariaSimOuNao.setVisible(false);
		labelEngenharia.setVisible(false);
		
		constroiBoxEngenharia();

	}

	void constroiBoxEngenharia(){
		labelEngenharia.setParent(vbox);
		
		Listcell listcellSim = new Listcell("Sim");
		Listcell listcellNao = new Listcell("Não");
		Listitem itemListaSim = new Listitem();
		Listitem itemListaNao = new Listitem();
		
		itemListaSim.setParent(listaEngenhariaSimOuNao);
		itemListaNao.setParent(listaEngenhariaSimOuNao);
		
		itemListaSim.setValue(new String("Sim"));
		itemListaNao.setValue(new String("Não"));
		
		itemListaSim.appendChild(listcellSim);
		itemListaNao.appendChild(listcellNao);
		
		listaEngenhariaSimOuNao.setCheckmark(true);
		listaEngenhariaSimOuNao.setParent(vbox);
	}
	
	class EventoCheckbox implements EventListener {
		@Override
		public void onEvent(Event checkbox) throws Exception {
			
			// Altera o comportamento visual de acordo com o tipo selecionado
			
			checkCompPPComplexo.setChecked(false);
			checkCompPPSimples.setChecked(false);
			checkCompPPMacro.setChecked(false);
			((Checkbox) checkbox.getTarget()).setChecked(true);

			labelAtividadePadrao.setVisible(false);
			labelTipo.setVisible(false);
			comboBListaKAtividade.setVisible(false);
			comboBListaKProcessos.setVisible(false);
			listaEngenhariaSimOuNao.setVisible(false);
			labelEngenharia.setVisible(false);

			if (((Checkbox) checkbox.getTarget()) != checkCompPPComplexo) {
				if (((Checkbox) checkbox.getTarget()) == checkCompPPSimples) {
					labelTipo.setVisible(true);
					comboBListaKProcessos.setVisible(true);
					listaEngenhariaSimOuNao.setVisible(true);
					labelEngenharia.setVisible(true);					
				} else {
					labelAtividadePadrao.setVisible(true);
					comboBListaKAtividade.setVisible(true);
				}
			}
		}
	}

	class EventListenerSalvar implements EventListener {
		@Override
		public void onEvent(Event arg0) throws Exception {

			Object ObjTipo = null;
			Class tipo = CompPPProcessoComplexo.class;
			boolean ehEngenharia = false;
			
			if (checkCompPPSimples.isChecked()) {
				ObjTipo = comboBListaKProcessos.getSelectedItem().getValue();
				tipo = CompPPProcessoSimples.class;
				
				if(listaEngenhariaSimOuNao.getSelectedItem().getValue().toString().compareTo("Sim") == 0){
					ehEngenharia = true;
				}
			}
			
			if (checkCompPPMacro.isChecked()) {
				ObjTipo = comboBListaKAtividade.getSelectedItem().getValue();
				tipo = CompPPMacroatividade.class;
			}

			ctrl.salvarCompPP(txtboxNome.getText(),
							  txtboxDescricao.getText(),
							  txtboxObjetivo.getText(),
							  tipo,
							  txtboxRequisitos.getText(),
							  ObjTipo,
							  setarCompPPEmArvore,
							  ehEngenharia);
			
			janela.onClose(); 
			ctrl.janPrincipal.conteudo();
		}
	}

	class EventListenerCancelar implements EventListener {
		@Override
		public void onEvent(Event arg0) throws Exception {
			janela.onClose(); 
		}
	}

	private void preencherCombobox() {
		Collection<KProcesso> listaKProcesso = ctrl.getAllKProcessoComOrdenacao("nome");
		//Collection<KAtividade> listaKAtividade = ctrl.getAllKAtividadeComOrdenacao("nome");
		Collection<AtividadeProcessoPadrao> listaAtividadePadrao = ctrl.getAllAtividadeProcessoPadrao();

		Comboitem comboItem;

		for (KProcesso kprocesso : listaKProcesso) {
			comboItem = comboBListaKProcessos.appendItem(kprocesso.getNome());
			comboItem.setValue(kprocesso);
		}

		/*for (KAtividade kAtividade : listaKAtividade) {
			comboItem = comboBListaKAtividade.appendItem(kAtividade.getNome());
			comboItem.setValue(kAtividade);
		}*/
		
		for (AtividadeProcessoPadrao atividadePadrao : listaAtividadePadrao) {
			comboItem = comboBListaKAtividade.appendItem(atividadePadrao.getNome());
			comboItem.setValue(atividadePadrao);
		}
	}

}
