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

import ode.processoPadrao.cdp.AtividadeProcessoPadrao;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.CompPPProcessoSimples;
import ode.processoPadrao.cdp.CompPPMacroatividade;
import ode.processoPadrao.ciu.JanSelecionarVisualizarProcesso.EventListnerLimpar;
import ode.processoPadrao.ciu.JanSelecionarVisualizarProcesso.EventListnerSelecionar;

public class JanSelecionarVisualizarAtividade extends JanCore{

	private JanelaSimples janela;
	private Listbox listaAtividadePadrao;
	
	public JanSelecionarVisualizarAtividade(CtrlDefinirProcessoPadrao ctrl) {
		
		super(ctrl);
		janela = this;
		listaAtividadePadrao = new Listbox();
		
		configuraElementosJanela();
		janela.mostrar();
		
	}

	private void configuraElementosJanela() {
		janela.setTitle("Selecione uma Atividade Padrão para Visualizar");
		
		// Criando as labels
		Label labelDefinicao = new Label("Atividade Padrão");

		// Criando as coleções
		Collection<AtividadeProcessoPadrao> listaAtividades = ctrl.getAllAtividadeProcessoPadrao();
		
		
		//----------  trabalhando com processo complexo -------------
		
		
		Vbox vbox = new Vbox();
		vbox.setParent(janela);
		labelDefinicao.setParent(vbox);
		
		listaAtividadePadrao.setCheckmark(true);
		listaAtividadePadrao.setParent(vbox);
		
		Listitem itemLista = new Listitem();
		
		if(listaAtividades != null){     // Evitar segmentation fault
			
			if(listaAtividades.isEmpty()){  // Caso esteja vazio o collection 
				
				listaAtividadePadrao.setCheckmark(false);
				Listcell listcell = new Listcell();
				
				itemLista.setParent(listaAtividadePadrao);
				itemLista.appendChild(listcell);
				
				listcell.setLabel("Não existem Atividades Padrão cadastradas.");
				
			}else{ // Caso não esteja
				
				for (AtividadeProcessoPadrao atividade : listaAtividades) {
					itemLista = new Listitem();
					Listcell listcell = new Listcell();
			
					itemLista.setParent(listaAtividadePadrao);
					itemLista.appendChild(listcell);
			
					listcell.setLabel(atividade.getNome());
					listcell.setValue(atividade);
					
					Listcell listcellTipo = new Listcell();
					itemLista.appendChild(listcellTipo);
					
					if(atividade.getTipo() != null){
						listcellTipo.setLabel(atividade.getTipo().toString());
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
	}

	class EventListnerSelecionar implements EventListener {

		public void onEvent(Event arg0) throws Exception {
			janela.onClose();
			ctrl.resetJanelaPrincipal();
			
			if (listaAtividadePadrao.getSelectedItem() != null) {
				AtividadeProcessoPadrao atividadeSelecionada = (AtividadeProcessoPadrao) ((Listcell) listaAtividadePadrao.getSelectedItem().getChildren().get(0)).getValue();
				ctrl.setAtividadeSelecionadaParaVisualisar(atividadeSelecionada);
						
			}
		}
	}
	
}
