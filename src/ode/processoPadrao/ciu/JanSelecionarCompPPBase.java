package ode.processoPadrao.ciu;

import java.util.ArrayList;
import java.util.Collection;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.principal.cdp.Conhecimento;
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cdp.CompPPMacroatividade;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.CompPPProcessoSimples;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;

public class JanSelecionarCompPPBase extends JanCore {
	JanelaSimples janela;
	private Listbox listaProcessoPadrao;

	public JanSelecionarCompPPBase(
			CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao) {

		super(ctrlDefinirProcessoPadrao);
		janela = this;
		listaProcessoPadrao = new Listbox();

		configuraElementosJanela();
		janela.mostrar();

	}

	private void configuraElementosJanela() {
		janela.setTitle("Selecionar CompPP Base");

		listaProcessoPadrao.setCheckmark(true);
		Listitem itemLista;

		listaProcessoPadrao.setParent(janela);
		Listhead listHead = new Listhead();
		Collection<CompPP> listaCompPP = new ArrayList<CompPP>();

		preencheListaCompPPsFinalizado(listaCompPP);

		for (CompPP compPP : listaCompPP) {

			// não adiciona na lista o compPP atual.
			if (compPP.getNome().compareTo(
					ctrl.getcompPPSelecionado().getNome()) != 0) {

				Conhecimento tipoFor = null, tipoSelec = null;

				// coleta os tipos para comparacao;
				if (compPP instanceof CompPPProcessoComplexo == false) {
					if (compPP instanceof CompPPProcessoSimples) {
						tipoFor = ((CompPPProcessoSimples) compPP).getTipo();
						tipoSelec = ((CompPPProcessoSimples) ctrl
								.getcompPPSelecionado()).getTipo();
					} else {// macro atividade
						tipoFor = ((CompPPMacroatividade) compPP).getTipo();
						tipoSelec = ((CompPPMacroatividade) ctrl
								.getcompPPSelecionado()).getTipo();
					}
				}

				// efetua a comparação e adiciona na lista se forem do mesmo
				// tipo.
				if (tipoFor.getNome().compareTo(tipoSelec.getNome()) == 0) {
					itemLista = new Listitem();
					Listcell listcell = new Listcell();
					itemLista.setParent(listaProcessoPadrao);
					itemLista.appendChild(listcell);

					listcell.setLabel(compPP.getNome());
					listcell.setValue(compPP);
				}
			}
		}

		Button buttonSelecionar = new Button();
		buttonSelecionar.setLabel("Selecionar");

		buttonSelecionar.setParent(janela);

		buttonSelecionar.addEventListener("onClick", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {

				if (listaProcessoPadrao.getSelectedItem() != null) {
					Messagebox.show(
							"A estrutura do CompPP selecionado será alterada",
							"Alteração", Messagebox.OK + Messagebox.CANCEL,
							Messagebox.QUESTION, new EventListener() {

								public void onEvent(Event evt) {
									switch (((Integer) evt.getData())
											.intValue()) {
									case Messagebox.OK:
										try {
											alterarEstruturaCompPP();
											ctrl.atualizarCompPP(ctrl
													.getcompPPSelecionado());

										} catch (CloneNotSupportedException e) {
											e.printStackTrace();
										}
										janela.onClose();
										break;
									}
								}

							});
				} else {
					Messagebox.show("Selecione um elemento da lista.");
				}
			}
		});

	}

	private void preencheListaCompPPsFinalizado(Collection listaCompPP) {
		listaCompPP.addAll(retornaCompPPsFinalizados(ctrl
				.getcompPPSelecionado()));
	}

	private Collection retornaCompPPsFinalizados(CompPP compPP) {

		if (compPP instanceof CompPPProcessoComplexo) {
			return retornaCompPPsFinalizados((CompPPProcessoComplexo) compPP);

		} else if (compPP instanceof CompPPProcessoSimples) {
			return retornaCompPPsFinalizados((CompPPProcessoSimples) compPP);

		} else {
			return retornaCompPPsFinalizados((CompPPMacroatividade) compPP);
		}
	}

	private Collection retornaCompPPsFinalizados(CompPPProcessoComplexo compPP) {
		return ctrl.getAllCompPPFinalizado(CompPPProcessoComplexo.class);
	}

	private Collection retornaCompPPsFinalizados(CompPPProcessoSimples compPP) {
		return ctrl.getAllCompPPFinalizado(CompPPProcessoSimples.class);
		// verifica se são do mesmo tipo
		/*
		 * if (((CompPPProcessoSimples) ctrl.getcompPPSelecionado())
		 * .getTipo().getNome() .compareTo(compPPsimples.getTipo().getNome()) ==
		 * 0) {
		 * 
		 * listaCompPP.add(compPPsimples); }
		 */
	}

	private Collection retornaCompPPsFinalizados(CompPPMacroatividade compPP) {
		return ctrl.getAllCompPPFinalizado(CompPPMacroatividade.class);
		// verifica se são do mesmo tipo
		/*
		 * if (((CompPPMacroatividade) ctrl.getcompPPSelecionado())
		 * .getTipo().getNome() .compareTo(compPPmacroatv.getTipo().getNome())
		 * == 0) {
		 * 
		 * listaCompPP.add(compPPmacroatv); }
		 */
	}

	void alterarEstruturaCompPP() throws CloneNotSupportedException {

		if (ctrl.getcompPPSelecionado() instanceof CompPPProcessoComplexo) {

			CompPPProcessoComplexo compPPBase = (CompPPProcessoComplexo) ((Listcell) listaProcessoPadrao
					.getSelectedItem().getChildren().get(0)).getValue();

			CompPPProcessoComplexo copia = (CompPPProcessoComplexo) compPPBase
					.clone();

			((CompPPProcessoComplexo) ctrl.getcompPPSelecionado())
					.setProcessosSimples(copia.getProcessosSimples());

			ctrl.salvarListaCompPP(((CompPPProcessoComplexo) ctrl
					.getcompPPSelecionado()).getProcessosSimples());

			ctrl.getcompPPSelecionado().setCompPPBase(compPPBase);
			copia = null;

		} else if (ctrl.getcompPPSelecionado() instanceof CompPPProcessoSimples) {

			CompPPProcessoSimples compPPBase = (CompPPProcessoSimples) ((Listcell) listaProcessoPadrao
					.getSelectedItem().getChildren().get(0)).getValue();

			CompPPProcessoSimples copia = (CompPPProcessoSimples) compPPBase
					.clone();

			((CompPPProcessoSimples) ctrl.getcompPPSelecionado())
					.setMacroAtividades(copia.getMacroAtividades());

			ctrl.salvarListaCompPP(((CompPPProcessoSimples) ctrl
					.getcompPPSelecionado()).getMacroAtividades());

			ctrl.getcompPPSelecionado().setCompPPBase(compPPBase);
			copia = null;

		} else { /* macroatividade */

			CompPPMacroatividade compPPBase = (CompPPMacroatividade) ((Listcell) listaProcessoPadrao
					.getSelectedItem().getChildren().get(0)).getValue();

			CompPPMacroatividade copia = (CompPPMacroatividade) compPPBase
					.clone();

			((CompPPMacroatividade) ctrl.getcompPPSelecionado())
					.setAtividadeProcessoPadrao(copia
							.getAtividadeProcessoPadrao());
			ctrl.getcompPPSelecionado().setCompPPBase(compPPBase);

			copia = null;

		}

	}
}
