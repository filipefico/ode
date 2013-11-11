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
import ode.processoPadrao.cdp.CompPPMacroatividade;

public class JanSelecionaProcessoMacroatividade extends JanCore {

	private static final long serialVersionUID = -3690385242625438493L;
	private JanelaSimples janela;
	private Listbox listaProcessoMacroatividade;

	// Construtor
	public JanSelecionaProcessoMacroatividade(CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao) {
		
		super(ctrlDefinirProcessoPadrao);
		janela = this;
		listaProcessoMacroatividade = new Listbox();
		
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
		janela.setTitle("Processos Padrão de Macroatividade");
		
		// Criando as coleções:
		Collection<CompPP> listaCompPP = ctrl.getAllCompPPComOrdenacao("nome");
		Collection<CompPP> listaCompPPMacroatividade = new ArrayList<CompPP>();
		
		// Filtrando do collection geral (listaCompPP) por granularidade, apenas os de macroatividades
		if(listaCompPP != null){
			for (CompPP compPP : listaCompPP) {
				if(compPP instanceof CompPPMacroatividade){
					listaCompPPMacroatividade.add(compPP);
				}
			}
		}
		
		if(listaCompPPMacroatividade.isEmpty() == false){
		
			// Definindo uma Observação:
			Listbox observacao = new Listbox();
			Listitem itemObservacao = new Listitem();
			Listcell cellObservacao = new Listcell(" * Os componentes já definidos não podem ser editados, apenas excluídos");
			
			observacao.setParent(janela);		
			itemObservacao.setParent(observacao);
			itemObservacao.appendChild(cellObservacao);
			
		}
		
		//----------  trabalhando com processo de macroatividade -------------

		
		Vbox vboxmacroatividade = new Vbox();
		vboxmacroatividade.setParent(janela);

		listaProcessoMacroatividade.setCheckmark(true);
		listaProcessoMacroatividade.setParent(vboxmacroatividade);
		
		Listitem itemLista = new Listitem();
		
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
				
				if(listaProcessoMacroatividade.getSelectedItem() != null) {
					CompPP compPPSelecionado = (CompPP) ((Listcell) listaProcessoMacroatividade.getSelectedItem().getChildren().get(0)).getValue();
					ctrl.setCompPPSelecionado(compPPSelecionado);
				}
		}

	}
}
