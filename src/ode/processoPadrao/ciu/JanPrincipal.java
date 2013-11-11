package ode.processoPadrao.ciu;

import java.awt.peer.MenuItemPeer;
import java.util.HashSet;
import java.util.Set;

import ode.conhecimento.principal.cdp.Conhecimento;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.processoPadrao.cdp.AtividadeProcessoPadrao;
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cdp.CompPPMacroatividade;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.CompPPProcessoSimples;
import ode.processoPadrao.cdp.ElementoCompPP;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Menuseparator;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;

public class JanPrincipal extends JanCore {

	private static final long serialVersionUID = -238115552395087228L;
	private Tree tree = null;

 	
	// Construtor
	public JanPrincipal(CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao) {

		super(ctrlDefinirProcessoPadrao);
		menu();
		conteudo();		
		mostrar();
	}

	
	// --------- Subitem Menu ----------
	
	private void menu() {
		
		// Instanciou variaveis:
		Menubar menubar = new Menubar();
		Menu menu = new Menu("Processo Padrão");
		MenupopupV menupopup = new MenupopupV();

		// Setou os pais:
		menubar.setParent(this);
		menu.setParent(menubar);
		menupopup.setParent(menu);

		// Menu de definição:
		Menu menuDefinir = new Menu("Cadastrar");
		Menu menuVisualizar = new Menu("Visualizar");
		Menu menuEditar = new Menu("Editar");
		Menu menuEditarCompPP = new Menu("Componente");
		
		MenupopupV popupDefCompPP = new MenupopupV();		
		MenupopupV popupEditar = new MenupopupV();
		MenupopupV popupVisualizar = new MenupopupV();
		MenupopupV popupEditarCompPP = new MenupopupV();
		
		menuDefinir.setParent(menupopup);
		menuEditar.setParent(menupopup);
		//menuVisualizar.setParent(menupopup);
		
		popupDefCompPP.setParent(menuDefinir);
		//popupVisualizar.setParent(menuVisualizar);
		popupEditar.setParent(menuEditar);
		popupEditarCompPP.setParent(menuEditarCompPP);
		
		menuEditarCompPP.setParent(popupEditar);
		
		newItemBasicoMenu(popupDefCompPP, "Componente de Processo", new EventListnerDefinirNovoCompPP());
		newItemBasicoMenu(popupDefCompPP, "Atividade Padrão", new EventListenerDefinirAtividadePadrao());
		
		//newItemBasicoMenu(popupEditar, "Atividade Padrão", new EventListnerEditarProcessoPadrao());
		
		// Criou subitens:
		
		newItemBasicoMenu(popupEditarCompPP, "Processo Complexo", new EventListenerEditarCompPPComplexo());
		newItemBasicoMenu(popupEditarCompPP, "Processo Simples", new EventListenerEditarCompPPSimples());
		newItemBasicoMenu(popupEditarCompPP, "Processo Macroatividade", new EventListenerEditarCompPPMacroatividade());
		
		newItemBasicoMenu(popupVisualizar, "Componente de Processo", new EventListenerSelecionarVisualizarProcesso());
		newItemBasicoMenu(popupVisualizar, "Atividade Padrão", new EventListenerSelecionarVisualizarAtividade());
		
	}
	
	class EventListenerEditarCompPPComplexo implements EventListener {

		@Override
		public void onEvent(Event arg0) throws Exception {
			ctrl.abrirJanSelecionaProcessoComplexo();
		}
	}
	
	class EventListenerEditarCompPPSimples implements EventListener {

		@Override
		public void onEvent(Event arg0) throws Exception {
			ctrl.abrirJanSelecionaProcessoSimples();
		}
	}
	
	class EventListenerEditarCompPPMacroatividade implements EventListener {

		@Override
		public void onEvent(Event arg0) throws Exception {
			ctrl.abrirJanSelecionaProcessoMacroatividade();
		}
	}
	
	class EventListnerEditarProcessoPadrao implements EventListener {

		@Override
		public void onEvent(Event arg0) throws Exception {
			
		}
	}
	
	class EventListnerDefinirNovoCompPP implements EventListener {

		@Override
		public void onEvent(Event arg0) throws Exception {
			ctrl.abrirJanDefinirCompPP(true);
		}
	}
	
	class EventListenerSelecionarVisualizarProcesso implements EventListener {
		@Override
		public void onEvent(Event arg0) throws Exception {
			ctrl.abrirJanSelecionarVisualizarProcesso();
		}
	}
	
	class EventListenerSelecionarVisualizarAtividade implements EventListener {

		@Override
		public void onEvent(Event arg0) throws Exception {
			ctrl.abrirJanSelecionarVisualizarAtividade();
		}
	}
	
	class EventListenerDefinirAtividadePadrao implements EventListener {

		@Override
		public void onEvent(Event arg0) throws Exception {
			ctrl.abrirJanDefinirAtividadePadrao();
		}
	}
	
	class EventListenerFecharComponente implements EventListener {

		@Override
		public void onEvent(Event arg0) throws Exception {
			ctrl.setCompPPSelecionado(null);
			ctrl.resetJanelaPrincipal();
		}
	}
	
	
	// ---------- Subitem Conteudo -----------
	
	
	public void conteudo() {
		this.setTitle("Definição de Processo Padrão");
		criaArvoreCompPP();
	}

	private void criaArvoreCompPP() {
		resetaArvore();
		geraTreeCompPPCompleta(ctrl.getcompPPSelecionado()).setParent(tree.getTreechildren());
		
	}

	protected void resetaArvore() {
		if (tree != null) {// remove a arvore antiga da janela.
			this.removeChild(tree);
			tree = null;
		}
		
		tree = new Tree(); // cria uma nova arvore e insere na janela.
		tree.setParent(this);

		tree.setStyle("overflow-y: scroll; max-height:100%;");
		Treechildren treechildren = new Treechildren();
		treechildren.setParent(tree);
		Separator sep = new Separator();
		sep.setParent(this);

	}
	
	private Treeitem geraTreeCompPPCompleta(CompPP compPP) {
		
		Treeitem treeitemCompPP;

		if (compPP != null) {
			adicionaItem(tree.getTreechildren(), "* Para configurar clique com o botão direito", "");
			adicionaItem(tree.getTreechildren(), "* Para abrir clique na seta ", "");
			treeitemCompPP = geraTreeCompPP(compPP);
		} else {
			return adicionaItem(tree.getTreechildren(),	"Nenhum Processo selecionado.", "");
		}

		return treeitemCompPP;
	}

	
	// Irá indicar qual o tipo de processo a árvore deverá gerar:	
	
	private Treeitem geraTreeCompPP(CompPP compPP) {

		if (compPP instanceof CompPPProcessoComplexo) {
			return geraTreeCompPP((CompPPProcessoComplexo) compPP);
		} else if (compPP instanceof CompPPProcessoSimples) {
			return geraTreeCompPP((CompPPProcessoSimples) compPP);
		} else {
			return geraTreeCompPP((CompPPMacroatividade) compPP);
		}

	}
	

	protected Treeitem adicionaItem(Treechildren treechildren, String label, String pathImage) {
		Treeitem treeitem = new Treeitem();
		treeitem.setOpen(false);
		treeitem.setParent(treechildren);
		treeitem.setLabel(label);
		treeitem.setImage(pathImage);
		return treeitem;
	}
	
	
	// Processo Complexo
	
	private Treeitem geraTreeCompPP(CompPPProcessoComplexo compPP) {
		
		Treeitem itemCabecalho = cabecalhoCompPP(compPP);
		Treechildren tc = itemCabecalho.getTreechildren();

		for (CompPPProcessoSimples compPPSimples : compPP.getProcessosSimples()) {
			Treeitem itemSimples = geraTreeCompPP(compPPSimples);
			itemSimples.setParent(tc);
			
			MenupopupV menupopupCabecalho = new MenupopupV();
			newItemBasicoMenu(menupopupCabecalho, "Excluir da composição do Processo Complexo", new EventExcluirDaComposicao(compPPSimples));
			menupopupCabecalho.setParent(this);
			itemSimples.setContext(menupopupCabecalho);
			
		}

		MenupopupV menuContexto = menuDeContextoCompPP(compPP);
		menuContexto.setParent(this);
		itemCabecalho.setContext(menuContexto);
		menuContexto.setValue(compPP);

		return itemCabecalho;

	}

	// Processo Simples
	
	private Treeitem geraTreeCompPP(CompPPProcessoSimples compPP) {
		
		Treeitem itemCabecalho = cabecalhoCompPP(compPP);
		Treechildren tc = itemCabecalho.getTreechildren();
		
/*		if (compPP.getAtividadeProcessoPadrao() != null){		
			geraTreeAtividadeProcessoPadrao(compPP.getAtividadeProcessoPadrao(), compPP.isDefinicaoConcluida()).setParent(tc);
		}*/
		
		for (CompPPMacroatividade macroAtv : compPP.getMacroAtividades()) {
			Treeitem itemMacroatividade = geraTreeCompPP(macroAtv);
			itemMacroatividade.setParent(tc);
			
			MenupopupV menupopupCabecalho = new MenupopupV();
			
			newItemBasicoMenu(menupopupCabecalho, "Excluir da composição do Processo Simples", new EventExcluirDaComposicao(macroAtv));
			newItemBasicoMenu(menupopupCabecalho, "Definir dependências", new EventSetarDependencias(macroAtv));
			
			menupopupCabecalho.setParent(this);
			itemMacroatividade.setContext(menupopupCabecalho);
			
		}

		// Insere menu de contexto
		MenupopupV menuContexto = menuDeContextoCompPP(compPP);
		menuContexto.setParent(this);
		menuContexto.getRoot();
		itemCabecalho.setContext(menuContexto);
		menuContexto.setValue(compPP);
		
		return itemCabecalho;
	}

	class EventSetarDependencias implements EventListener {
        CompPPMacroatividade macroatividade;
        
        public EventSetarDependencias(CompPPMacroatividade macroAtv){
        	macroatividade = macroAtv;
        }
        
		@Override
		public void onEvent(Event arg0) throws Exception {
			 ctrl.abrirJanDefinirDependencias(macroatividade);
        };
	}
	
	// Macroatividade
	
	private Treeitem geraTreeCompPP(CompPPMacroatividade compPP) {

		Treeitem itemCabecalho = cabecalhoCompPP(compPP);
		Treechildren tc = itemCabecalho.getTreechildren();

		geraTreeAtividadeProcessoPadrao(compPP.getAtividadeProcessoPadrao(), compPP.isDefinicaoConcluida(), itemCabecalho, tc);//.setParent(tc);

		// Insere menu de contexto
		MenupopupV menuContexto = menuDeContextoCompPP(compPP);
		menuContexto.setParent(this);
		menuContexto.getRoot();
		itemCabecalho.setContext(menuContexto);
		menuContexto.setValue(compPP);

		return itemCabecalho;
	}

	private Treeitem cabecalhoCompPP(CompPP compPP) {
		Treeitem ti = new Treeitem();
		ti.setOpen(false);
		ti.setLabel(compPP.getNome());
		ti.setValue(compPP);

		ti.setImage("/imagens/grupo.png");

		Treechildren treeChildren = new Treechildren();
		treeChildren.setParent(ti);

		return ti;
	}
	
	// Processo Padrão
	
	protected Treeitem geraTreeAtividadeProcessoPadrao(AtividadeProcessoPadrao atividade, boolean DefinicaoConcluida, Treeitem itemCabecalho, Treechildren tc) {
		
		//Treeitem itemCabecalho = cabecalhoAtividadeProcessoPadrao(atividade);
		//Treechildren tc = itemCabecalho.getTreechildren();

		Treeitem subAtividades = adicionaItem(tc, "SubAtividades", "/imagens/folha.gif");
		Treeitem preAtividades = adicionaItem(tc, "Pré-Atividades", "/imagens/folha.gif");

		Treeitem artefatos = adicionaItem(tc, "Artefatos", "/imagens/artefato.gif");
		Treechildren subAtividadesChildren = new Treechildren();
		subAtividadesChildren.setParent(artefatos);

		Treeitem recursos = adicionaItem(tc, "Recursos", "/imagens/recurso.gif");
		Treechildren recursosChildren = new Treechildren();
		recursosChildren.setParent(recursos);

		Treeitem procedimentos = adicionaItem(tc, "Procedimentos", "/imagens/procedimento.gif");
		Treechildren procedimentosChildren = new Treechildren();
		procedimentosChildren.setParent(procedimentos);

		// subAtividades por recursão
		Treechildren tcSubAtividades = new Treechildren();
		tcSubAtividades.setParent(subAtividades);
		for (AtividadeProcessoPadrao atv : atividade.getSubAtividades()) {
			Treeitem ti = geraTreeSubEPreAtividadeProcessoPadrao(atv, DefinicaoConcluida);
			ti.setParent(tcSubAtividades);
		}

		// preAtividades por recursão
		Treechildren tcPreAtividades = new Treechildren();
		tcPreAtividades.setParent(preAtividades);
		for (AtividadeProcessoPadrao atv : atividade.getPreAtividades()) {
			Treeitem ti = geraTreeSubEPreAtividadeProcessoPadrao(atv, DefinicaoConcluida);
			ti.setParent(tcPreAtividades);
		}

		// INSUMOS
		arvoreConhecimento(subAtividadesChildren, atividade.getInsumos(), "/imagens/insumo.jpg");

		// PRODUTOS
		arvoreConhecimento(subAtividadesChildren, atividade.getProdutos(), "/imagens/produto.jpg");

		// RECURSOS HARDWARE
		arvoreConhecimento(recursosChildren, atividade.getRecursoHardware(), "/imagens/recursoHardware.gif");
		
		// RECURSOS SOFTWARE
		arvoreConhecimento(recursosChildren, atividade.getFerramentaSoftware(), "/imagens/ferramentaSoftware.gif");
		
		// RECURSOS HUMANOS
		arvoreConhecimento(recursosChildren, atividade.getRecursoHumano(), 	"/imagens/recursoHumano.gif");

		// PROCEDIMENTO metodos roteiros tecnicas normas
		arvoreConhecimento(procedimentosChildren, atividade.getProcedimentoMetodo(), "/imagens/metodo.gif");
		arvoreConhecimento(procedimentosChildren, atividade.getProcedimentoNorma(), "/imagens/norma.gif");
		arvoreConhecimento(procedimentosChildren, atividade.getProcedimentoRoteiro(), "/imagens/roteiro.gif");
		arvoreConhecimento(procedimentosChildren, atividade.getProcedimentoTecnica(), "/imagens/tecnica.gif");

		
		
		/* MENUS de contexto */

		if (DefinicaoConcluida == false){
		
			MenupopupV menupopupCabecalho = new MenupopupV();
			menusDeEdicaoCabecalho(menupopupCabecalho, atividade);
			menupopupCabecalho.setParent(this);
			itemCabecalho.setContext(menupopupCabecalho);
			
			MenupopupV menupopupSubAtividades = new MenupopupV();
			menusDeEdicaoSubatividades(menupopupSubAtividades);
			menupopupSubAtividades.setParent(this);
			subAtividades.setContext(menupopupSubAtividades);
			
			MenupopupV menupopupPreAtividades = new MenupopupV();
			menusDeEdicaoPreatividades(menupopupPreAtividades, atividade);
			menupopupPreAtividades.setParent(this);
			preAtividades.setContext(menupopupPreAtividades);
			
			MenupopupV menupopupArtefatos = new MenupopupV();
			newItemBasicoMenu(menupopupArtefatos, "Editar insumos",	new EventAtividade(atividade, EnumAtividadeProcessoPadrao.INSUMO));
			newItemBasicoMenu(menupopupArtefatos, "Editar produtos", new EventAtividade(atividade, EnumAtividadeProcessoPadrao.PRODUTO));
			menupopupArtefatos.setParent(this);
			artefatos.setContext(menupopupArtefatos);
	
			MenupopupV menupopupRecursos = new MenupopupV();
			newItemBasicoMenu(menupopupRecursos, "Editar recursos de hardware", new EventAtividade(atividade, EnumAtividadeProcessoPadrao.REQUER_HARDWARE));
			menupopupRecursos.setParent(this);
			recursos.setContext(menupopupRecursos);
	
			newItemBasicoMenu(menupopupRecursos, "Editar recursos de software", new EventAtividade(atividade, EnumAtividadeProcessoPadrao.REQUER_SOFTWARE));
			menupopupRecursos.setParent(this);
			recursos.setContext(menupopupRecursos);
	
			newItemBasicoMenu(menupopupRecursos, "Editar recursos humanos", new EventAtividade(atividade, EnumAtividadeProcessoPadrao.REALIZADA_POR));
			menupopupRecursos.setParent(this);
			recursos.setContext(menupopupRecursos);
	
			MenupopupV menupopupProcedimentos = new MenupopupV();
			newItemBasicoMenu(menupopupProcedimentos, "Editar metodos", new EventAtividade(atividade, EnumAtividadeProcessoPadrao.ADOTA_METODOS));
			menupopupProcedimentos.setParent(this);
			procedimentos.setContext(menupopupProcedimentos);
	
			newItemBasicoMenu(menupopupProcedimentos, "Editar normas", new EventAtividade(atividade, EnumAtividadeProcessoPadrao.ADOTA_NORMAS));
			menupopupProcedimentos.setParent(this);
			procedimentos.setContext(menupopupProcedimentos);
	
			newItemBasicoMenu(menupopupProcedimentos, "Editar roteiros", new EventAtividade(atividade, EnumAtividadeProcessoPadrao.ADOTA_ROTEIROS));
			menupopupProcedimentos.setParent(this);
			procedimentos.setContext(menupopupProcedimentos);
	
			newItemBasicoMenu(menupopupProcedimentos, "Editar tecnicas", new EventAtividade(atividade, EnumAtividadeProcessoPadrao.ADOTA_TECNICAS));
			menupopupProcedimentos.setParent(this);
			procedimentos.setContext(menupopupProcedimentos);

		}
			
		return itemCabecalho;

	}

protected Treeitem geraTreeSubEPreAtividadeProcessoPadrao(AtividadeProcessoPadrao atividade, boolean DefinicaoConcluida) {
		
		Treeitem itemCabecalho = cabecalhoAtividadeProcessoPadrao(atividade);
		Treechildren tc = itemCabecalho.getTreechildren();

		Treeitem subAtividades = adicionaItem(tc, "SubAtividades", "/imagens/folha.gif");
		Treeitem preAtividades = adicionaItem(tc, "Pré-Atividades", "/imagens/folha.gif");

		Treeitem artefatos = adicionaItem(tc, "Artefatos", "/imagens/artefato.gif");
		Treechildren subAtividadesChildren = new Treechildren();
		subAtividadesChildren.setParent(artefatos);

		Treeitem recursos = adicionaItem(tc, "Recursos", "/imagens/recurso.gif");
		Treechildren recursosChildren = new Treechildren();
		recursosChildren.setParent(recursos);

		Treeitem procedimentos = adicionaItem(tc, "Procedimentos", "/imagens/procedimento.gif");
		Treechildren procedimentosChildren = new Treechildren();
		procedimentosChildren.setParent(procedimentos);

		// subAtividades por recursão
		Treechildren tcSubAtividades = new Treechildren();
		tcSubAtividades.setParent(subAtividades);
		for (AtividadeProcessoPadrao atv : atividade.getSubAtividades()) {
			Treeitem ti = geraTreeSubEPreAtividadeProcessoPadrao(atv, DefinicaoConcluida);
			ti.setParent(tcSubAtividades);
		}

		// preAtividades por recursão
		Treechildren tcPreAtividades = new Treechildren();
		tcPreAtividades.setParent(preAtividades);
		for (AtividadeProcessoPadrao atv : atividade.getPreAtividades()) {
			Treeitem ti = geraTreeSubEPreAtividadeProcessoPadrao(atv, DefinicaoConcluida);
			ti.setParent(tcPreAtividades);
		}

		// INSUMOS
		arvoreConhecimento(subAtividadesChildren, atividade.getInsumos(), "/imagens/insumo.jpg");

		// PRODUTOS
		arvoreConhecimento(subAtividadesChildren, atividade.getProdutos(), "/imagens/produto.jpg");

		// RECURSOS HARDWARE
		arvoreConhecimento(recursosChildren, atividade.getRecursoHardware(), "/imagens/recursoHardware.gif");
		
		// RECURSOS SOFTWARE
		arvoreConhecimento(recursosChildren, atividade.getFerramentaSoftware(), "/imagens/ferramentaSoftware.gif");
		
		// RECURSOS HUMANOS
		arvoreConhecimento(recursosChildren, atividade.getRecursoHumano(), 	"/imagens/recursoHumano.gif");

		// PROCEDIMENTO metodos roteiros tecnicas normas
		arvoreConhecimento(procedimentosChildren, atividade.getProcedimentoMetodo(), "/imagens/metodo.gif");
		arvoreConhecimento(procedimentosChildren, atividade.getProcedimentoNorma(), "/imagens/norma.gif");
		arvoreConhecimento(procedimentosChildren, atividade.getProcedimentoRoteiro(), "/imagens/roteiro.gif");
		arvoreConhecimento(procedimentosChildren, atividade.getProcedimentoTecnica(), "/imagens/tecnica.gif");

		
		
		/* MENUS de contexto */

		if (DefinicaoConcluida == false){
		
			MenupopupV menupopupCabecalho = new MenupopupV();
			menusDeEdicaoCabecalho(menupopupCabecalho, atividade);
			menupopupCabecalho.setParent(this);
			itemCabecalho.setContext(menupopupCabecalho);
			
			MenupopupV menupopupSubAtividades = new MenupopupV();
			menusDeEdicaoSubatividades(menupopupSubAtividades);
			menupopupSubAtividades.setParent(this);
			subAtividades.setContext(menupopupSubAtividades);
			
			MenupopupV menupopupPreAtividades = new MenupopupV();
			menusDeEdicaoPreatividades(menupopupPreAtividades, atividade);
			menupopupPreAtividades.setParent(this);
			preAtividades.setContext(menupopupPreAtividades);
			
			MenupopupV menupopupArtefatos = new MenupopupV();
			newItemBasicoMenu(menupopupArtefatos, "Editar insumos",	new EventAtividade(atividade, EnumAtividadeProcessoPadrao.INSUMO));
			newItemBasicoMenu(menupopupArtefatos, "Editar produtos", new EventAtividade(atividade, EnumAtividadeProcessoPadrao.PRODUTO));
			menupopupArtefatos.setParent(this);
			artefatos.setContext(menupopupArtefatos);
	
			MenupopupV menupopupRecursos = new MenupopupV();
			newItemBasicoMenu(menupopupRecursos, "Editar recursos de hardware", new EventAtividade(atividade, EnumAtividadeProcessoPadrao.REQUER_HARDWARE));
			menupopupRecursos.setParent(this);
			recursos.setContext(menupopupRecursos);
	
			newItemBasicoMenu(menupopupRecursos, "Editar recursos de software", new EventAtividade(atividade, EnumAtividadeProcessoPadrao.REQUER_SOFTWARE));
			menupopupRecursos.setParent(this);
			recursos.setContext(menupopupRecursos);
	
			newItemBasicoMenu(menupopupRecursos, "Editar recursos humanos", new EventAtividade(atividade, EnumAtividadeProcessoPadrao.REALIZADA_POR));
			menupopupRecursos.setParent(this);
			recursos.setContext(menupopupRecursos);
	
			MenupopupV menupopupProcedimentos = new MenupopupV();
			newItemBasicoMenu(menupopupProcedimentos, "Editar metodos", new EventAtividade(atividade, EnumAtividadeProcessoPadrao.ADOTA_METODOS));
			menupopupProcedimentos.setParent(this);
			procedimentos.setContext(menupopupProcedimentos);
	
			newItemBasicoMenu(menupopupProcedimentos, "Editar normas", new EventAtividade(atividade, EnumAtividadeProcessoPadrao.ADOTA_NORMAS));
			menupopupProcedimentos.setParent(this);
			procedimentos.setContext(menupopupProcedimentos);
	
			newItemBasicoMenu(menupopupProcedimentos, "Editar roteiros", new EventAtividade(atividade, EnumAtividadeProcessoPadrao.ADOTA_ROTEIROS));
			menupopupProcedimentos.setParent(this);
			procedimentos.setContext(menupopupProcedimentos);
	
			newItemBasicoMenu(menupopupProcedimentos, "Editar tecnicas", new EventAtividade(atividade, EnumAtividadeProcessoPadrao.ADOTA_TECNICAS));
			menupopupProcedimentos.setParent(this);
			procedimentos.setContext(menupopupProcedimentos);

		}
			
		return itemCabecalho;

	}
	
	protected void arvoreConhecimento(Treechildren subAtividadesChildren, 	Set listaConhecimento, String img) {
		for (Object conhecimento : listaConhecimento) {
			Treeitem itemInsumo = new Treeitem();
			itemInsumo.setOpen(false);
			itemInsumo.setParent(subAtividadesChildren);

			itemInsumo.setLabel(((Conhecimento) conhecimento).getNome());
			itemInsumo.setValue(conhecimento);

			itemInsumo.setImage(img);
		}
	}
	
	private Treeitem cabecalhoAtividadeProcessoPadrao(AtividadeProcessoPadrao atv) {
		Treeitem itemCabecalho = new Treeitem();
		itemCabecalho.setOpen(false);
		
		if(atv != null){
			if(atv.getNome() != null && atv.getNome() != ""){
				itemCabecalho.setLabel(atv.getNome());
				//itemCabecalho.setLabel("Atividade Padrão");
			}else{
				if(atv.getTipo() != null){
					itemCabecalho.setLabel(atv.getTipo().getNome());
				}else
					itemCabecalho.setLabel("Atividades");
			}
		}else
			itemCabecalho.setLabel("Atividades");
		
		itemCabecalho.setImage("/imagens/grupo.png");

		Treechildren tc = new Treechildren();
		tc.setParent(itemCabecalho);

		return itemCabecalho;
	}


	class EventAtividade implements EventListener {
		private AtividadeProcessoPadrao atividade;
		private EnumAtividadeProcessoPadrao enumAtv;

		public EventAtividade(AtividadeProcessoPadrao atividade, EnumAtividadeProcessoPadrao enumAtv) {
			this.atividade = atividade;
			this.enumAtv = enumAtv;
		}

		@Override
		public void onEvent(Event arg0) throws Exception {
			new JanEditarConhecimentosAtividadePP(ctrl, atividade, enumAtv);
		}
	}





	// Menus Popup

	private MenupopupV menuDeContextoCompPP(CompPP compPP) {
		
		MenupopupV menupopupContexto = new MenupopupV();

		// Insere menus de edição de compPP
		if (compPP.isDefinicaoConcluida() == false) {
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
			Messagebox.show("Deseja realmente excluir o item: " 
			        + compPP.getNome() + "?", "Finalização", Messagebox.OK
			        + Messagebox.CANCEL, Messagebox.QUESTION,
					new EventListener() {
						public void onEvent(Event evt) {
							if (((Integer) evt.getData()).intValue() == Messagebox.OK) {
								ctrl.excluirCompPP(compPP);
							}
						}
			        });
		}

		CompPP compPP;

		public CompPP getCompPP() {
			return compPP;
		}

		public void setCompPP(CompPP compPP) {
			this.compPP = compPP;
		}
		
	}
	
	class EventExcluirDaComposicao implements EventListener {
		
		@Override
		public void onEvent(Event arg0) throws Exception {
			Messagebox.show("Deseja realmente excluir este da composição do componente: " 
			        + ctrl.getcompPPSelecionado().getNome() + "?", "Finalização", Messagebox.OK
			        + Messagebox.CANCEL, Messagebox.QUESTION,
					new EventListener() {
						public void onEvent(Event evt) {
							if (((Integer) evt.getData()).intValue() == Messagebox.OK) {
								
									if(compPP instanceof CompPPProcessoSimples){
										if(podeExcluirDaComposicao((CompPPProcessoSimples)compPP)){
											((CompPPProcessoComplexo)ctrl.getcompPPSelecionado()).getProcessosSimples().remove(compPP);
											ctrl.atualizarCompPP(ctrl.getcompPPSelecionado());
										}else{
											try {
												Messagebox.show("Nâo foi possivel excluir este elemento da estrutura, o tipo dele é obrigatório na composição.", 
																"Informação", Messagebox.OK, Messagebox.INFORMATION);
											} catch (InterruptedException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									}else{
										if(podeExcluirDaComposicao((CompPPMacroatividade)compPP)){
											((CompPPProcessoSimples)ctrl.getcompPPSelecionado()).getMacroAtividades().remove(compPP);
											ctrl.atualizarCompPP(ctrl.getcompPPSelecionado());
										}else{
											try {
												Messagebox.show("Nâo foi possivel excluir este elemento da estrutura, o tipo dele é obrigatório na composição.", 
																"Informação", Messagebox.OK, Messagebox.INFORMATION);
											} catch (InterruptedException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									}
									
								conteudo();
							}
						}
			        });
		}

		CompPP compPP;

		public EventExcluirDaComposicao(CompPPProcessoSimples simples){
			setCompPP(simples);
		}
		
		public EventExcluirDaComposicao(CompPPMacroatividade macroatividade){
			setCompPP(macroatividade);
		}
		
		public CompPP getCompPP() {
			return compPP;
		}

		public void setCompPP(CompPP compPP) {
			this.compPP = compPP;
		}
		
	}

	public boolean podeExcluirDaComposicao(CompPPProcessoSimples simples){
		Set<KProcesso> listaKAtividadeObrigatoria = new HashSet<KProcesso>();
		
		for(ElementoCompPP elemento: ctrl.getcompPPSelecionado().getInterfaceCompPP().getEstruturaCompPP().getElementosCompPP()){
			if(elemento.isObrigatorio() ==  true){
				listaKAtividadeObrigatoria.add((KProcesso)elemento.getElementoConhecimento());
			}
		}
		
		if(ctrl.getcompPPSelecionado().getCompPPBase() != null){
			for(ElementoCompPP elemento: ctrl.getcompPPSelecionado().getCompPPBase().getInterfaceCompPP().getEstruturaCompPP().getElementosCompPP()){
				if(elemento.isObrigatorio() ==  true){
					listaKAtividadeObrigatoria.add((KProcesso)elemento.getElementoConhecimento());
				}
			}
		}
		
		if(listaKAtividadeObrigatoria.contains(simples.getTipo())){
			return false;
		}else
			return true;
	}

	public boolean podeExcluirDaComposicao(CompPPMacroatividade macroatividade){
		Set<KAtividade> listaKAtividadeObrigatoria = new HashSet<KAtividade>();
		
		for(ElementoCompPP elemento: ctrl.getcompPPSelecionado().getInterfaceCompPP().getEstruturaCompPP().getElementosCompPP()){
			if(elemento.isObrigatorio() ==  true){
				listaKAtividadeObrigatoria.add((KAtividade)elemento.getElementoConhecimento());
			}
		}
		
		if(ctrl.getcompPPSelecionado().getCompPPBase() != null){
			for(ElementoCompPP elemento: ctrl.getcompPPSelecionado().getCompPPBase().getInterfaceCompPP().getEstruturaCompPP().getElementosCompPP()){
				if(elemento.isObrigatorio() ==  true){
					listaKAtividadeObrigatoria.add((KAtividade)elemento.getElementoConhecimento());
				}
			}
		}
		
		if(listaKAtividadeObrigatoria.contains(macroatividade.getTipo())){
			return false;
		}else
			return true;
	}
	
	class EventFinalizarDefinicao implements EventListener {

		CompPP compPP;

		EventFinalizarDefinicao(CompPP compPP) {
			this.compPP = compPP;
		}

		@Override
		public void onEvent(Event arg0) throws Exception {
			Messagebox.show("Deseja realmente finalizar a definição do item: " 
					        + compPP.getNome() + "?", "Finalização", Messagebox.OK
					        + Messagebox.CANCEL, Messagebox.QUESTION,
							new EventListener() {
								public void onEvent(Event evt) {
									if (((Integer) evt.getData()).intValue() == Messagebox.OK) {
										ctrl.finalizarDefinicao(compPP);
									}
								}
					        });
		}
	}
	

	protected void menusDeEdicaoCompPP(MenupopupV menupopupContexto, CompPP compPP) {

		// Subitem 'Editar Interface do Componente':
		
		Menu menuEditarInterface = new Menu("Editar Interface do Componente");
		Menu menuContextoApp = new Menu("Editar Contexto de Aplicação");
				
		Menupopup menuPopupInterface = new Menupopup();
		Menupopup menuPopupContextoApp = new Menupopup();
			
		Menuitem menuItemPropBasicas = new Menuitem("Editar Propriedades Básicas");
		Menuitem menuItemEstrutura = new Menuitem("Editar Estrutura");
		Menuitem menuItemEditarCarac = new Menuitem("Editar Caracterização"); //  <------ FALTA CRIAR EVENTO
		
		menuEditarInterface.setParent(menupopupContexto);
		menuEditarInterface.appendChild(menuPopupInterface);
		
		menuItemPropBasicas.setParent(menuPopupInterface);
		menuItemEstrutura.setParent(menuPopupInterface);
		
		menuContextoApp.setParent(menuPopupInterface);
		menuContextoApp.appendChild(menuPopupContextoApp);
		
		menuItemEditarCarac.setParent(menuPopupContextoApp);
		
		menuItemPropBasicas.addEventListener("onClick", new EventListnerPripriedadesBasicas());
		menuItemEstrutura.addEventListener("onClick", new EventListener() {
					@Override
					public void onEvent(Event arg0) throws Exception {
						ctrl.abrirJanEditarEstruturaCompPP();
					}
				});
		
		
		
		
		
		// Menus específicos:
		
		if (compPP instanceof CompPPMacroatividade) {
			menusDeEdicaoMacroAtividade(menupopupContexto);
		}else
			if (compPP instanceof CompPPProcessoSimples) {
				menusDeEdicaoCompPPComplexoESimples(menupopupContexto);
				//menusDeEdicaoMacroAtividade(menupopupContexto);
				//newItemBasicoMenu(menupopupContexto, "Definir dependências entre subprocessos", new EventListnerDefinirDependencias());
			}else
				menusDeEdicaoCompPPComplexoESimples(menupopupContexto);


		// separador.
		new Menuseparator().setParent(menupopupContexto);

		EventFinalizarDefinicao finalizarDefinicao = new EventFinalizarDefinicao(compPP);

		newItemBasicoMenu(menupopupContexto, "Finalizar Definição", finalizarDefinicao);

	}
	
	protected void menusDeEdicaoCompPPComplexoESimples(MenupopupV menupopupContexto) {

/*		newItemBasicoMenu(menupopupContexto, "Selecionar Componente Base", new EventListener() {
					@Override
					public void onEvent(Event arg0) throws Exception {
						ctrl.abrirJanSelecionarCompPPBase();
					}
				});*/
		
		String string = new String();
		if(ctrl.getcompPPSelecionado() instanceof CompPPProcessoComplexo){
			string = "Selecionar Processos Simples";
		}else
			string = "Selecionar Macroatividades";

		
		newItemBasicoMenu(menupopupContexto, string, new EventListener() {
					@Override
					public void onEvent(Event arg0) throws Exception {
						ctrl.abrirJanIndicarSubProcessos();
					}
				});

	}

	

	protected void menusDeEdicaoPreatividades(MenupopupV menupopupContexto, final AtividadeProcessoPadrao atividade) {
		newItemBasicoMenu(menupopupContexto, "Definir Pré-Atividades", new EventListener() {
					@Override
					public void onEvent(Event arg0) throws Exception {
						ctrl.abrirJanIndicarPreAtividades(atividade);
					}
				});

	}
	
	protected void menusDeEdicaoCabecalho(MenupopupV menupopupContexto, final AtividadeProcessoPadrao Atv){
		newItemBasicoMenu(menupopupContexto, "Editar propriedades da Atividade Padrão", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				ctrl.abrirJanEditarPropriedadesAtividadePadrao(Atv);
			}
		});
	}
	
	protected void menusDeEdicaoSubatividades(MenupopupV menupopupContexto) {
		newItemBasicoMenu(menupopupContexto, "Definir SubAtividades", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				ctrl.abrirJanIndicarSubAtividades();
			}
		});
	}

	
	protected void menusDeEdicaoMacroAtividade(MenupopupV menupopupContexto) {
		newItemBasicoMenu(menupopupContexto, "Selecionar Componente Base", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				ctrl.abrirJanSelecionarCompPPBase();
			}
		});
	}

	private void newItemBasicoMenu(MenupopupV menupopup, String label, EventListener eventListner) {
		Menuitem menuItemDefinirCompPP = new Menuitem();
		menuItemDefinirCompPP.setParent(menupopup);
		menuItemDefinirCompPP.setLabel(label);
		menuItemDefinirCompPP.addEventListener("onClick", eventListner);
	}



	public class EventListnerPripriedadesBasicas implements EventListener {
		@Override
		public void onEvent(Event arg0) throws Exception {
			
			ctrl.abrirJanEditarPropriedadesBasicas();
		}
	}

/*	public class EventListnerDefinirDependencias implements EventListener {
		@Override
		public void onEvent(Event arg0) throws Exception {

			ctrl.abrirJanDefinirDependencias();
		}
	}*/



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
