package ode.processoPadrao.ciu;

import java.util.ArrayList;
import java.util.Collection;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
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

		preencheListaCompPP(listaCompPP);

		for (CompPP compPP : listaCompPP) {

			itemLista = new Listitem();
			Listcell listcell = new Listcell();
			itemLista.setParent(listaProcessoPadrao);
			itemLista.appendChild(listcell);

			listcell.setLabel(compPP.getNome());
			listcell.setValue(compPP);
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
											// ctrl.salvarCompPP(ctrl.getcompPPSelecionado());
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

	private void preencheListaCompPP(Collection listaCompPP) {
		if (ctrl.getcompPPSelecionado() instanceof CompPPProcessoComplexo) {

			listaCompPP.addAll(ctrl.getAllCompPPProcessoComplexo());

		} else if (ctrl.getcompPPSelecionado() instanceof CompPPProcessoSimples) {

			for (CompPPProcessoSimples compPPsimples : ctrl
					.getAllCompPPProcessoSimples()) {

				// verifica se são do mesmo tipo
				if (((CompPPProcessoSimples) ctrl.getcompPPSelecionado())
						.getTipo().getNome()
						.compareTo(compPPsimples.getTipo().getNome()) == 0) {

					listaCompPP.add(compPPsimples);
				}
			}

		} else if (ctrl.getcompPPSelecionado() instanceof CompPPMacroatividade) {

			for (CompPPMacroatividade compPPmacroatv : ctrl
					.getAllCompPPMacroAtividade()) {

				if (((CompPPMacroatividade) ctrl.getcompPPSelecionado())
						.getTipo().getNome()
						.compareTo(compPPmacroatv.getTipo().getNome()) == 0) {

					listaCompPP.add(compPPmacroatv);
				}
			}

		}
	}

	void alterarEstruturaCompPP() throws CloneNotSupportedException {

		if (ctrl.getcompPPSelecionado() instanceof CompPPProcessoComplexo) {

			CompPPProcessoComplexo compPPBase = (CompPPProcessoComplexo) ((Listcell) listaProcessoPadrao
					.getSelectedItem().getChildren().get(0)).getValue();

			CompPPProcessoComplexo copia = (CompPPProcessoComplexo) compPPBase
					.clone();
			((CompPPProcessoComplexo) ctrl.getcompPPSelecionado())
					.setProcessosSimples(copia.getProcessosSimples());
			copia = null;

		} else if (ctrl.getcompPPSelecionado() instanceof CompPPProcessoSimples) {

			CompPPProcessoSimples compPPBase = (CompPPProcessoSimples) ((Listcell) listaProcessoPadrao
					.getSelectedItem().getChildren().get(0)).getValue();

			CompPPProcessoSimples copia = (CompPPProcessoSimples) compPPBase
					.clone();
			((CompPPProcessoSimples) ctrl.getcompPPSelecionado())
					.setMacroAtividades(copia.getMacroAtividades());
			copia = null;

		} else { /* macroatividade */

			CompPPMacroatividade compPPBase = (CompPPMacroatividade) ((Listcell) listaProcessoPadrao
					.getSelectedItem().getChildren().get(0)).getValue();

			CompPPMacroatividade copia = (CompPPMacroatividade) compPPBase
					.clone();
			((CompPPMacroatividade) ctrl.getcompPPSelecionado())
					.setAtividadeProcessoPadrao(copia
							.getAtividadeProcessoPadrao());
			copia = null;

		}

	}
}
