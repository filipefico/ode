package ode.processoPadrao.ciu;

import java.util.Set;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.principal.cdp.Conhecimento;
import ode.processoPadrao.cdp.AtividadeProcessoPadrao;

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

public class JanVisualisarAtividadePadrao extends JanCore{

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
	
	public JanVisualisarAtividadePadrao(CtrlDefinirProcessoPadrao ctrl) {
	
		super(ctrl);

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
					
			if (ctrl.getAtividadeSelecionada().getNome().compareTo("") == 0){
				cellNomeObjeto.setLabel("não informado");
				cellNomeObjeto.setStyle("font-style: italic");
			}else{
				cellNomeObjeto.setLabel(ctrl.getAtividadeSelecionada().getNome());
			}
			
			
			// Descricao:
			
			Listitem listitemDescricao = new Listitem();
			
			Listcell cellDescricaoLabel = new Listcell("Descrição:");
			Listcell cellDescricaoObjeto = new Listcell();
			
			listitemDescricao.setParent(listbox);
			listitemDescricao.appendChild(cellDescricaoLabel);
			listitemDescricao.appendChild(cellDescricaoObjeto);
					
			if (ctrl.getAtividadeSelecionada().getDescricao().compareTo("") == 0){
				cellDescricaoObjeto.setLabel("não informado");
				cellDescricaoObjeto.setStyle("font-style: italic");
			}else{
				cellDescricaoObjeto.setLabel(ctrl.getAtividadeSelecionada().getDescricao());
			}
			
			
			
			// Tipo:
			
			Listitem listitemTipo = new Listitem();
				
			Listcell cellTipoLabel = new Listcell("Tipo:");
			Listcell cellTipoObjeto = new Listcell();
				
			listitemTipo.setParent(listbox);
			listitemTipo.appendChild(cellTipoLabel);
			listitemTipo.appendChild(cellTipoObjeto);
			
			if(ctrl.getAtividadeSelecionada().getTipo() != null){
				if (ctrl.getAtividadeSelecionada().getTipo().getNome().compareTo("") == 0){
					cellTipoObjeto.setLabel("não informado");
					cellTipoObjeto.setStyle("font-style: italic");
				}else{
					cellTipoObjeto.setLabel(ctrl.getAtividadeSelecionada().getTipo().getNome());
				}
			}else{
				cellTipoObjeto.setLabel("não informado");
				cellTipoObjeto.setStyle("font-style: italic");
			}
			

			// Situação (Marco ou não):
			
			Listitem listitemSituacao = new Listitem();
			
			Listcell cellSituacaoLabel = new Listcell("Situação:");
			Listcell cellSituacaoObjeto = new Listcell();
			
			listitemSituacao.setParent(listbox);
			listitemSituacao.appendChild(cellSituacaoLabel);
			listitemSituacao.appendChild(cellSituacaoObjeto);
			
			if (ctrl.getAtividadeSelecionada().isEhMarco() == true){
				cellSituacaoObjeto.setLabel("É marco de projeto");
			}else{
				cellSituacaoObjeto.setLabel("Não é marco de projeto");
			}		
				
			
			return vbox;
			
		}
		
		public void conteudoEstrutura() {
			this.setTitle(ctrl.getAtividadeSelecionada().getNome());
			criaArvoreAtividade();
		}

		private void criaArvoreAtividade() {
			resetaArvore();
			geraTreeAtividadeCompleta(ctrl.getAtividadeSelecionada()).setParent(tree.getTreechildren());
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
		
		private Treeitem geraTreeAtividadeCompleta(AtividadeProcessoPadrao atividade) {
			
			Treeitem treeitemAtividade;

			if (atividade != null) {
				adicionaItem(tree.getTreechildren(), "* Para visualizar a estrutura clique na seta ", "");
				treeitemAtividade = geraTreeAtividade(atividade);
			} else {
				return adicionaItem(tree.getTreechildren(),	"Nenhuma Atividade selecionado.", "");
			}

			return treeitemAtividade;
		}

		
    	protected Treeitem adicionaItem(Treechildren treechildren, String label, String pathImage) {
			Treeitem treeitem = new Treeitem();
			treeitem.setOpen(false);
			treeitem.setParent(treechildren);
			treeitem.setLabel(label);
			treeitem.setImage(pathImage);
			return treeitem;
		}
		
		
		// Atividade Padrão
		
		private Treeitem geraTreeAtividade(AtividadeProcessoPadrao atividade) {
			
			Treeitem itemCabecalho = cabecalhoAtividade(atividade);
			Treechildren tc = itemCabecalho.getTreechildren();
			
			geraTreeAtividadeProcessoPadrao(atividade).setParent(tc);

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

		private Treeitem cabecalhoAtividade(AtividadeProcessoPadrao atividade) {
			Treeitem ti = new Treeitem();
			ti.setOpen(false);
			ti.setLabel(atividade.getNome());
			ti.setValue(atividade);

			ti.setImage("/imagens/grupo.png");

			Treechildren treeChildren = new Treechildren();
			treeChildren.setParent(ti);

			return ti;
		}

		private void newItemBasicoMenu(MenupopupV menupopup, String label, EventListener eventListner) {
			Menuitem menuItemDefinirAtividade = new Menuitem();
			menuItemDefinirAtividade.setParent(menupopup);
			menuItemDefinirAtividade.setLabel(label);
			menuItemDefinirAtividade.addEventListener("onClick", eventListner);
		}


	
	
}
