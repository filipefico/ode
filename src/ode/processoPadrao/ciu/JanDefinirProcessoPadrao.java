package ode.processoPadrao.ciu;

import java.util.Set;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.principal.cdp.Conhecimento;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

public class JanDefinirProcessoPadrao {

	CtrlDefinirProcessoPadrao ctrl;
	JanelaSimples janela;
	private Tree tree = null;

	public JanDefinirProcessoPadrao(
			CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao,
			JanelaSimples janelaSimples) {

		ctrl = ctrlDefinirProcessoPadrao;
		janela = janelaSimples;

		configuracaoBasica();
		menu();
		conteudo();
		// listaProcessoPadrao();
		janela.mostrar();

	}

	public void setCompPPSelecionado() {
		Treechildren treechildren = new Treechildren();
		Treeitem treeitem = new Treeitem();
		Treerow treerow = new Treerow();
		Treecell treecell = new Treecell();

		if (tree != null) {// remove a arvore antiga da janela.
			janela.removeChild(tree);
		}
		tree = new Tree();// cria uma nova arvore e insere na janela.
		tree.setParent(janela);
		treechildren.setParent(tree);
		treeitem.setParent(treechildren);
		treerow.setParent(treeitem);
		treecell.setParent(treerow);
		
		if (ctrl.getcompPPSelecionado() == null) {
			treecell.setLabel("Nenhum CompPP selecionado.");
		} else {
			treecell.setLabel(ctrl.getcompPPSelecionado().getNome()+" : "+ctrl.getcompPPSelecionado().getClass().getSimpleName());
			tree.setTooltiptext(ctrl.getcompPPSelecionado().getClass().getSimpleName());
		}
				
		Menupopup menuContexto = menuDeContexto();
		menuContexto.setParent(janela);
		tree.setContext(menuContexto);		
	}

	private Menupopup menuDeContexto() {
		Menupopup menupopupContexto = new Menupopup();
				
		newItemBasicoMenu(menupopupContexto, "Definir Interface",
				new EventListener() {
					@Override
					public void onEvent(Event arg0) throws Exception {
						ctrl.abrirJanDefinirInterfaceCompPP();
					}
				});
		
		return menupopupContexto;
	}

	private void conteudo() {
		setCompPPSelecionado();// inicialmente não passa nenhum CompPP
	}

	private void configuracaoBasica() {
		janela.setTitle("Definir Processo Padrao");
		janela.setWidth("450px");
		janela.setHeight("600px");
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
		newItemBasicoMenu(menupopup, "Abrir Processo Padrão",
				new EventListnerSelecionarProcessoPadrao());
		newItemBasicoMenu(menupopup, "item teste",
				new EventListener(){
					@Override
					public void onEvent(Event arg0) throws Exception {
						Set<Conhecimento> conhecimento =  ctrl.getconhecimento();
						Object o = conhecimento;
					}});
		
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
