package ode.processoPadrao.ciu;

import ode.processoPadrao.cdp.CompPPMacroatividade;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Menuseparator;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;

public class JanPrincipal extends JanCore {

	private static final long serialVersionUID = -238115552395087228L;
	private Tree tree = null;

	public JanPrincipal(CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao) {

		super(ctrlDefinirProcessoPadrao);
		menu();
		conteudo();
		this.mostrar();
	}

	private void conteudo() {
		this.setTitle("Definir Processo Padrao");
		criaArvoreCompPP();// inicialmente não passa nenhum CompPP
		Menupopup menuContexto = menuDeContexto();
		menuContexto.setParent(this);
		tree.setContext(menuContexto);
	}

	public void criaArvoreCompPP() {
		Treechildren treechildren = new Treechildren();
		Treeitem treeitem = new Treeitem();

		if (tree != null) {// remove a arvore antiga da janela.
			this.removeChild(tree);
		}

		tree = new Tree();// cria uma nova arvore e insere na janela.
		tree.setParent(this);
		treechildren.setParent(tree);
		treeitem.setParent(treechildren);

		if (ctrl.getcompPPSelecionado() == null) {
			treeitem.setLabel("Nenhum CompPP selecionado.");
		} else {
			treeitem.setLabel(ctrl.getcompPPSelecionado().getNome() + " : "
					+ ctrl.getcompPPSelecionado().getClass().getSimpleName());
			tree.setTooltiptext(ctrl.getcompPPSelecionado().getClass()
					.getSimpleName());
		}
	}

	private Menupopup menuDeContexto() {
		Menupopup menupopupContexto = new Menupopup();
		if (ctrl.getcompPPSelecionado() == null) {
			return menupopupContexto;
		}

		// insere menus de edição de compPP
		if (ctrl.getcompPPSelecionado().isEhDefinido() == false) {
			menusDeEdicao(menupopupContexto);
		}

		newItemBasicoMenu(menupopupContexto, "Excluir", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				ctrl.excluirCompPPselecionado();
			}
		});

		return menupopupContexto;
	}

	protected void menusDeEdicao(Menupopup menupopupContexto) {

		menusDeEdicaoCompPPGenerico(menupopupContexto);

		// menus específicos
		if (ctrl.getcompPPSelecionado() instanceof CompPPMacroatividade) {
			menusDeEdicaoMacroAtividade(menupopupContexto);
		} else {
			menusDeEdicaoComplexoESimples(menupopupContexto);
		}

		// separador.
		new Menuseparator().setParent(menupopupContexto);

		newItemBasicoMenu(menupopupContexto, "Finalizar Definição",
				new EventListener() {
					@Override
					public void onEvent(Event arg0) throws Exception {
						Messagebox
								.show("Deseja realmente finalizar a definição do item: "
										+ ctrl.getcompPPSelecionado().getNome(),
										"Finalização", Messagebox.OK
												+ Messagebox.CANCEL,
										Messagebox.QUESTION,
										new EventListener() {
											public void onEvent(Event evt) {
												if (((Integer) evt.getData())
														.intValue() == Messagebox.OK) {
													ctrl.finalizarDefinicao();
												}
											}
										});
					}
				});
	}

	protected void menusDeEdicaoCompPPGenerico(Menupopup menupopupContexto) {
		newItemBasicoMenu(menupopupContexto, "Estabelecer Requisitos",
				new EventListnerEstabelecerRequisitos());

		newItemBasicoMenu(menupopupContexto, "Definir Interface",
				new EventListener() {
					@Override
					public void onEvent(Event arg0) throws Exception {
						ctrl.abrirJanDefinirInterfaceCompPP();
					}
				});
	}

	protected void menusDeEdicaoComplexoESimples(Menupopup menupopupContexto) {
		if (ctrl.getcompPPSelecionado() instanceof CompPPMacroatividade == false) {

			newItemBasicoMenu(menupopupContexto, "Selecionar CompPP base",
					new EventListener() {
						@Override
						public void onEvent(Event arg0) throws Exception {
							ctrl.abrirJanSelecionarCompPPBase();
						}
					});
			newItemBasicoMenu(menupopupContexto, "Definir SubProcessos",
					new EventListener() {
						@Override
						public void onEvent(Event arg0) throws Exception {
							ctrl.abrirJanIndicarSubAtividades();
						}
					});

		}
	}

	protected void menusDeEdicaoMacroAtividade(Menupopup menupopupContexto) {
		newItemBasicoMenu(menupopupContexto, "Definir SubAtividades",
				new EventListener() {
					@Override
					public void onEvent(Event arg0) throws Exception {
						ctrl.abrirJanIndicarSubAtividades();
					}
				});
		newItemBasicoMenu(menupopupContexto, "Definir Pré-Atividades",
				new EventListener() {
					@Override
					public void onEvent(Event arg0) throws Exception {
						ctrl.abrirJanIndicarPreAtividades();
					}
				});

	}

	private void menu() {
		Menubar menubar = new Menubar();
		Menu menu = new Menu();
		Menupopup menupopup = new Menupopup();

		menubar.setParent(this);
		menu.setParent(menubar);
		menupopup.setParent(menu);

		menu.setLabel("Processo");

		newItemBasicoMenu(menupopup, "Definir CompPP",
				new EventListnerDefinirCompPP());
		newItemBasicoMenu(menupopup, "Abrir Processo Padrão",
				new EventListnerSelecionarProcessoPadrao());

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
			ctrl.abrirJanEstabelecerRequisitos();
		}
	}

	public class EventListnerSelecionarProcessoPadrao implements EventListener {
		@Override
		public void onEvent(Event arg0) throws Exception {
			ctrl.abrirJanSelecionaProcessoPadrao();
		}
	}

}
