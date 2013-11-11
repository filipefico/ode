package ode.processoPadrao.ciu;

import java.util.HashSet;
import java.util.Set;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Vbox;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.processoPadrao.cdp.AtividadeProcessoPadrao;
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cdp.CompPPMacroatividade;
import ode.processoPadrao.cdp.CompPPProcessoSimples;
import ode.processoPadrao.cdp.DependenciaMacroAtividades;
import ode.processoPadrao.ciu.JanDefinirDependencias.EventListnerSelecionar;

public class JanIndicarPreAtividades extends JanCore {
	private JanelaSimples janela;
	private Listbox listaAtividadePadrao;
	private AtividadeProcessoPadrao atividadeSelecionada;
	
	public JanIndicarPreAtividades(CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao, AtividadeProcessoPadrao atividade) {

		super(ctrlDefinirProcessoPadrao);
		
		janela = this;
		listaAtividadePadrao = new Listbox();
		atividadeSelecionada = atividade;
		
		try {
			janela.doModal();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//configuraElementosJanela();
		janela.mostrar();
		
	}
/*
	@SuppressWarnings("deprecation")
	private void configuraElementosJanela() {
		janela.setTitle("Definir Dependências");
		
		Set<AtividadeProcessoPadrao> listaAtividade = new HashSet<AtividadeProcessoPadrao>();
		
		Vbox vboxatividade = new Vbox();
		vboxatividade.setParent(janela);

		preencheLista(listaAtividade);
		
		listaAtividadePadrao.setMultiple(true);
		listaAtividadePadrao.setCheckmark(true);
		listaAtividadePadrao.setParent(vboxatividade);
		
		Listitem itemLista = new Listitem();
		
		if(listaCompPPMacroatividade != null){
			if(listaCompPPMacroatividade.isEmpty()){  // Caso esteja vazio o collection 
				listaProcessoMacroatividade.setCheckmark(false);
				
				Listitem itemListaMacro = new Listitem();
				Listcell listcell = new Listcell();
				
				itemListaMacro.setParent(listaProcessoMacroatividade);
				itemListaMacro.appendChild(listcell);
				
				listcell.setLabel("Não existem outros componentes de processo de macroatividade que compõem o processo simples.");
				
			}else{ // Caso não esteja
				
				for (CompPP compPP : listaCompPPMacroatividade) {
					itemLista = new Listitem();
					Listcell listcell = new Listcell();
			
					itemLista.setParent(listaProcessoMacroatividade);
					itemLista.appendChild(listcell);
			
					listcell.setLabel(compPP.getNome());
					listcell.setValue(compPP);
				}
			}
		}
		

		// ----- Criando o botão selecionar -----
		
		Button buttonSelecionar = new Button();
		buttonSelecionar.setLabel("Selecionar");
		buttonSelecionar.setParent(janela);
		buttonSelecionar.addEventListener("onClick", new EventListnerSelecionar());
		
		
	}

	public void preencheLista(Set<AtividadeProcessoPadrao> listaAtividade){
		for(AtividadeProcessoPadrao Atv : ((CompPPMacroatividade)ctrl.getcompPPSelecionado()).get){
			listaMacro.add(macro);
		}
		
		listaMacro.remove(macroatividadeSelecionada);
	}
	
	class EventListnerSelecionar implements EventListener {

		public void onEvent(Event arg0) throws Exception {

				janela.onClose();
				ctrl.resetJanelaPrincipal();
				
				if(listaProcessoMacroatividade.getSelectedItems() != null) {
					Set<Listitem> itens = listaProcessoMacroatividade.getSelectedItems();
					DependenciaMacroAtividades dependenciaBase = new DependenciaMacroAtividades();
					macroatividadeSelecionada.setBaseDependenciaMacroAtividade(dependenciaBase);
					Set<CompPPMacroatividade> preAtividades = new HashSet<CompPPMacroatividade>();
					
					for(Listitem item : itens){
						CompPPMacroatividade macro = (CompPPMacroatividade) ((Listcell) item.getChildren().get(0)).getValue();
						preAtividades.add(macro);
					}
					
					macroatividadeSelecionada.getBaseDependenciaMacroAtividade().setPreAtividade(preAtividades);
					
					ctrl.atualizarCompPP(ctrl.getcompPPSelecionado());
				}
		}

	}*/
}
