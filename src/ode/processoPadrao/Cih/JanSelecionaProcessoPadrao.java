package ode.processoPadrao.Cih;

import java.util.Collection;
import java.util.List;

import org.zkoss.zk.ui.SuspendNotAllowedException;
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

import ode.nucleo.cci.CtrlBase;
import ode.nucleo.crud.cih.JanelaSimples;
import ode.processoPadrao.Cci.CtrlDefinirProcessoPadrao;
import ode.processoPadrao.Cdp.CompPP;
import ode.processoPadrao.Cih.JanDefinirCompPP.EventListnerSalvar;

public class JanSelecionaProcessoPadrao {
	private CtrlDefinirProcessoPadrao ctrl;
	private JanelaSimples janela;
	private Listbox listaProcessoPadrao;

	public JanSelecionaProcessoPadrao(
			CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao,
			JanelaSimples JanelaSimples) {
		ctrl = ctrlDefinirProcessoPadrao;
		janela = JanelaSimples;
		listaProcessoPadrao = new Listbox();
		try {
			janela.doModal();
		} catch (Exception e) {
			e.printStackTrace();
		}

		configuracaoBasica();
		configuraElementosJanela();
		janela.mostrar();
	}

	private void configuraElementosJanela() {
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

	private void configuracaoBasica() {
		janela.setTitle("Lista de Processos Padrão");
		janela.setWidth("450px");
		janela.setBorder("normal");
		janela.setClosable(true);
		janela.setPosition("&quot;center;&quot;;");
		janela.setSizable(true);
		janela.setMaximizable(true);
	}

	class EventListnerSelecionar implements EventListener {

		public void onEvent(Event arg0) throws Exception {
			if (listaProcessoPadrao.getSelectedItem() == null) {
				Messagebox.show("Selecione um elemento da lista.");
			} else {
				CompPP compPPSelecionado = (CompPP) ((Listcell) listaProcessoPadrao
						.getSelectedItem().getChildren().get(0)).getValue();
				ctrl.setCompPPSelecionado(compPPSelecionado);
				janela.onClose();//fecha a janela
				
			}
		}

	}
}
