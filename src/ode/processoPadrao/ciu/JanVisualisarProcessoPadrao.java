package ode.processoPadrao.ciu;

import java.util.Set;

import ode.conhecimento.principal.cdp.Conhecimento;
import ode.processoPadrao.cdp.AtividadeProcessoPadrao;
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cdp.CompPPMacroatividade;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.CompPPProcessoSimples;
import ode.processoPadrao.cdp.ElementoCompPP;

import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menuseparator;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Vbox;

public class JanVisualisarProcessoPadrao extends JanCore {

	private static final long serialVersionUID = -238115552395087228L;
	private Tree tree = null;
	
	private Listbox listbox;
 	private Label labelInterface;
 	private Label labelEstrutura;
 	private Label label;
 	private Tabbox tabbox;
 	private Tabs tabs;
 	private Tab tabInterface;
 	private Tab tabEstrutura;
 	private Tabpanels tabpanels;
 	private Tabpanel panelInterface;
 	private Tabpanel panelEstrutura;
 	
 	private Vbox vboxInterface;
 	private Vbox vboxEstrutura; 	
 	
 	
	// Construtor
	public JanVisualisarProcessoPadrao(CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao) {

		super(ctrlDefinirProcessoPadrao);
		
		listbox = new Listbox();
		labelInterface = new Label();
		labelEstrutura = new Label();
		label = new Label();
		tabbox = new Tabbox();
		vboxEstrutura = new Vbox();
		tabbox.setParent(this);
		
		vboxInterface = conteudoInterface();
		conteudoEstrutura();
		
		criarTabbox();
		tree.setParent(vboxEstrutura);
		
		
		mostrar();
	}

	
	// ---------- Subitem Conteudo Interface -----------
	
	private void criarTabbox() {
		tabs = new Tabs();
		tabInterface = new Tab("Interface");
		tabEstrutura = new Tab("Estrutura");
		
		tabInterface.setParent(tabs);
		tabEstrutura.setParent(tabs);
		tabs.setParent(tabbox);
		
		tabpanels = new Tabpanels();
		
		panelInterface = new Tabpanel();
		panelEstrutura = new Tabpanel();
		
		tabpanels.setParent(tabbox);
		panelInterface.setParent(tabpanels);
		panelEstrutura.setParent(tabpanels);
		
		vboxInterface.setParent(panelInterface);
		vboxEstrutura.setParent(panelEstrutura);
	}


	public Vbox conteudoInterface(){
		
		Vbox vbox = new Vbox();
				
		listbox.setParent(vbox);
				
		
		
		// Nome:
		
		Listitem listitemNome = new Listitem();
		
		Listcell cellNomeLabel = new Listcell("Nome:");
		Listcell cellNomeObjeto = new Listcell();
		
		listitemNome.setParent(listbox);
		listitemNome.appendChild(cellNomeLabel);
		listitemNome.appendChild(cellNomeObjeto);
				
		if (ctrl.getcompPPSelecionado().getNome().compareTo("") == 0){
			cellNomeObjeto.setLabel("não informado");
			cellNomeObjeto.setStyle("font-style: italic");
		}else{
			cellNomeObjeto.setLabel(ctrl.getcompPPSelecionado().getNome());
		}
		

		// Situação (Definido ou não):
		
		Listitem listitemSituacao = new Listitem();
		
		Listcell cellSituacaoLabel = new Listcell("Situação:");
		Listcell cellSituacaoObjeto = new Listcell();
		
		listitemSituacao.setParent(listbox);
		listitemSituacao.appendChild(cellSituacaoLabel);
		listitemSituacao.appendChild(cellSituacaoObjeto);
		
		if (ctrl.getcompPPSelecionado().isDefinicaoConcluida() == true){
			cellSituacaoObjeto.setLabel("Definido");
		}else{
			cellSituacaoObjeto.setLabel("Em definição");
		}
		
		
		// Tipo:
		
		if((ctrl.getcompPPSelecionado() instanceof CompPPProcessoComplexo) == false){
			
			Listitem listitemTipo = new Listitem();
				
			Listcell cellTipoLabel = new Listcell("Tipo:");
			Listcell cellTipoObjeto = new Listcell();
				
			listitemTipo.setParent(listbox);
			listitemTipo.appendChild(cellTipoLabel);
			listitemTipo.appendChild(cellTipoObjeto);
								
			if (ctrl.getcompPPSelecionado() instanceof CompPPProcessoSimples){
				
				if (((CompPPProcessoSimples) ctrl.getcompPPSelecionado()).getTipo().getNome().compareTo("") == 0){
					cellTipoObjeto.setLabel("não informado");
					cellTipoObjeto.setStyle("font-style: italic");
				}else{
					cellTipoObjeto.setLabel(((CompPPProcessoSimples) ctrl.getcompPPSelecionado()).getTipo().getNome());
				}
			}else{
				if(ctrl.getcompPPSelecionado() instanceof CompPPMacroatividade){
					
					if (((CompPPMacroatividade) ctrl.getcompPPSelecionado()).getTipo().getNome().compareTo("") == 0){
						cellTipoObjeto.setLabel("não informado");
						cellTipoObjeto.setStyle("font-style: italic");
					}else{
						cellTipoObjeto.setLabel(((CompPPMacroatividade) ctrl.getcompPPSelecionado()).getTipo().getNome());
					}
				}
			}
		}
		
		// Descricao:
		
		Listitem listitemDescricao = new Listitem();
		
		Listcell cellDescricaoLabel = new Listcell("Descrição:");
		Listcell cellDescricaoObjeto = new Listcell();
		
		listitemDescricao.setParent(listbox);
		listitemDescricao.appendChild(cellDescricaoLabel);
		listitemDescricao.appendChild(cellDescricaoObjeto);
				
		if (ctrl.getcompPPSelecionado().getDescricao().compareTo("") == 0){
			cellDescricaoObjeto.setLabel("não informado");
			cellDescricaoObjeto.setStyle("font-style: italic");
		}else{
			cellDescricaoObjeto.setLabel(ctrl.getcompPPSelecionado().getDescricao());
		}
			
			
		// Objetivo:
		
		Listitem listitemObjetivo = new Listitem();
		
		Listcell cellObjetivoLabel = new Listcell("Objetivos:");
		Listcell cellObjetivoObjeto = new Listcell();
		
		listitemObjetivo.setParent(listbox);
		listitemObjetivo.appendChild(cellObjetivoLabel);
		listitemObjetivo.appendChild(cellObjetivoObjeto);
		
		label.setParent(cellObjetivoObjeto);
		
		if (ctrl.getcompPPSelecionado().getObjetivo().compareTo("") == 0){
			label.setValue("não informado");
			label.setStyle("font-style: italic");
		}else{
			label.setMultiline(true);
			label.setValue(ctrl.getcompPPSelecionado().getObjetivo());
		}
		
		
		
		// Requisitos:
		
		Listitem listitemRequisitos = new Listitem();
		
		Listcell cellRequisitosLabel = new Listcell("Requisitos:");
		Listcell cellRequisitosObjeto = new Listcell();
		
		listitemRequisitos.setParent(listbox);
		listitemRequisitos.appendChild(cellRequisitosLabel);
		listitemRequisitos.appendChild(cellRequisitosObjeto);
				
		if (ctrl.getcompPPSelecionado().getRequisitoCompPP().compareTo("") == 0){
			cellRequisitosObjeto.setLabel("não informado");
			cellRequisitosObjeto.setStyle("font-style: italic");
		}else{
			cellRequisitosObjeto.setLabel(ctrl.getcompPPSelecionado().getRequisitoCompPP());
		}

		
		
		// Requisitos:

		if((ctrl.getcompPPSelecionado() instanceof CompPPProcessoComplexo) == false){
		
			Boolean primeiraVezNoFor = true;
			
			Listitem listitemTipos = new Listitem();
			
			Listcell cellTiposLabel = new Listcell("Tipos presentes na interface:");
			Listcell cellTiposObjeto = new Listcell();
			
			Set<ElementoCompPP> setTiposInterface = ctrl.getcompPPSelecionado().getInterfaceCompPP().getEstruturaCompPP().getElementosCompPP();
			
			listitemTipos.setParent(listbox);
			listitemTipos.appendChild(cellTiposLabel);
			listitemTipos.appendChild(cellTiposObjeto);
				
			if (setTiposInterface.isEmpty() == false){	
				for(ElementoCompPP elemento : setTiposInterface){
				
					if (primeiraVezNoFor){
						cellTiposObjeto.setLabel(elemento.getElementoConhecimento().getNome());
						primeiraVezNoFor = false;
					}else{
						Listitem itemNovo = new Listitem();
						Listcell cellNovaLabel = new Listcell("");
						Listcell cellNovoObjeto = new Listcell(elemento.getElementoConhecimento().getNome());
						
						itemNovo.setParent(listbox);
						itemNovo.appendChild(cellNovaLabel);
						itemNovo.appendChild(cellNovoObjeto);
					}
				}
			}else{
				cellTiposObjeto.setLabel("não informado");
				cellTiposObjeto.setStyle("font-style: italic");
			}
		}
		return vbox;
		
	}
	
	public void conteudoEstrutura() {
		this.setTitle(ctrl.getcompPPSelecionado().getNome());
		criaArvoreCompPP();
	}

	private void criaArvoreCompPP() {
		resetaArvore();
		geraTreeCompPPCompleta(ctrl.getcompPPSelecionado()).setParent(tree.getTreechildren());
	}

	protected void resetaArvore() {
		if (tree != null) {// remove a arvore antiga da janela.
			this.removeChild(tree);
		}
		
		tree = new Tree(); // cria uma nova arvore e insere na janela.
		//tree.setParent(this);

		tree.setStyle("overflow-y: scroll; max-height:100%;");
		Treechildren treechildren = new Treechildren();
		treechildren.setParent(tree);
		Separator sep = new Separator();
		sep.setParent(this);

	}
	
	private Treeitem geraTreeCompPPCompleta(CompPP compPP) {
		
		Treeitem treeitemCompPP;

		if (compPP != null) {
			adicionaItem(tree.getTreechildren(), "* Para visualizar a estrutura clique na seta ", "");
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
			geraTreeCompPP(compPPSimples).setParent(tc);
		}

		return itemCabecalho;

	}

	// Processo Simples
	
	private Treeitem geraTreeCompPP(CompPPProcessoSimples compPP) {
		
		Treeitem itemCabecalho = cabecalhoCompPP(compPP);
		Treechildren tc = itemCabecalho.getTreechildren();


/*		if (compPP.getAtividadeProcessoPadrao() != null){		
			geraTreeAtividadeProcessoPadrao(compPP.getAtividadeProcessoPadrao()).setParent(tc);
		}*/
		
		for (CompPPMacroatividade macroAtv : compPP.getMacroAtividades()) {
			geraTreeCompPP(macroAtv).setParent(tc);
		}

		return itemCabecalho;
	}

	// Macroatividade
	
	private Treeitem geraTreeCompPP(CompPPMacroatividade compPP) {
		
		Treeitem itemCabecalho = cabecalhoCompPP(compPP);
		Treechildren tc = itemCabecalho.getTreechildren();

		geraTreeAtividadeProcessoPadrao(compPP.getAtividadeProcessoPadrao()).setParent(tc);

		return itemCabecalho;
	}

	// Processo Padrão
	
	protected Treeitem geraTreeAtividadeProcessoPadrao(AtividadeProcessoPadrao atividade) {

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
		arvoreConhecimento(procedimentosChildren,
				atividade.getProcedimentoMetodo(), "/imagens/metodo.gif");
		arvoreConhecimento(procedimentosChildren,
				atividade.getProcedimentoNorma(), "/imagens/norma.gif");
		arvoreConhecimento(procedimentosChildren,
				atividade.getProcedimentoRoteiro(), "/imagens/roteiro.gif");
		arvoreConhecimento(procedimentosChildren,
				atividade.getProcedimentoTecnica(), "/imagens/tecnica.gif");

		/* MENUS de contexto */
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

	private Treeitem cabecalhoAtividadeProcessoPadrao(AtividadeProcessoPadrao atv) {
		Treeitem itemCabecalho = new Treeitem();
		itemCabecalho.setOpen(false);
		
		if(atv != null){
			if(atv.getNome() != null && atv.getNome() != ""){
				itemCabecalho.setLabel(atv.getNome());
			}else{
				if(atv.getTipo() != null){
					itemCabecalho.setLabel(atv.getTipo().getNome());
				}else
					itemCabecalho.setLabel("Atividade Padrão");
			}
		}else
			itemCabecalho.setLabel("Atividade Padrão");
		
		itemCabecalho.setImage("/imagens/grupo.png");

		Treechildren tc = new Treechildren();
		tc.setParent(itemCabecalho);

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

	private void newItemBasicoMenu(MenupopupV menupopup, String label, EventListener eventListner) {
		Menuitem menuItemDefinirCompPP = new Menuitem();
		menuItemDefinirCompPP.setParent(menupopup);
		menuItemDefinirCompPP.setLabel(label);
		menuItemDefinirCompPP.addEventListener("onClick", eventListner);
	}


}
