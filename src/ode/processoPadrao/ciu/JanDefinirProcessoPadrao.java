package ode.processoPadrao.ciu;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.processoPadrao.cdp.CompPP;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Label;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;

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
		conteudo();
		//listaProcessoPadrao();
		janela.mostrar();

	}

	Label comppSelecionado = new Label();
	public void setComppSelecionado(CompPP comppSelecionado) {
		this.comppSelecionado.setValue("CompPP selecionado: "+comppSelecionado.getNome());
	}

	private void conteudo() {
		comppSelecionado.setParent(janela);
		if(ctrl.getcompPPSelecionado()==null){
			comppSelecionado.setValue("Nenhum CompPP selecionado.");			
		}else{
			this.setComppSelecionado(ctrl.getcompPPSelecionado());			
		}
	}

	/*private void listaProcessoPadrao() {
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
	}*/

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
