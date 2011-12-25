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

public class JanIndicarSubProcessos extends JanCore {
	JanelaSimples janela;
	Listbox listaSubProc;

	public JanIndicarSubProcessos(
			CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao) {

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

		botaoSalvar();
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

		for (CompPP compPP : obtemSubElementos()) {
			itemListaSubProc = new Listitem();
			Listcell listcell = new Listcell();
			itemListaSubProc.setParent(listaSubProc);
			itemListaSubProc.appendChild(listcell);

			listcell.setLabel(compPP.getNome());
			listcell.setValue(compPP);

			if (ctrl.getcompPPSelecionado() instanceof CompPPProcessoComplexo) {
				preencheSeCompPPComplexo(itemListaSubProc, listItensAMarcar,
						compPP);
			}

			if (ctrl.getcompPPSelecionado() instanceof CompPPProcessoSimples) {
				preencheSeCompPPSimples(itemListaSubProc, listItensAMarcar,
						compPP);
			}

			// seleciona na lista
			listaSubProc.setSelectedItems(listItensAMarcar); // marca os itens
																// que já
			// estao selecionados
		}
	}

	protected void preencheSeCompPPComplexo(Listitem itemLista,
			Set<Listitem> itemAMarcar, CompPP compPP) {
		// cria lista com subElementos normais
		Set<CompPPProcessoSimples> processosSimples = ((CompPPProcessoComplexo) ctrl
				.getcompPPSelecionado()).getProcessosSimples();

		// adiciona subElementos do CompPP Base
		if (ctrl.getcompPPSelecionado().getCompPPBase() != null) {
			processosSimples
					.addAll(((CompPPProcessoComplexo) ((CompPPProcessoComplexo) ctrl
							.getcompPPSelecionado()).getCompPPBase())
							.getProcessosSimples());

		}

		// adiciona os itens na lista
		if (processosSimples.contains(compPP)) {
			itemAMarcar.add(itemLista);
		}
	}

	protected void preencheSeCompPPSimples(Listitem itemLista,
			Set<Listitem> itemAMarcar, CompPP compPP) {
		// cria lista com subElementos normais
		Set<CompPPMacroatividade> macroAtividades = ((CompPPProcessoSimples) ctrl
				.getcompPPSelecionado()).getMacroAtividades();

		// adiciona subElementos do CompPP Base
		if (ctrl.getcompPPSelecionado().getCompPPBase() != null) {
			macroAtividades
					.addAll(((CompPPProcessoSimples) ((CompPPProcessoSimples) ctrl
							.getcompPPSelecionado()).getCompPPBase())
							.getMacroAtividades());
		}
		// adiciona os itens na lista
		if (macroAtividades.contains(compPP)) {
			itemAMarcar.add(itemLista);
		}
	}

	private void salvarDadosCompPPProcessoComplexo() {
		Set<CompPPProcessoSimples> subProcessos = new HashSet<CompPPProcessoSimples>();

		for (Listitem listitem : (Set<Listitem>) listaSubProc
				.getSelectedItems()) {

			Listcell listcell = (Listcell) listitem.getChildren().get(0);
			subProcessos.add((CompPPProcessoSimples) listcell.getValue());
		}

		((CompPPProcessoComplexo) ctrl.getcompPPSelecionado())
				.setProcessosSimples(subProcessos);

		ctrl.atualizarCompPP(ctrl.getcompPPSelecionado());
	}

	private void salvarDadosCompPPProcessoSimples() {
		Set<CompPPMacroatividade> macroAtvs = new HashSet<CompPPMacroatividade>();

		for (Listitem listitem : (Set<Listitem>) listaSubProc
				.getSelectedItems()) {
			Listcell listcell = (Listcell) listitem.getChildren().get(0);
			macroAtvs.add((CompPPMacroatividade) listcell.getValue());
		}

		((CompPPProcessoSimples) ctrl.getcompPPSelecionado())
				.setMacroAtividades(macroAtvs);

		ctrl.atualizarCompPP(ctrl.getcompPPSelecionado());
	}

	private Collection<CompPP> obtemSubElementos() {
		Collection<CompPP> subElementosFiltrados = new HashSet<CompPP>();
		Set<Conhecimento> conhecimento = new HashSet<Conhecimento>();

		// preenche a lista conhecimento.
		for (ElementoCompPP el : ctrl.getcompPPSelecionado()
				.getInterfaceCompPP().getEstruturaCompPP().getElementosCompPP()) {

			conhecimento.add(el.getElementoConhecimento());

		}

		// esse if é somente para CompPPProcessoComplexo
		if (ctrl.getcompPPSelecionado() instanceof CompPPProcessoComplexo) {
			Collection<CompPPProcessoSimples> allSubElementos = ctrl
					.getAllCompPPProcessoSimples();
			for (CompPPProcessoSimples cPPsimples : allSubElementos) {

				if (conhecimento.contains(cPPsimples.getTipo())) {
					subElementosFiltrados.add(cPPsimples);
				}

			}

		}

		// esse if é somente para CompPPProcessoSimples
		if (ctrl.getcompPPSelecionado() instanceof CompPPProcessoSimples) {
			Collection<CompPPMacroatividade> allSubElementos = ctrl
					.getAllCompPPMacroAtividade();
			for (CompPPMacroatividade cPPMacroAtv : allSubElementos) {

				if (conhecimento.contains(cPPMacroAtv.getTipo())) {
					subElementosFiltrados.add(cPPMacroAtv);
				}

			}

		}

		return subElementosFiltrados;
	}

}
