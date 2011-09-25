package ode.processoPadrao.Cih;

import java.util.Collection;
import java.util.Set;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Window;

import ode.nucleo.crud.cih.JanelaSimples;
import ode.processoPadrao.Cci.CtrlDefinirProcessoPadrao;
import ode.processoPadrao.Cdp.CompPP;

public class JanDefinirProcessoPadrao {

	CtrlDefinirProcessoPadrao ctrl;
	JanelaSimples janela;

	public JanDefinirProcessoPadrao(
			CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao,
			JanelaSimples janelaSimples) {

		ctrl = ctrlDefinirProcessoPadrao;
		janela = janelaSimples;

		configuracaoBasica();
		menu();
		listaProcessoPadrao();
		janela.mostrar();

	}

	private void listaProcessoPadrao() {
		Listbox listaProcessoPadrao = new Listbox();
		Listitem itemLista;

		listaProcessoPadrao.setParent(janela);
		listaProcessoPadrao.setAttribute("testeNOME", null);

		Listhead listHead = new Listhead();
		listHead.setAttribute("teste", null);

		// listaProcessoPadrao.setWidth("200px");
		// listaProcessoPadrao.setHeight("300px");

		Collection<CompPP> listaCompPP = ctrl.getAllCompPP();
		for (CompPP compPP : listaCompPP) {
			itemLista = new Listitem();
			Listcell listcell = new Listcell();

			itemLista.setParent(listaProcessoPadrao);
			itemLista.appendChild(listcell);

			listcell.setLabel(compPP.getNome());
		}
	}

	private void configuracaoBasica() {
		janela.setTitle("Definir Processo Padrao");
		janela.setWidth("450px");
		janela.setBorder("normal");
		janela.setClosable(true);
		janela.setPosition("&quot;center;&quot;;");
		janela.setSizable(true);
		janela.setMaximizable(true);
	}

	private void menu() {
		Menubar menubar = new Menubar();
		Menu menu = new Menu();
		Menupopup menupopup = new Menupopup();

		menubar.setParent(janela);
		menu.setParent(menubar);
		menupopup.setParent(menu);

		menu.setLabel("Processo");

		newItemBasicoMenu(menupopup, "Definir CompPP",
				new EventListnerDefinirCompPP());
		newItemBasicoMenu(menupopup, "Estabelecer Requisitos",
				new EventListnerEstabelecerRequisitos());
		newItemBasicoMenu(menupopup, "Abrir Processo Padrão", new EventListnerSelecionarProcessoPadrao());

	}

	private void newItemBasicoMenu(Menupopup menupopup, String label,
			EventListener eventListner) {
		Menuitem menuItemDefinirCompPP = new Menuitem();
		menuItemDefinirCompPP.setParent(menupopup);
		menuItemDefinirCompPP.setLabel(label);
		menuItemDefinirCompPP.addEventListener("onClick", eventListner);
	}

	class EventListnerDefinirCompPP implements EventListener {

		@Override
		public void onEvent(Event arg0) throws Exception {
			ctrl.abrirJanDefinirCompPP();
		}
	}
	
	public class EventListnerEstabelecerRequisitos implements EventListener {
		@Override
		public void onEvent(Event arg0) throws Exception {
			ctrl.abrrrJanEstabelecerRequisitos();
		}
	}
	public class EventListnerSelecionarProcessoPadrao implements EventListener {
		@Override
		public void onEvent(Event arg0) throws Exception {
			ctrl.abrirJanSelecionaProcessoPadrao();
		}
	}
	
	

}
