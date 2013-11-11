package ode.processoPadrao.ciu;

import java.util.ArrayList;
import java.util.Collection;
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
import ode.conhecimento.principal.cdp.Conhecimento;
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cdp.CompPPMacroatividade;
import ode.processoPadrao.cdp.CompPPProcessoSimples;
import ode.processoPadrao.cdp.DependenciaMacroAtividades;
import ode.processoPadrao.ciu.JanSelecionaProcessoMacroatividade.EventListnerSelecionar;

public class JanDefinirDependencias extends JanCore {
	private static final long serialVersionUID = -4206190222266107301L;
	private JanelaSimples janela;
	private Listbox listaProcessoMacroatividade;
	private CompPPMacroatividade macroatividadeSelecionada;
	
	public JanDefinirDependencias(CtrlDefinirProcessoPadrao ctrl, CompPPMacroatividade macroAtv) {
		super(ctrl);
		janela = this;
		listaProcessoMacroatividade = new Listbox();
		macroatividadeSelecionada = macroAtv;
		
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
		janela.setTitle("Selecione as pré-atividades para a macroatividade selecionada");
		
		Set<CompPPMacroatividade> listaCompPPMacroatividade = new HashSet<CompPPMacroatividade>();
		
		Vbox vboxmacroatividade = new Vbox();
		vboxmacroatividade.setParent(janela);

		preencheLista(listaCompPPMacroatividade);
		
		listaProcessoMacroatividade.setMultiple(true);
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

	public void preencheLista(Set<CompPPMacroatividade> listaMacro){
		for(CompPPMacroatividade macro : ((CompPPProcessoSimples)ctrl.getcompPPSelecionado()).getMacroAtividades()){
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
					dependenciaBase.setAtividadeBase(macroatividadeSelecionada);
					dependenciaBase.setCompPPProcessoSimples((CompPPProcessoSimples)ctrl.getcompPPSelecionado());
					
					Set<CompPPMacroatividade> preAtividades = new HashSet<CompPPMacroatividade>();
					
					for(Listitem item : itens){
						CompPPMacroatividade macro = (CompPPMacroatividade) ((Listcell) item.getChildren().get(0)).getValue();
						preAtividades.add(macro);
					}
					
					macroatividadeSelecionada.getBaseDependenciaMacroAtividade().setPreAtividade(preAtividades);
					((CompPPProcessoSimples)ctrl.getcompPPSelecionado()).getDependenciaMacroAtividades().add(dependenciaBase);
					
					ctrl.salvarDependecia(dependenciaBase);
					
					
					ctrl.atualizarCompPP(ctrl.getcompPPSelecionado());
				}
		}

	}

	

}
