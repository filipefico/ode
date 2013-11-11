package ode.processoPadrao.ciu;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.principal.cdp.Conhecimento;
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cdp.CompPPMacroatividade;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.CompPPProcessoSimples;
import ode.processoPadrao.cdp.ElementoCompPP;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;

public class JanIndicarSubProcessos extends JanCore {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JanelaSimples janela;
	Listbox listaSubProc;

	public JanIndicarSubProcessos(CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao) {

		super(ctrlDefinirProcessoPadrao);
		janela = this;

		listaSubProc = new Listbox();

		conteudo();
		janela.mostrar();

	}

	private void conteudo() {
		janela.setTitle("Indicar subprocessos a serem considerados.");

		listaSubProc.setCheckmark(true);
		listaSubProc.setMultiple(true);
		listaSubProc.setParent(janela);

		preencheLista();
	}

	private void botaoSalvar() {
		Button buttonSalvar = new Button();
		buttonSalvar.setParent(janela);
		buttonSalvar.setLabel("Salvar");
		buttonSalvar.addEventListener("onClick", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				if (ctrl.getcompPPSelecionado() instanceof CompPPProcessoComplexo) {
					salvarDadosCompPPProcessoComplexo();
				} else {
					salvarDadosCompPPProcessoSimples();
				}
				janela.onClose();
			}
		});
	}

	private void preencheLista() {
		Listitem itemListaSubProc = null;
		Set<Listitem> listItensAMarcar = new HashSet<Listitem>();

		Collection<CompPP> listaElementos = obtemSubElementos();
		if (listaElementos.isEmpty() == true){
			listaSubProc.setCheckmark(false);
			listaSubProc.setMultiple(false);
			itemListaSubProc = new Listitem();
			Listcell listcell;
			
			listcell = new Listcell("Por favor, selecione os tipos dos SubElementos desejados na interface em 'Editar Estrutura' no componente em definição.");
			
			itemListaSubProc.setParent(listaSubProc);
			itemListaSubProc.appendChild(listcell);
		}else{
		
			for (CompPP compPP : listaElementos) {
				if(ctrl.getcompPPSelecionado() instanceof CompPPProcessoComplexo){	
					if(((CompPPProcessoComplexo)ctrl.getcompPPSelecionado()).getProcessosSimples().contains(compPP) == false){
						itemListaSubProc = new Listitem();
						Listcell listcell = new Listcell();
						itemListaSubProc.setParent(listaSubProc);
						itemListaSubProc.appendChild(listcell);
			
						listcell.setLabel(compPP.getNome());
						listcell.setValue(compPP);
					}
				}else{
					if(((CompPPProcessoSimples)ctrl.getcompPPSelecionado()).getMacroAtividades().contains(compPP) == false){
						itemListaSubProc = new Listitem();
						Listcell listcell = new Listcell();
						itemListaSubProc.setParent(listaSubProc);
						itemListaSubProc.appendChild(listcell);
			
						listcell.setLabel(compPP.getNome());
						listcell.setValue(compPP);
					}
				}
				// aqui esta comentado porque no momento não é preciso que apareca marcado nas caixinhas os subelementos que ja fazem parte da composicao do CompPP selecionado.
				/*if (ctrl.getcompPPSelecionado() instanceof CompPPProcessoComplexo) {
					preencheSeCompPPComplexo(itemListaSubProc, listItensAMarcar,compPP);
				}
	
				if (ctrl.getcompPPSelecionado() instanceof CompPPProcessoSimples) {
					preencheSeCompPPSimples(itemListaSubProc, listItensAMarcar,	compPP);
				}*/
	
				// seleciona na lista
				//listaSubProc.setSelectedItems(listItensAMarcar); // marca os itens que já fazem parte da composição do CompPP selecionado
			}
			
			// Preenchendo os Subitens do CompPPBase:
			if(ctrl.getcompPPSelecionado().getCompPPBase() != null){
				if (ctrl.getcompPPSelecionado() instanceof CompPPProcessoComplexo) {
					for(CompPPProcessoSimples simples : ((CompPPProcessoComplexo)(ctrl.getcompPPSelecionado().getCompPPBase())).getProcessosSimples()){
						itemListaSubProc = new Listitem();
						Listcell listcell = new Listcell();
						itemListaSubProc.setParent(listaSubProc);
						itemListaSubProc.appendChild(listcell);
			
						listcell.setLabel(simples.getNome());
						listcell.setValue(simples);
						
						listItensAMarcar.add(itemListaSubProc);
						
						listaSubProc.setSelectedItems(listItensAMarcar);
					}
				}else{
					if(ctrl.getcompPPSelecionado() instanceof CompPPProcessoSimples){
						for(CompPPMacroatividade macroatividade : ((CompPPProcessoSimples)(ctrl.getcompPPSelecionado().getCompPPBase())).getMacroAtividades()){
							itemListaSubProc = new Listitem();
							Listcell listcell = new Listcell();
							itemListaSubProc.setParent(listaSubProc);
							itemListaSubProc.appendChild(listcell);
				
							listcell.setLabel(macroatividade.getNome());
							listcell.setValue(macroatividade);
							
							listItensAMarcar.add(itemListaSubProc);
							
							listaSubProc.setSelectedItems(listItensAMarcar);
						}
					}
				}
			}
			
			botaoSalvar();
			
		}
	}

	protected void preencheVazio(Listitem itemLista, Set<Listitem> itemAMarcar, CompPP compPP) {
		// cria lista com subElementos normais
		Set<CompPPProcessoSimples> processosSimples = ((CompPPProcessoComplexo) ctrl.getcompPPSelecionado()).getProcessosSimples();

		// adiciona subElementos do CompPP Base
		if (ctrl.getcompPPSelecionado().getCompPPBase() != null) {
			processosSimples.addAll(((CompPPProcessoComplexo) ((CompPPProcessoComplexo) ctrl.getcompPPSelecionado()).getCompPPBase()).getProcessosSimples());
		}

		// adiciona os itens na lista
		if (processosSimples.contains(compPP)) {
			itemAMarcar.add(itemLista);
		}
	}
	
	protected void preencheSeCompPPComplexo(Listitem itemLista, Set<Listitem> itemAMarcar, CompPP compPP) {
		// cria lista com subElementos normais
		Set<CompPPProcessoSimples> processosSimples = ((CompPPProcessoComplexo) ctrl.getcompPPSelecionado()).getProcessosSimples();
		
		// adiciona subElementos do CompPP Base
		if (ctrl.getcompPPSelecionado().getCompPPBase() != null) {
			Set<CompPPProcessoSimples> processosSimplesBase = ((CompPPProcessoComplexo) (ctrl.getcompPPSelecionado()).getCompPPBase()).getProcessosSimples();
			processosSimples.addAll(processosSimplesBase);
		}

		// adiciona os itens na lista
		if (processosSimples.contains(compPP)) {
			itemAMarcar.add(itemLista);
		}
	}

	protected void preencheSeCompPPSimples(Listitem itemLista,Set<Listitem> itemAMarcar, CompPP compPP) {
		// cria lista com subElementos normais
		Set<CompPPMacroatividade> macroAtividades = ((CompPPProcessoSimples) ctrl.getcompPPSelecionado()).getMacroAtividades();

		// adiciona subElementos do CompPP Base
		if (ctrl.getcompPPSelecionado().getCompPPBase() != null) {
			Set<CompPPMacroatividade> macroAtividadesBase = ((CompPPProcessoSimples)(ctrl.getcompPPSelecionado()).getCompPPBase()).getMacroAtividades();
			macroAtividades.addAll(macroAtividadesBase);
		}
		// adiciona os itens na lista
		if (macroAtividades.contains(compPP)) {
			itemAMarcar.add(itemLista);
		}
	}

	private void salvarDadosCompPPProcessoComplexo() {
		Set<CompPPProcessoSimples> subProcessos = new HashSet<CompPPProcessoSimples>();
		int quantidadeEngenharia = 0;
		boolean jatemEngenharia = false;
		boolean podeCommitar = true;
		
		// verifica se o componente complexo selecionado já possui entre os subelementos um componente simples de engenharia
		for(CompPPProcessoSimples simples : ((CompPPProcessoComplexo) ctrl.getcompPPSelecionado()).getProcessosSimples()){
			if(simples.getEngenharia() == true){
				jatemEngenharia = true;
				break;
			}
		}

		for (Listitem listitem : (Set<Listitem>) listaSubProc.getSelectedItems()) {
			Listcell listcell = (Listcell) listitem.getChildren().get(0);
			subProcessos.add((CompPPProcessoSimples) listcell.getValue());
			
			if(((CompPPProcessoSimples) listcell.getValue()).getEngenharia() == true){
				quantidadeEngenharia++;
			}
		}
		
		if(quantidadeEngenharia >= 2){
			try {
				Messagebox.show("Por favor, selecione apenas um Processo Simples de Engenharia.", 
								"Informação", Messagebox.OK, Messagebox.INFORMATION);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
		
			if(jatemEngenharia == false){ // caso o processo selecionado não possua um subprocesso de engenharia
				for(CompPPProcessoSimples processoSimples : ((CompPPProcessoComplexo) ctrl.getcompPPSelecionado()).getProcessosSimples()){
					subProcessos.add(processoSimples);
				}

				((CompPPProcessoComplexo) ctrl.getcompPPSelecionado()).setProcessosSimples(subProcessos);
				ctrl.atualizarCompPP(ctrl.getcompPPSelecionado());
			}else{ // caso já possua um processo de engenharia, ira verificar se o usuario nao selecionara outro de engenharia para compor, só pode 1 de engenharia
				
				if(quantidadeEngenharia == 1){
					podeCommitar = false;
				}
				
				if(podeCommitar){
					for(CompPPProcessoSimples processoSimples : ((CompPPProcessoComplexo) ctrl.getcompPPSelecionado()).getProcessosSimples()){
						subProcessos.add(processoSimples);
					}
			
					((CompPPProcessoComplexo) ctrl.getcompPPSelecionado()).setProcessosSimples(subProcessos);
					ctrl.atualizarCompPP(ctrl.getcompPPSelecionado());
				}else{
					try {
						Messagebox.show("Nenhum elemento retornado.", "Informação", Messagebox.OK, Messagebox.INFORMATION);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}
	}

	private void salvarDadosCompPPProcessoSimples() {
		Set<CompPPMacroatividade> macroAtvs = new HashSet<CompPPMacroatividade>();

		for (Listitem listitem : (Set<Listitem>) listaSubProc.getSelectedItems()) {
			Listcell listcell = (Listcell) listitem.getChildren().get(0);
			macroAtvs.add((CompPPMacroatividade) listcell.getValue());
		}

		for(CompPPMacroatividade macroatividade : ((CompPPProcessoSimples) ctrl.getcompPPSelecionado()).getMacroAtividades()){
			macroAtvs.add(macroatividade);
		}
		
		if(macroAtvs.isEmpty() == true){
			try {
				Messagebox.show("O componente complexo em edição já possui entre seus subelementos um Processo Simples de Engenharia. " +
								"Por Favor, selecione apenas processos que não sejam de engenharia.", 
								"Informação", Messagebox.OK, Messagebox.INFORMATION);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			((CompPPProcessoSimples) ctrl.getcompPPSelecionado()).setMacroAtividades(macroAtvs);
			ctrl.atualizarCompPP(ctrl.getcompPPSelecionado());
		}
	}

	private Collection<CompPP> obtemSubElementos() {
		Collection<CompPP> subElementosFiltrados = new HashSet<CompPP>();
		Set<Conhecimento> conhecimento = new HashSet<Conhecimento>();

		// preenche a lista conhecimento.
		for (ElementoCompPP el : ctrl.getcompPPSelecionado().getInterfaceCompPP().getEstruturaCompPP().getElementosCompPP()) {
			conhecimento.add(el.getElementoConhecimento());
		}

		// esse if é somente para CompPPProcessoComplexo
		if (ctrl.getcompPPSelecionado() instanceof CompPPProcessoComplexo) {
			Collection<CompPPProcessoSimples> allSubElementos = ctrl.getAllCompPPProcessoSimples();

			for (CompPPProcessoSimples cPPsimples : allSubElementos) {

				if (conhecimento.contains(cPPsimples.getTipo())) {
					subElementosFiltrados.add(cPPsimples);
				}
			}
		}

		// esse if é somente para CompPPProcessoSimples
		if (ctrl.getcompPPSelecionado() instanceof CompPPProcessoSimples) {
			Collection<CompPPMacroatividade> allSubElementos = ctrl.getAllCompPPMacroAtividade();

			for (CompPPMacroatividade cPPMacroAtv : allSubElementos) {

				if (conhecimento.contains(cPPMacroAtv.getTipo())) {
					subElementosFiltrados.add(cPPMacroAtv);
				}
			}
		}

		return subElementosFiltrados;
	}

}
