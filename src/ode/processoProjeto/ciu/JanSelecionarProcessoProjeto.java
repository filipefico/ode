package ode.processoProjeto.ciu;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.Box;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.processoProjeto.cdp.CompPPr;

import org.python.antlr.runtime.tree.Tree;
import org.springframework.web.servlet.tags.form.LabelTag;
import org.zkoss.xel.taglib.Taglib;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Vbox;

public class JanSelecionarProcessoProjeto extends JanCore{
	
	private JanelaSimples janela;
	private Listbox listaProcessoProjeto;
	
	// Construtor
		public JanSelecionarProcessoProjeto(CtrlDefinirProcessoProjeto ctrlDefinirProcessoProjeto) {
			
			super(ctrlDefinirProcessoProjeto);
			janela = this;
			listaProcessoProjeto = new Listbox();
			
			try {
				janela.doModal();
			} catch (Exception e) {
				e.printStackTrace();
			}

			configuraElementosJanela();
			janela.mostrar();
		}
		
		@SuppressWarnings("deprecation")
		private void configuraElementosJanela() {
			janela.setTitle("Processos de Projeto");
					
			/*// Criando as coleções:
			Collection<CompPP> listaCompPP = ctrl.getAllCompPPComOrdenacao("nome");
			Collection<CompPP> listaCompPPComplexo = new ArrayList<CompPP>();
			
			// Separando o collection geral (listaCompPP) por granularidade, para 3 novos collections criados acima
			if(listaCompPP != null){
				for (CompPP compPP : listaCompPP) {
					if(compPP instanceof CompPPProcessoComplexo){
						listaCompPPComplexo.add(compPP);
					}
				}
			}*/
			
			Collection<CompPPr> listaCompPPr = ctrl.getAllCompPPr();
			
			
			if(listaCompPPr.isEmpty() == false){
			
				// Definindo uma Observação:
				Listbox observacao = new Listbox();
				Listitem itemObservacao = new Listitem();
				Listcell cellObservacao = new Listcell(" * Os componentes já definidos não podem ser editados, apenas excluídos");
		
				observacao.setParent(janela);		
				itemObservacao.setParent(observacao);
				itemObservacao.appendChild(cellObservacao);
				
			}
			
			//----------  trabalhando com processo complexo -------------
			
			Vbox vbox = new Vbox();
			vbox.setParent(janela);

			listaProcessoProjeto.setCheckmark(true);
			listaProcessoProjeto.setParent(vbox);
			
			Listitem itemLista = new Listitem();
			
			if(listaCompPPr != null){     // Evitar segmentation fault
				
				if(listaCompPPr.isEmpty()){  // Caso esteja vazio o collection 
					
					listaProcessoProjeto.setCheckmark(false);
					Listcell listcell = new Listcell();
					
					itemLista.setParent(listaProcessoProjeto);
					itemLista.appendChild(listcell);
					
					listcell.setLabel("Não existem componentes de processo complexo cadastrados.");
					
				}else{ // Caso não esteja
					
					for (CompPPr compPPr : listaCompPPr) {
						itemLista = new Listitem();
						Listcell listcell = new Listcell();
				
						itemLista.setParent(listaProcessoProjeto);
						itemLista.appendChild(listcell);
				
						listcell.setLabel(compPPr.getPadraoBase().getNome());
						listcell.setValue(compPPr);

						Listcell listcellSituacao = new Listcell();
						itemLista.appendChild(listcellSituacao);
						
						// Verifica se o componente está definido ou não:
						if (((CompPPr) listcell.getValue()).getPadraoBase().isDefinicaoConcluida() == true){
							listcellSituacao.setLabel("Definido");
						}else{
							listcellSituacao.setLabel("Em definição");
						}
					}
				}
			}
			
			
			// ----- Criando o botão selecionar -----
			
			Button buttonSelecionar = new Button();
			buttonSelecionar.setLabel("Selecionar");

			buttonSelecionar.setParent(janela);

			buttonSelecionar.addEventListener("onClick", new EventListnerSelecionar());
		}

		class EventListnerSelecionar implements EventListener {

			public void onEvent(Event arg0) throws Exception {
				
				
				try {
					Messagebox.show("Processo criado com sucesso!","Informação", Messagebox.OK, Messagebox.INFORMATION);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (listaProcessoProjeto.getSelectedItem() != null) {
					CompPPr compPPrSelecionado = (CompPPr) ((Listcell) listaProcessoProjeto.getSelectedItem().getChildren().get(0)).getValue();
					ctrl.setCompPPrSelecionado(compPPrSelecionado);
					
					ctrl.salvarCompPPr(compPPrSelecionado);
				}
				
				janela.onClose();
				ctrl.resetJanelaPrincipal();
					
			}

		}

}



























