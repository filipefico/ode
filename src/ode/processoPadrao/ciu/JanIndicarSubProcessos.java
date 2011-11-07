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
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listitem;

import com.lowagie.text.ListItem;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.principal.cdp.Conhecimento;
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.CompPPProcessoSimples;
import ode.processoPadrao.cdp.ElementoCompPP;

public class JanIndicarSubProcessos {
	CtrlDefinirProcessoPadrao ctrl;
	JanelaSimples janela;
	Listbox listaSubProc;

	public JanIndicarSubProcessos(
			CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao,
			JanelaSimples janelaSimples) {

		ctrl = ctrlDefinirProcessoPadrao;
		janela = janelaSimples;

		listaSubProc = new Listbox();

		configuracaoBasica();
		conteudo();
		janela.mostrar();

	}

	private void configuracaoBasica() {
		janela.setTitle("Indicar subprocessos a serem considerados.");
		janela.setWidth("450px");
		janela.setHeight("600px");
		janela.setBorder("normal");
		janela.setClosable(true);
		janela.setPosition("&quot;center;&quot;;");
		janela.setSizable(true);
		janela.setMaximizable(true);
	}

	private void conteudo() {
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
				salvarDados();
				janela.onClose();
			}

			private void salvarDados() {
				if (ctrl.getcompPPSelecionado() instanceof CompPPProcessoComplexo) {
					salvarDadosCompPPProcessoComplexo();
				}
			}
		});
	}

	private void preencheLista() {
		Listitem itemLista = null;
		Set<Listitem> itemAMarcar = new HashSet<Listitem>();

		for (CompPP compPP : obtemSubElementos()) {
			itemLista = new Listitem();
			Listcell listcell = new Listcell();
			itemLista.setParent(listaSubProc);
			itemLista.appendChild(listcell);

			listcell.setLabel(compPP.getNome());
			listcell.setValue(compPP);

			if (ctrl.getcompPPSelecionado() instanceof CompPPProcessoComplexo) {
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
				
				//adiciona os itens na lista
				if (processosSimples.contains(compPP)) {
					itemAMarcar.add(itemLista);
				}
			}

			//seleciona na lista
			listaSubProc.setSelectedItems(itemAMarcar); // marca os itens que já
														// estao selecionados
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
		return subElementosFiltrados;
	}

}
