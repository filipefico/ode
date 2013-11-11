package ode.processoPadrao.ciu;

import java.util.ArrayList;
import java.util.Collection;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.processoPadrao.cdp.CompPP;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Vbox;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.CompPPProcessoSimples;
import ode.processoPadrao.cdp.CompPPMacroatividade;

public class JanSelecionarVisualizarProcesso extends JanCore{


	private static final long serialVersionUID = 543154L;
	private JanelaSimples janela;
	private Listbox listaProcessoComplexo;
	private Listbox listaProcessoSimples;
	private Listbox listaProcessoMacroatividade;
	
	// Construtor:
	
	public JanSelecionarVisualizarProcesso(CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao) {
		
		super(ctrlDefinirProcessoPadrao);
		
		janela = this;
		listaProcessoComplexo = new Listbox();
		listaProcessoSimples = new Listbox();
		listaProcessoMacroatividade = new Listbox();
		
		configuraElementosJanela();
		janela.mostrar();
	}

	private void configuraElementosJanela() {
		janela.setTitle("Selecione um Processo para Visualizar");
		
		// Criando as labels
		Label labelDefinicao = new Label();
		Label labelDefinicaoComplexo = new Label();
		Label labelDefinicaoSimples = new Label();
		Label labelDefinicaoMacroatividade = new Label();

		// Setando as labels
		labelDefinicaoComplexo.setValue("Processos Complexo:");
		labelDefinicaoSimples.setValue("Processos Simples:");
		labelDefinicaoMacroatividade.setValue("Processos de Macroatividade:");

		// Criando as coleções
		Collection<CompPP> listaCompPP = ctrl.getAllCompPPComOrdenacao("nome");
		Collection<CompPP> listaCompPPComplexo = new ArrayList<CompPP>();
		Collection<CompPP> listaCompPPSimples = new ArrayList<CompPP>();
		Collection<CompPP> listaCompPPMacroatividade = new ArrayList<CompPP>();
		
		// separando o collection geral (listaCompPP) por granularidade, para 3 novos collections criados acima
		if(listaCompPP != null){
			for (CompPP compPP : listaCompPP) {
				if(compPP instanceof CompPPProcessoComplexo){
					listaCompPPComplexo.add(compPP);
				}
					
				if(compPP instanceof CompPPProcessoSimples){
					listaCompPPSimples.add(compPP);
				}
						
				if(compPP instanceof CompPPMacroatividade){
					listaCompPPMacroatividade.add(compPP);
				}
			}
		}
		
		
		
		
		//----------  trabalhando com processo complexo -------------
		
		
		Vbox vbox = new Vbox();
		vbox.setParent(janela);
		labelDefinicaoComplexo.setParent(vbox);
		
		listaProcessoComplexo.setCheckmark(true);
		listaProcessoComplexo.setParent(vbox);
		
		Listitem itemLista = new Listitem();
		
		if(listaCompPPComplexo != null){     // Evitar segmentation fault
			
			if(listaCompPPComplexo.isEmpty()){  // Caso esteja vazio o collection 
				
				listaProcessoComplexo.setCheckmark(false);
				Listcell listcell = new Listcell();
				
				itemLista.setParent(listaProcessoComplexo);
				itemLista.appendChild(listcell);
				
				listcell.setLabel("Não existem componentes de processo complexo cadastrados.");
				
			}else{ // Caso não esteja
				
				for (CompPP compPP : listaCompPPComplexo) {
					itemLista = new Listitem();
					Listcell listcell = new Listcell();
			
					itemLista.setParent(listaProcessoComplexo);
					itemLista.appendChild(listcell);
			
					listcell.setLabel(compPP.getNome());
					listcell.setValue(compPP);
					
					Listcell listcellSituacao = new Listcell();
					itemLista.appendChild(listcellSituacao);
					
					// Verifica se o componente está definido ou não:
					if (((CompPP) listcell.getValue()).isDefinicaoConcluida() == true){
						listcellSituacao.setLabel("Definido");
					}else{
						listcellSituacao.setLabel("Em definição");
					}
				}
			}
		}
		
		
		

		//----------  trabalhando com processo simples -------------

		Vbox vboxsimples = new Vbox();
		vboxsimples.setParent(janela);
		labelDefinicaoSimples.setParent(vboxsimples);

		listaProcessoSimples.setCheckmark(true);
		listaProcessoSimples.setParent(vboxsimples);

		
		if(listaCompPPSimples != null){
			
			if(listaCompPPSimples.isEmpty()){  // Caso esteja vazio o collection 
				
				listaProcessoSimples.setCheckmark(false);
				
				Listcell listcell = new Listcell();
				Listitem itemListaSimples = new Listitem();
				
				itemListaSimples.setParent(listaProcessoSimples);
				itemListaSimples.appendChild(listcell);
				
				listcell.setLabel("Não existem componentes de processo simples cadastrados.");
				
			}else{ // Caso não esteja
				
				for (CompPP compPP : listaCompPPSimples) {
					itemLista = new Listitem();
					Listcell listcell = new Listcell();
			
					itemLista.setParent(listaProcessoSimples);
					itemLista.appendChild(listcell);
			
					listcell.setLabel(compPP.getNome());
					listcell.setValue(compPP);
					
					Listcell listcellSituacao = new Listcell();
					itemLista.appendChild(listcellSituacao);
					
					// Verifica se o componente está definido ou não:
					if (((CompPP) listcell.getValue()).isDefinicaoConcluida() == true){
						listcellSituacao.setLabel("Definido");
					}else{
						listcellSituacao.setLabel("Em definição");
					}
				}
			}
		}
		
		
		
		//----------  trabalhando com processo de macroatividade -------------
		
		Vbox vboxmacroatividade = new Vbox();
		vboxmacroatividade.setParent(janela);
		labelDefinicaoMacroatividade.setParent(vboxmacroatividade);

		listaProcessoMacroatividade.setCheckmark(true);
		listaProcessoMacroatividade.setParent(vboxmacroatividade);
		
		if(listaCompPPMacroatividade != null){
			
			if(listaCompPPMacroatividade.isEmpty()){  // Caso esteja vazio o collection 
				
				listaProcessoMacroatividade.setCheckmark(false);
				
				Listitem itemListaMacro = new Listitem();
				Listcell listcell = new Listcell();
				
				itemListaMacro.setParent(listaProcessoMacroatividade);
				itemListaMacro.appendChild(listcell);
				
				listcell.setLabel("Não existem componentes de processo de macroatividade cadastrados.");
				
			}else{ // Caso não esteja
			
				for (CompPP compPP : listaCompPPMacroatividade) {
					itemLista = new Listitem();
					Listcell listcell = new Listcell();
			
					itemLista.setParent(listaProcessoMacroatividade);
					itemLista.appendChild(listcell);
			
					listcell.setLabel(compPP.getNome());
					listcell.setValue(compPP);

					Listcell listcellSituacao = new Listcell();
					itemLista.appendChild(listcellSituacao);
					
					// Verifica se o componente está definido ou não:
					if (((CompPP) listcell.getValue()).isDefinicaoConcluida() == true){
						listcellSituacao.setLabel("Definido");
					}else{
						listcellSituacao.setLabel("Em definição");
					}
					
				}
			}
		}
		
		
		
		
		// BOTÕES
		
		// ----- Criando o botão selecionar -----
		
		Button buttonSelecionar = new Button();
		buttonSelecionar.setLabel("Selecionar");

		buttonSelecionar.setParent(janela);

		buttonSelecionar.addEventListener("onClick", new EventListnerSelecionar());
		
		
		// ----- Criando o botão Limpar -----
		
		Button buttonLimpar = new Button();
		buttonLimpar.setLabel("Limpar");

		buttonLimpar.setParent(janela);

		buttonLimpar.addEventListener("onClick", new EventListnerLimpar());
	}

	class EventListnerSelecionar implements EventListener {

		public void onEvent(Event arg0) throws Exception {
			
			// Caso o usuário marque mais de 1 item:
			if ((listaProcessoComplexo.getSelectedIndex() != -1 && listaProcessoSimples.getSelectedIndex() != -1) ||
				(listaProcessoComplexo.getSelectedIndex() != -1 && listaProcessoMacroatividade.getSelectedIndex() != -1) ||
				(listaProcessoSimples.getSelectedIndex() != -1 && listaProcessoMacroatividade.getSelectedIndex() != -1)){
				
					Messagebox.show("Selecione apenas um elemento");
					limpaSelecao();
					
			}else{
				
				janela.onClose();
				ctrl.resetJanelaPrincipal();
				
				if (listaProcessoComplexo.getSelectedItem() != null) {
					CompPP compPPSelecionado = (CompPP) ((Listcell) listaProcessoComplexo.getSelectedItem().getChildren().get(0)).getValue();
					ctrl.setCompPPSelecionadoParaVisualisar(compPPSelecionado);
				}else
					if (listaProcessoSimples.getSelectedItem() != null) {
						CompPP compPPSelecionado = (CompPP) ((Listcell) listaProcessoSimples.getSelectedItem().getChildren().get(0)).getValue();
						ctrl.setCompPPSelecionadoParaVisualisar(compPPSelecionado);
					}else				
						if (listaProcessoMacroatividade.getSelectedItem() != null) {
							CompPP compPPSelecionado = (CompPP) ((Listcell) listaProcessoMacroatividade.getSelectedItem().getChildren().get(0)).getValue();
							ctrl.setCompPPSelecionadoParaVisualisar(compPPSelecionado);
						}
			}
		}

	}
	
	class EventListnerLimpar implements EventListener {
		public void onEvent(Event arg0) throws Exception {
			limpaSelecao();
		}
	}
	
	protected void limpaSelecao(){
		listaProcessoComplexo.setSelectedIndex(-1);
		listaProcessoSimples.setSelectedIndex(-1);
		listaProcessoMacroatividade.setSelectedIndex(-1);
		listaProcessoComplexo.setSelectedItem(null);
		listaProcessoSimples.setSelectedItem(null);
		listaProcessoMacroatividade.setSelectedItem(null);
	}
}
