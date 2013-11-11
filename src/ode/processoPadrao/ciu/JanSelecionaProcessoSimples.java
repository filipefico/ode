package ode.processoPadrao.ciu;

import java.util.ArrayList;
import java.util.Collection;


import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.processoPadrao.cdp.CompPP;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Vbox;
import ode.processoPadrao.cdp.CompPPProcessoSimples;


public class JanSelecionaProcessoSimples extends JanCore {

	private static final long serialVersionUID = -3690385242625438493L;
	private JanelaSimples janela;
	private Listbox listaProcessoSimples;

	// Construtor
	public JanSelecionaProcessoSimples(CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao) {
		
		super(ctrlDefinirProcessoPadrao);
		janela = this;
		listaProcessoSimples = new Listbox();
		
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
		janela.setTitle("Processos Padrão Simples");
		
		// Criando as coleções:
		Collection<CompPP> listaCompPP = ctrl.getAllCompPPComOrdenacao("nome");
		Collection<CompPP> listaCompPPSimples = new ArrayList<CompPP>();
		
		// Separando o collection geral (listaCompPP) por granularidade, para 3 novos collections criados acima
		if(listaCompPP != null){
			for (CompPP compPP : listaCompPP) {

				if(compPP instanceof CompPPProcessoSimples){
					listaCompPPSimples.add(compPP);
				}	
			}
		}
		
		if(listaCompPPSimples.isEmpty() == false){
		
			// Definindo uma Observação:
			Listbox observacao = new Listbox();
			Listitem itemObservacao = new Listitem();
			Listcell cellObservacao = new Listcell(" * Os componentes já definidos não podem ser editados, apenas excluídos");
	
			observacao.setParent(janela);		
			itemObservacao.setParent(observacao);
			itemObservacao.appendChild(cellObservacao);

		}
		
		//----------  trabalhando com processo simples -------------
		
		Vbox vboxsimples = new Vbox();
		vboxsimples.setParent(janela);

		listaProcessoSimples.setCheckmark(true);
		listaProcessoSimples.setParent(vboxsimples);
		
		Listitem itemLista = new Listitem();
		
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
				
				if (listaProcessoSimples.getSelectedItem() != null) {
						CompPP compPPSelecionado = (CompPP) ((Listcell) listaProcessoSimples.getSelectedItem().getChildren().get(0)).getValue();
						ctrl.setCompPPSelecionado(compPPSelecionado);
					}
		}

	}
}
