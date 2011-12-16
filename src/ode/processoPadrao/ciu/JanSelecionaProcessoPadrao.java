package ode.processoPadrao.ciu;

import java.util.Collection;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.processoPadrao.cdp.CompPP;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Vbox;

public class JanSelecionaProcessoPadrao extends JanCore {

	private static final long serialVersionUID = -3690385242625438493L;
	private JanelaSimples janela;
	private Listbox listaProcessoPadrao;

	public JanSelecionaProcessoPadrao(
			CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao) {
		super(ctrlDefinirProcessoPadrao);
		janela = this;
		listaProcessoPadrao = new Listbox();
		try {
			janela.doModal();
		} catch (Exception e) {
			e.printStackTrace();
		}

		configuraElementosJanela();
		janela.mostrar();
	}

	private void configuraElementosJanela() {
		janela.setTitle("Lista de Processos Padrão");

		Vbox vbox = new Vbox();
		Label labelDefinicao = new Label();

		labelDefinicao.setValue("Escolha o Processo Padrão que deseja abrir:");

		vbox.setParent(janela);
		labelDefinicao.setParent(vbox);

		listaProcessoPadrao.setCheckmark(true);
		Listitem itemLista;

		listaProcessoPadrao.setParent(vbox);
		Listhead listHead = new Listhead();

		Collection<CompPP> listaCompPP = ctrl.getAllCompPP();
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

		buttonSelecionar.addEventListener("onClick",
				new EventListnerSelecionar());
	}

	class EventListnerSelecionar implements EventListener {

		public void onEvent(Event arg0) throws Exception {
			if (listaProcessoPadrao.getSelectedItem() == null) {
				Messagebox.show("Selecione um elemento da lista.");
			} else {
				CompPP compPPSelecionado = (CompPP) ((Listcell) listaProcessoPadrao
						.getSelectedItem().getChildren().get(0)).getValue();
				ctrl.setCompPPSelecionado(compPPSelecionado);
				janela.onClose();// fecha a janela
				ctrl.resetJanelaPrincipal();

			}
		}

	}
}
