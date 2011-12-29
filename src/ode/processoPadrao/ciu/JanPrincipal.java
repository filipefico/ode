package ode.processoPadrao.ciu;

import ode.processoPadrao.cdp.AtividadeProcessoPadrao;
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cdp.CompPPMacroatividade;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.CompPPProcessoSimples;

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
		atualizaConteudo();
		mostrar();
	}

	public void atualizaConteudo() {
		this.setTitle("Definir Processo Padrao");
		criaArvoreCompPP();
	}

	private void criaArvoreCompPP() {
		resetaArvore();
		geraTreeCompPP(ctrl.getcompPPSelecionado()).setParent(
				tree.getTreechildren());
	}

	private Treeitem geraTreeCompPP(CompPP compPP) {
		Treeitem treeitemCompPP;

		if (compPP instanceof CompPPProcessoComplexo) {
			treeitemCompPP = geraTreeCompPPComplexo((CompPPProcessoComplexo) compPP);

		} else if (compPP instanceof CompPPProcessoSimples) {
			treeitemCompPP = geraTreeCompPPSimples((CompPPProcessoSimples) compPP);

		} else if (compPP instanceof CompPPMacroatividade) {
			treeitemCompPP = geraTreeMacroAtividade((CompPPMacroatividade) compPP);

		} else {
			return adicionaItem(tree.getTreechildren(),
					"Nenhum Processo selecionado.", "");
		}

		return treeitemCompPP;

	}

	private Treeitem geraTreeCompPPComplexo(CompPPProcessoComplexo compPP) {
		Treeitem itemCabecalho = cabecalhoCompPP(compPP);

		Treechildren tc = itemCabecalho.getTreechildren();

		for (CompPPProcessoSimples compPPSimples : compPP.getProcessosSimples()) {
			geraTreeCompPPSimples(compPPSimples).setParent(tc);
		}

		MenupopupV menuContexto = menuDeContextoCompPP(compPP);
		menuContexto.setParent(this);
		itemCabecalho.setContext(menuContexto);
		menuContexto.setValue(compPP);

		return itemCabecalho;

	}

	private Treeitem geraTreeCompPPSimples(CompPPProcessoSimples compPP) {
		Treeitem itemCabecalho = cabecalhoCompPP(compPP);

		Treechildren tc = itemCabecalho.getTreechildren();

		for (CompPPMacroatividade macroAtv : compPP.getMacroAtividades()) {
			geraTreeMacroAtividade(macroAtv).setParent(tc);
		}

		// insere menu de contexto
		MenupopupV menuContexto = menuDeContextoCompPP(compPP);
		menuContexto.setParent(this);
		itemCabecalho.setContext(menuContexto);
		menuContexto.setValue(compPP);

		return itemCabecalho;
	}

	private Treeitem geraTreeMacroAtividade(CompPPMacroatividade compPP) {
		Treeitem itemCabecalho = cabecalhoCompPP(compPP);

		Treechildren tc = itemCabecalho.getTreechildren();

		geraTreeAtividadeProcessoPadrao(compPP.getAtividadeProcessoPadrao())
				.setParent(tc);

		// insere menu de contexto
		MenupopupV menuContexto = menuDeContextoCompPP(compPP);
		menuContexto.setParent(this);
		menuContexto.getRoot();
		itemCabecalho.setContext(menuContexto);
		menuContexto.setValue(compPP);

		return itemCabecalho;
	}

	protected Treeitem geraTreeAtividadeProcessoPadrao(
			AtividadeProcessoPadrao atividade) {

		Treeitem itemCabecalho = cabecalhoAtividadeProcessoPadrao(atividade);

		Treechildren tc = itemCabecalho.getTreechildren();

		Treeitem subAtividades = adicionaItem(tc, "SubAtividades",
				"/imagens/folha.gif");
		Treeitem preAtividades = adicionaItem(tc, "Pré-Atividades",
				"/imagens/folha.gif");
		Treeitem artefatos = adicionaItem(tc, "Artefatos",
				"/imagens/artefato.gif");
		Treeitem recursos = adicionaItem(tc, "Recursos", "/imagens/recurso.gif");
		Treeitem recursosHumanos = adicionaItem(tc, "Recursos humanos",
				"/imagens/recursoHumano.gif");
		Treeitem procedimentos = adicionaItem(tc, "Procedimentos",
				"/imagens/procedimento.gif");

		// subAtividades por recursão
		Treechildren tcSubAtividades = new Treechildren();
		tcSubAtividades.setParent(subAtividades);
		for (AtividadeProcessoPadrao atv : atividade.getSubAtividades()) {
			Treeitem ti = geraTreeAtividadeProcessoPadrao(atv);
			ti.setParent(tcSubAtividades);
		}

		// preAtividades por recursão
		Treechildren tcPreAtividades = new Treechildren();
		tcPreAtividades.setParent(preAtividades);
		for (AtividadeProcessoPadrao atv : atividade.getPreAtividades()) {
			Treeitem ti = geraTreeAtividadeProcessoPadrao(atv);
			ti.setParent(tcPreAtividades);
		}
		// TODO a medida que adiciono funcionalidades adicionar esses elementos
		// na arvore.

		/* MENUS de contexto */
		MenupopupV menupopupArtefatos = new MenupopupV();
		newItemBasicoMenu(menupopupArtefatos, "Editar insumos",
				new EventAtividade(atividade,
						EnumAtividadeProcessoPadrao.INSUMO));

		newItemBasicoMenu(menupopupArtefatos, "Editar produtos",
				new EventAtividade(atividade,
						EnumAtividadeProcessoPadrao.PRODUTO));
		menupopupArtefatos.setParent(this);
		artefatos.setContext(menupopupArtefatos);

		MenupopupV menupopupRecursos = new MenupopupV();
		newItemBasicoMenu(menupopupRecursos, "Editar recursos de hardware",
				new EventAtividade(atividade,
						EnumAtividadeProcessoPadrao.REQUER_HARDWARE));
		menupopupRecursos.setParent(this);
		recursos.setContext(menupopupRecursos);

		newItemBasicoMenu(menupopupRecursos, "Editar recursos de software",
				new EventAtividade(atividade,
						EnumAtividadeProcessoPadrao.REQUER_SOFTWARE));
		menupopupRecursos.setParent(this);
		recursos.setContext(menupopupRecursos);

		MenupopupV menupopupRecursosHumanos = new MenupopupV();
		newItemBasicoMenu(menupopupRecursosHumanos, "Editar recursos humanos",
				new EventAtividade(atividade,
						EnumAtividadeProcessoPadrao.REALIZADA_POR));
		menupopupRecursosHumanos.setParent(this);
		recursosHumanos.setContext(menupopupRecursosHumanos);

		MenupopupV menupopupProcedimentos = new MenupopupV();
		newItemBasicoMenu(menupopupProcedimentos, "Editar metodos",
				new EventAtividade(atividade,
						EnumAtividadeProcessoPadrao.ADOTA_METODOS));
		menupopupProcedimentos.setParent(this);
		procedimentos.setContext(menupopupProcedimentos);

		newItemBasicoMenu(menupopupProcedimentos, "Editar normas",
				new EventAtividade(atividade,
						EnumAtividadeProcessoPadrao.ADOTA_NORMAS));
		menupopupProcedimentos.setParent(this);
		procedimentos.setContext(menupopupProcedimentos);

		newItemBasicoMenu(menupopupProcedimentos, "Editar roteiros",
				new EventAtividade(atividade,
						EnumAtividadeProcessoPadrao.ADOTA_ROTEIROS));
		menupopupProcedimentos.setParent(this);
		procedimentos.setContext(menupopupProcedimentos);

		newItemBasicoMenu(menupopupProcedimentos, "Editar tecnicas",
				new EventAtividade(atividade,
						EnumAtividadeProcessoPadrao.ADOTA_TECNICAS));
		menupopupProcedimentos.setParent(this);
		procedimentos.setContext(menupopupProcedimentos);

		return itemCabecalho;

	}

	class EventAtividade implements EventListener {
		private AtividadeProcessoPadrao atividade;
		private EnumAtividadeProcessoPadrao enumAtv;

		public EventAtividade(AtividadeProcessoPadrao atividade,
				EnumAtividadeProcessoPadrao enumAtv) {
			this.atividade = atividade;
			this.enumAtv = enumAtv;
		}

		@Override
		public void onEvent(Event arg0) throws Exception {
			new JanEditarConhecimentosAtividadePP(ctrl, atividade, enumAtv);
		}
	}

	private Treeitem cabecalhoAtividadeProcessoPadrao(
			AtividadeProcessoPadrao atv) {
		Treeitem itemCabecalho = new Treeitem();
		itemCabecalho.setLabel("Atividade: " + atv.getNome());
		itemCabecalho.setImage("/imagens/atividade.png");

		Treechildren tc = new Treechildren();
		tc.setParent(itemCabecalho);

		return itemCabecalho;
	}

	private Treeitem cabecalhoCompPP(CompPP compPP) {
		Treeitem ti = new Treeitem();
		ti.setLabel(compPP.getNome());
		ti.setValue(compPP);

		ti.setImage("/imagens/grupo.png");

		Treechildren treeChildren = new Treechildren();
		treeChildren.setParent(ti);

		return ti;
	}

	protected Treeitem adicionaItem(Treechildren treechildren, String label,
			String pathImage) {
		Treeitem treeitem = new Treeitem();
		treeitem.setParent(treechildren);
		treeitem.setLabel(label);
		treeitem.setImage(pathImage);
		return treeitem;
	}

	protected void resetaArvore() {
		if (tree != null) {// remove a arvore antiga da janela.
			this.removeChild(tree);
		}
		tree = new Tree();// cria uma nova arvore e insere na janela.
		tree.setParent(this);
		Treechildren treechildren = new Treechildren();
		treechildren.setParent(tree);
	}

	private MenupopupV menuDeContextoCompPP(CompPP compPP) {
		MenupopupV menupopupContexto = new MenupopupV();

		// insere menus de edição de compPP
		if (compPP.isEhDefinido() == false) {
			menusDeEdicaoCompPP(menupopupContexto, compPP);
		}

		EventExcluir eventExcluir = new EventExcluir();
		eventExcluir.setCompPP(compPP);
		newItemBasicoMenu(menupopupContexto, "Excluir", eventExcluir);

		return menupopupContexto;
	}

	class EventExcluir implements EventListener {
		@Override
		public void onEvent(Event arg0) throws Exception {
			// ctrl.excluirCompPPselecionado();
			ctrl.excluirCompPP(compPP);
		}

		CompPP compPP;

		public CompPP getCompPP() {
			return compPP;
		}

		public void setCompPP(CompPP compPP) {
			this.compPP = compPP;
		}

	}

	protected void menusDeEdicaoCompPP(MenupopupV menupopupContexto,
			CompPP compPP) {

		newItemBasicoMenu(menupopupContexto, "Editar Propriedades básicas",
				new EventListnerPripriedadesBasicas());

		newItemBasicoMenu(menupopupContexto, "Editar estrutura",
				new EventListener() {
					@Override
					public void onEvent(Event arg0) throws Exception {
						ctrl.abrirJanEditarEstruturaCompPP();
					}
				});

		// menus específicos
		if (compPP instanceof CompPPMacroatividade) {
			menusDeEdicaoMacroAtividade(menupopupContexto);
		} else {
			menusDeEdicaoCompPPComplexoESimples(menupopupContexto);
		}

		// separador.
		new Menuseparator().setParent(menupopupContexto);

		EventFinalizarDefinicao finalizarDefinicao = new EventFinalizarDefinicao(
				compPP);

		newItemBasicoMenu(menupopupContexto, "Finalizar Definição",
				finalizarDefinicao);

	}

	class EventFinalizarDefinicao implements EventListener {

		CompPP compPP;

		EventFinalizarDefinicao(CompPP compPP) {
			this.compPP = compPP;
		}

		@Override
		public void onEvent(Event arg0) throws Exception {
			Messagebox.show("Deseja realmente finalizar a definição do item: "
					+ compPP.getNome(), "Finalização", Messagebox.OK
					+ Messagebox.CANCEL, Messagebox.QUESTION,
					new EventListener() {
						public void onEvent(Event evt) {
							if (((Integer) evt.getData()).intValue() == Messagebox.OK) {
								ctrl.finalizarDefinicao();
							}
						}
					});
		}
	}

	protected void menusDeEdicaoCompPPComplexoESimples(
			MenupopupV menupopupContexto) {

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
						ctrl.abrirJanIndicarSubProcessos();
					}
				});

	}

	protected void menusDeEdicaoMacroAtividade(MenupopupV menupopupContexto) {
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
		MenupopupV menupopup = new MenupopupV();

		menubar.setParent(this);
		menu.setParent(menubar);
		menupopup.setParent(menu);

		menu.setLabel("Processo");

		newItemBasicoMenu(menupopup, "Definir CompPP",
				new EventListnerDefinirCompPP());
		newItemBasicoMenu(menupopup, "Abrir Processo Padrão",
				new EventListnerSelecionarProcessoPadrao());

	}

	private void newItemBasicoMenu(MenupopupV menupopup, String label,
			EventListener eventListner) {
		Menuitem menuItemDefinirCompPP = new Menuitem();
		menuItemDefinirCompPP.setParent(menupopup);
		menuItemDefinirCompPP.setLabel(label);
		menuItemDefinirCompPP.addEventListener("onClick", eventListner);
	}

	class EventListnerDefinirCompPP implements EventListener {

		@Override
		public void onEvent(Event arg0) throws Exception {
			ctrl.abrirJanDefinirCompPP(true);
		}
	}

	public class EventListnerPripriedadesBasicas implements EventListener {
		@Override
		public void onEvent(Event arg0) throws Exception {
			Object o = arg0.getTarget();
			ctrl.abrirJanEditarPropriedadesBasicas();
		}
	}

	public class EventListnerSelecionarProcessoPadrao implements EventListener {
		@Override
		public void onEvent(Event arg0) throws Exception {
			ctrl.abrirJanSelecionaProcessoPadrao();
		}
	}

}

class MenupopupV extends Menupopup {
	private static final long serialVersionUID = -7771442276943364678L;
	Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
