package ode.processoPadrao.ciu;

import java.util.Collection;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.processoPadrao.cdp.CompPPMacroatividade;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.CompPPProcessoSimples;
import ode.processoPadrao.ciu.JanDefinirCompPP.EventListenerCancelar;
import ode.processoPadrao.ciu.JanDefinirCompPP.EventListenerSalvar;
import ode.processoPadrao.ciu.JanDefinirCompPP.EventoCheckbox;

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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Space;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;

public class JanDefinirAtividadePadrao extends JanCore{

	private static final long serialVersionUID = 7239538606905889592L;
	private JanelaSimples janela;
	private Listbox listaMarcoSimOuNao;
	
	public JanDefinirAtividadePadrao(CtrlDefinirProcessoPadrao ctrl) {
		super(ctrl);
		janela = this;
		listaMarcoSimOuNao = new Listbox();
		

		configuraElementosJanela();
		preencherCombobox();
		janela.mostrar();
	}

	// Declaração de variáveis e instanciação:
	
		private Vbox vbox = new Vbox();
		private Hbox hboxGroupbox = new Hbox();
		private Vbox vboxGroupbox = new Vbox();

		private Label labelNome = new Label("Nome");
		private Label labelDescricao = new Label("Descrição");
		private Label labelTipo = new Label("Selecione o tipo da atividade");
		private Label labelMarco = new Label("Esta atividade é considerada um Marco de projeto?");

		private Textbox txtboxNome = new Textbox();
		private Textbox txtboxDescricao = new Textbox();

		private Combobox comboBListaKProcessos = new Combobox();
		private Combobox comboBListaKAtividade = new Combobox();
		
		private Button buttonSalvar = new Button("Salvar");
		private Button buttonCancelar = new Button("Cancelar");

	
		//Métodos:
		
		private void configuraElementosJanela() {
			janela.setTitle("Definir Atividade Padrão");

			vbox.setParent(janela);
			vbox.setWidth("100%");
			
			// Nome:
			labelNome.setParent(vbox);
			txtboxNome.setParent(vbox);
			txtboxNome.setWidth("100%");
			new Space().setParent(vbox);

			// Descrição:
			/*labelDescricao.setParent(vbox);
			txtboxDescricao.setParent(vbox);
			txtboxDescricao.setWidth("100%");
			txtboxDescricao.setRows(3);
			txtboxDescricao.setMultiline(true);
			new Space().setParent(vbox);*/

			labelTipo.setVisible(true);
			comboBListaKAtividade.setVisible(true);
			
			labelTipo.setParent(vbox);
			comboBListaKAtividade.setParent(vbox);

			buttonSalvar.setParent(janela);
			buttonCancelar.setParent(janela);

			// Definir ação para os botões.
			buttonSalvar.addEventListener("onClick", new EventListenerSalvar());
			buttonCancelar.addEventListener("onClick", new EventListenerCancelar());
			
			constroiBoxMarco();
		}

		void constroiBoxMarco(){
			labelMarco.setParent(vbox);
			
			Listcell listcellSim = new Listcell("Sim");
			Listcell listcellNao = new Listcell("Não");
			Listitem itemListaSim = new Listitem();
			Listitem itemListaNao = new Listitem();
			
			itemListaSim.setParent(listaMarcoSimOuNao);
			itemListaNao.setParent(listaMarcoSimOuNao);
			
			itemListaSim.setValue(new String("Sim"));
			itemListaNao.setValue(new String("Não"));
			
			itemListaSim.appendChild(listcellSim);
			itemListaNao.appendChild(listcellNao);
			
			listaMarcoSimOuNao.setCheckmark(true);
			listaMarcoSimOuNao.setParent(vbox);
		}
		
		class EventListenerSalvar implements EventListener {
			@Override
			public void onEvent(Event arg0) throws Exception {

				KAtividade kAtividade = null;
				boolean ehMarco = false;

				if(listaMarcoSimOuNao.getSelectedItem().getValue().toString().compareTo("Sim") == 0){
					ehMarco = true;
				}
			
				kAtividade = (KAtividade) comboBListaKAtividade.getSelectedItem().getValue();				
				
				if(ctrl.existeAtividadePadraoBanco(kAtividade) == 0){ // não existe uma atividade padrão com a mesma katividade cadastrada
					if(kAtividade.getPreAtividades() != null || kAtividade.getSubAtividades() != null){
						if(kAtividade.getPreAtividades().isEmpty() == false || kAtividade.getSubAtividades().isEmpty() == false){ // a atividade possui sub ou preatividades
							try {		
								Messagebox.show("Esta atividade possui sub ou pré-atividades cadastradas em conhecimento. Portanto serão criadas atividades padrão automaticamente para tais.");
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					ctrl.criarAtividadePadrao(txtboxNome.getText(),
							  //txtboxDescricao.getText(),
							  kAtividade.getDescricao(),
							  kAtividade,
							  ehMarco);
					
					janela.onClose(); 
					ctrl.janPrincipal.conteudo();
					
				}else{ // existe
					try {		
						Messagebox.show("Já existe uma atividade padrão cadastrada relacionada com o mesmo tipo em conhecimento. Isso talvez se deva ao anterior cadastro de uma atividade padrão que possui como sub ou pré-atividade a atividade atual em cadastro, com isso, a atividade foi criada automaticamente. Por favor, selecione outro tipo.");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}				
								
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
