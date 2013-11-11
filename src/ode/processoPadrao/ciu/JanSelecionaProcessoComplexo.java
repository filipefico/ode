package ode.processoPadrao.ciu;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.Box;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.processoPadrao.cdp.CompPP;

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
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.CompPPProcessoSimples;
import ode.processoPadrao.cdp.CompPPMacroatividade;

public class JanSelecionaProcessoComplexo extends JanCore {

	private static final long serialVersionUID = -3690385242625438493L;
	private JanelaSimples janela;
	private Listbox listaProcessoComplexo;

	// Construtor
	public JanSelecionaProcessoComplexo(CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao) {
		
		super(ctrlDefinirProcessoPadrao);
		janela = this;
		listaProcessoComplexo = new Listbox();
		
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
		janela.setTitle("Processos Padrão Complexo");
				
		// Criando as coleções:
		Collection<CompPP> listaCompPP = ctrl.getAllCompPPComOrdenacao("nome");
		Collection<CompPP> listaCompPPComplexo = new ArrayList<CompPP>();
		
		// Separando o collection geral (listaCompPP) por granularidade, para 3 novos collections criados acima
		if(listaCompPP != null){
			for (CompPP compPP : listaCompPP) {
				if(compPP instanceof CompPPProcessoComplexo){
					listaCompPPComplexo.add(compPP);
				}
			}
		}
		
		if(listaCompPPComplexo.isEmpty() == false){
		
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
				
				if (listaProcessoComplexo.getSelectedItem() != null) {
					CompPP compPPSelecionado = (CompPP) ((Listcell) listaProcessoComplexo.getSelectedItem().getChildren().get(0)).getValue();
					ctrl.setCompPPSelecionado(compPPSelecionado);
				}
		}

	}

}
