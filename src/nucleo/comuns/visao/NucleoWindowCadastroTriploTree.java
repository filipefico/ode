package nucleo.comuns.visao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nucleo.comuns.aplicacao.NucleoAplCadastroBase;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.persistencia.NucleoObjetoPersistenteImpl;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.cache.arvore.NucleoArvoreCache;
import nucleo.comuns.visao.cache.arvore.NucleoItemArvoreCache;
import nucleo.comuns.visao.old.NucleoWindowCadastroDados;

import org.springframework.dao.DataAccessException;
import org.zkoss.zhtml.Table;
import org.zkoss.zhtml.Td;
import org.zkoss.zhtml.Tr;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Menuseparator;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Splitter;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treecol;
import org.zkoss.zul.Treecols;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;


/**
 * Classe abstrata que representa a base para uma janela de cadastro triplo com
 * uma árvore de objetos. Entende-se por cadastro triplo a possibilidade de
 * cadastrar três tipos diferentes de objeto na mesma árvore, um objeto
 * organizador, um objeto meio e um objeto fim.
 * 
 * @author Alexandre G. N. Coelho
 * @param <O>
 *            Classe dos objetos organizadores.
 * @param <M>
 *            Classe dos objetos meio.
 * @param <F>
 *            Classe dos objetos fim.
 */
public abstract class NucleoWindowCadastroTriploTree<O extends NucleoObjetoPersistenteImpl<Long, Long>, M extends NucleoObjetoPersistenteImpl<Long, Long>, F extends NucleoObjetoPersistenteImpl<Long, Long>>
		extends NucleoWindow {

	/**
	 * Interface de aplicação usada para cadastros básicos dos objetos
	 * organizadores
	 */
	protected NucleoAplCadastroBase<O> nucleoAplCadastroBaseOrganizador;

	/**
	 * Interface de aplicação usada para cadastros básicos dos objetos meio
	 */
	protected NucleoAplCadastroBase<M> nucleoAplCadastroBaseObjetoMeio;

	/**
	 * Interface de aplicação usada para cadastros básicos dos objetos fim
	 */
	protected NucleoAplCadastroBase<F> nucleoAplCadastroBaseObjetoFim;

	/**
	 * Estrutura da árvore mantida atualmente em cache.
	 */
	protected NucleoArvoreCache arvoreCache;

	/**
	 * Constrói uma nova window de cadastro triplo de árvore sem colunas.
	 */
	public NucleoWindowCadastroTriploTree() {
		super();
	}

	/**
	 * Constrói uma nova window de cadastro triplo de árvore com as colunas
	 * passadas como argumento.
	 */
	public NucleoWindowCadastroTriploTree(String[] colunasTree) {
		super();

		for (String coluna : colunasTree) {
			Treecol treecol = new Treecol(coluna);
			treecol.setParent(treecols);
		}
	}

	public void onCreateWindow() {
		try {
			// Realiza a configuração inicial da janela
			iniciarComponentesInterface();

			// Recupera objetos do banco, monta a árvore de cache e atualiza a
			// árvore da interface
			atualizarArvore();
		} catch (Exception e) {
			exibirJanelaErro(e);
		}
	}

	/**
	 * Monta a inteface gráfica.
	 */
	private void iniciarComponentesInterface() throws NucleoRegraNegocioExcecao {

		// Configura os componentes
		this.configurarComponentes();

		// Adiciona os componentes
		this.adicionarComponentes();

		// Configura a exibição da barra de ferramentas
		this.configurarExibicaoBarraFerramentas();
	}

	/**
	 * Define a configuração dos componentes da interface no que diz respeito a
	 * dimensões, imagem utilizada, tooltip, eventos, etc.
	 */
	private void configurarComponentes() throws NucleoRegraNegocioExcecao {

		// ////////////////////////////////////
		// Define configurações gerais da janela
		// ////////////////////////////////////
		this.setTitle(this.getTituloWindow());
		tree.setMultiple(true);

		splitter.setCollapse("before");
		splitter.setTooltiptext(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_FILTRO));

		menupopup.setId("menuCadastro");
		menupopupNovo.setId("menuNovo");

		// ////////////////////////////////////
		// Configuração da barra de ferramentas
		// ////////////////////////////////////
		toolbar.setHeight(HEIGHT_TOOLBAR);
		toolbar.setWidth(WIDTH_TOOLBAR);
		tbbtNovo.setImage("/imagens/filenew.png");
		tbbtNovo.setPopup("menuNovo");
		tbbtAbrir.setImage("/imagens/fileopen.png");
		tbbtExcluir.setImage("/imagens/editdelete.png");
		tbbtParaBaixo.setImage("/imagens/paraBaixo.png");
		tbbtParaCima.setImage("/imagens/paraCima.png");
		tbbtAbrir.setTooltiptext(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_ABRIR));
		tbbtExcluir.setTooltiptext(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_EXCLUIR));
		tbbtParaBaixo.setTooltiptext(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_MOVER_ITEM_PARA_BAIXO));
		tbbtParaCima.setTooltiptext(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_MOVER_ITEM_PARA_CIMA));

		// ////////////////////////////////////
		// Define as ações de cada botão
		// ////////////////////////////////////
		tbbtAbrir.addEventListener("onClick", new EventListenerAbrir());
		tbbtExcluir.addEventListener("onClick", new EventListenerExcluir());
		tbbtParaBaixo.addEventListener("onClick", new EventListenerParaBaixo());
		tbbtParaCima.addEventListener("onClick", new EventListenerParaCima());
		menuitemNovoOrganizador.addEventListener("onClick",
				new EventListenerNovoOrganizador());
		menuitemNovoOrganizador.setLabel(this.getTooltipOrganizador());
		menuitemNovoObjetoMeio.addEventListener("onClick",
				new EventListenerNovoObjetoMeio());
		menuitemNovoObjetoMeio.setLabel(this.getTooltipObjetoMeio());
		menuitemNovoObjetoFim.addEventListener("onClick",
				new EventListenerNovoObjetoFim());
		menuitemNovoObjetoFim.setLabel(this.getTooltipObjetoFim());
		menuitemAbrir.addEventListener("onClick", new EventListenerAbrir());
		menuitemExcluir.addEventListener("onClick", new EventListenerExcluir());
		menuitemAcima.addEventListener("onClick", new EventListenerParaCima());
		menuitemAbaixo
				.addEventListener("onClick", new EventListenerParaBaixo());

		this.tree.addEventListener("onSelect",
				new EventListenerAtualizarBarraFerramentas());
		this.toolbar.addEventListener("onClick",
				new EventListenerLimparSelecao());

		// ////////////////////////////////////
		// Define configurações extras
		// ////////////////////////////////////
		configurarComponentesExtensao();
	}

	/**
	 * Obtém a tooltip a ser associada ao botão "Novo" do objeto organizador.
	 * 
	 * @return Tooltipo associada ao botão novo do objeto organizador.
	 */
	public abstract String getTooltipOrganizador();

	/**
	 * Obtém a tooltip a ser associada ao botão "Novo" do objeto meio.
	 * 
	 * @return Tooltip associada ao botão novo do objeto meio.
	 */
	public abstract String getTooltipObjetoMeio();

	/**
	 * Obtém a tooltip a ser associada ao botão "Novo" do objeto fim.
	 * 
	 * @return Tooltip associada ao botão novo do objeto fim.
	 */
	public abstract String getTooltipObjetoFim();

	/**
	 * Define configurações extras (inclusive reconfigurações) para os
	 * componentes da interface no que diz respeito a dimensões, imagem
	 * utilizada, tooltip, eventos, etc. Esse método deve ser sobrescrito por
	 * classes que herdam de NucleoWindowCadastroTree e necessitem de realizar
	 * configurações extras sobre componentes já existentes ou configurar novos
	 * componentes
	 */
	protected void configurarComponentesExtensao()
			throws NucleoRegraNegocioExcecao {
	}

	/**
	 * Adiciona os componentes, posicionando-os na janela.
	 */
	protected void adicionarComponentes() throws NucleoRegraNegocioExcecao {

		// ////////////////////////////////////
		// Barra de ferramentas
		// ////////////////////////////////////
		vboxPainel.setParent(this);

		toolbar.setParent(vboxPainel);
		tbbtNovo.setParent(toolbar);
		tbbtAbrir.setParent(toolbar);
		tbbtExcluir.setParent(toolbar);
		tbbtParaCima.setParent(toolbar);
		tbbtParaBaixo.setParent(toolbar);
		separator.setParent(vboxPainel);

		// Adiciona componentes à barra de ferramentas
		adicionarComponentesExtensaoBarraFerramentas(toolbar);

		// ////////////////////////////////////
		// Filtro da consulta
		// ////////////////////////////////////
		if (possuiFiltro()) {
			groupboxFiltro.setParent(vboxPainel);
			adicionarComponentesExtensaoFiltro(groupboxFiltro);
			captionFiltro.setParent(groupboxFiltro);
		} else {
			// Se não possui filtro, coloca um separador por motivos decorativos
			(new Separator()).setParent(vboxPainel);
		}

		// Spliter
		splitter.setParent(vboxPainel);

		// ////////////////////////////////////
		// Árvore de elementos
		// ////////////////////////////////////
		// // Window hboxTree = new Window();
		// // hboxTree.setParent(vboxPainel);
		// // hboxTree.setHeight("100%");
		// this.setSizable(true);

		tree.setParent(vboxPainel);

		// Menu popup
		menupopup.setParent(vboxPainel);
		menupopupNovo.setParent(vboxPainel);
		menuitemNovoOrganizador.setParent(menupopupNovo);
		menuitemNovoObjetoMeio.setParent(menupopupNovo);
		menuitemNovoObjetoFim.setParent(menupopupNovo);
		menuitemAbrir.setParent(menupopup);
		menuitemExcluir.setParent(menupopup);
		menuseparator.setParent(menupopup);
		menuitemAcima.setParent(menupopup);
		menuitemAbaixo.setParent(menupopup);

		// /////////////////////////////////////
		// Adiciona componentes de extensão
		// //////////////////////////////////////
		this.adicionarComponentesExtensao();
	}

	/**
	 * Adiciona novos componentes à barra de ferramentas
	 */
	protected void adicionarComponentesExtensaoBarraFerramentas(Toolbar toolbar) {
	}

	/**
	 * Adiciona componentes ao filtro
	 */
	protected void adicionarComponentesExtensaoFiltro(Groupbox groupboxFiltro)
			throws NucleoRegraNegocioExcecao {
	}

	/**
	 * Adiciona componentes gerais
	 */
	protected void adicionarComponentesExtensao()
			throws NucleoRegraNegocioExcecao {
	}

	/**
	 * Indica se a janela possui caixa de filtro de elementos.
	 * <p>
	 * Se esse método for sobrescrito, o método passouFiltro deve ser
	 * sobrescrito para testar objetos quanto ao atendimento aos critérios do
	 * filtro.
	 * 
	 * @return Verdadeiro caso a janela possua caixa de filtro e falso caso
	 *         contrário.
	 */
	protected boolean possuiFiltro() {
		return false;
	}

	/**
	 * Configura a exibição dos botões da barra de ferramentas dependendo do
	 * item selecionado na árvore. Ex.: Se o item selecionado permitir a criação
	 * de um novo item em um nó abaixo, o botão "Novo" ficará habilitado.
	 */
	protected void configurarExibicaoBarraFerramentas() {
		// Configura os botões caso nenhum item esteja selecionado
		if (tree.getSelectedItem() == null) {
			tbbtParaBaixo.setVisible(false);
			tbbtParaCima.setVisible(false);
			tbbtAbrir.setVisible(false);
			tbbtExcluir.setVisible(false);
			if (ehPossivelIncluirObjetoOrganizador()) {
				menuitemNovoOrganizador.setVisible(true);
			} else {
				menuitemNovoOrganizador.setVisible(false);
			}
			if (ehPossivelIncluirObjetoMeio()) {
				menuitemNovoObjetoMeio.setVisible(true);
			} else {
				menuitemNovoObjetoMeio.setVisible(false);
			}
			if (ehPossivelIncluirObjetoFim()) {
				menuitemNovoObjetoFim.setVisible(true);
			} else {
				menuitemNovoObjetoFim.setVisible(false);
			}

			if (ehPossivelIncluirObjetoOrganizador()
					|| ehPossivelIncluirObjetoMeio()
					|| ehPossivelIncluirObjetoFim()) {
				tbbtNovo.setVisible(true);
			} else {
				tbbtNovo.setVisible(false);
			}

			// Configura os botões caso um item esteja selecionado
		} else {
			tbbtParaBaixo.setVisible(true);
			tbbtParaCima.setVisible(true);

			if (ehPossivelIncluirObjetoOrganizador()) {
				menuitemNovoOrganizador.setVisible(true);
			} else {
				menuitemNovoOrganizador.setVisible(false);
			}

			if (ehPossivelIncluirObjetoMeio()) {
				menuitemNovoObjetoMeio.setVisible(true);
			} else {
				menuitemNovoObjetoMeio.setVisible(false);
			}

			if (ehPossivelIncluirObjetoFim()) {
				menuitemNovoObjetoFim.setVisible(true);
			} else {
				menuitemNovoObjetoFim.setVisible(false);
			}

			if (ehPossivelIncluirObjetoOrganizador()
					|| ehPossivelIncluirObjetoMeio()
					|| ehPossivelIncluirObjetoFim()) {
				tbbtNovo.setVisible(true);
			} else {
				tbbtNovo.setVisible(false);
			}

			if (ehPossivelAbrir()) {
				tbbtAbrir.setVisible(true);
				tbbtParaBaixo.setVisible(true);
				tbbtParaCima.setVisible(true);
			} else {
				tbbtAbrir.setVisible(false);
				tbbtParaBaixo.setVisible(false);
				tbbtParaCima.setVisible(false);
			}

			if (ehPossivelExcluir()) {
				tbbtExcluir.setVisible(true);
			} else {
				tbbtExcluir.setVisible(false);
			}
		}
		// Permite que configurações extras de exibição sejam realizadas nas
		// classes filhas
		configurarExibicaoBarraFerramentasExtensao();
	}

	/**
	 * Verifica se é possível incluir um item organizador.
	 * 
	 * @return Verdadeiro caso seja possível incluir.
	 */
	protected abstract boolean ehPossivelIncluirObjetoOrganizador();

	/**
	 * Verifica se é possível incluir um item objeto meio.
	 * 
	 * @return Verdadeiro caso seja possível incluir.
	 */
	protected abstract boolean ehPossivelIncluirObjetoMeio();

	/**
	 * Verifica se é possível incluir um item objeto fim.
	 * 
	 * @return Verdadeiro caso seja possível incluir.
	 */
	protected abstract boolean ehPossivelIncluirObjetoFim();

	/**
	 * Verifica se é possível alterar um item.
	 * 
	 * @return Verdadeiro caso seja possível abrir.
	 */
	protected abstract boolean ehPossivelAbrir();

	/**
	 * Verifica se é possível excluir um item.
	 * 
	 * @return Verdadeiro caso seja possível excluir.
	 */
	protected abstract boolean ehPossivelExcluir();

	/**
	 * Esse método deve ser implementado caso haja a necessidade de realizar
	 * configurações extras quanto à exibição de botões da toolbar.
	 */
	protected void configurarExibicaoBarraFerramentasExtensao() {
	}

	public void atualizarArvore() {
		try {
			// Recupera elementos e monta estrutura inicial da árvore
			arvoreCache = montarEstruturaInicialArvoreCache();

			// Preenche toda a árvore a partir da estrutura montada
			preencherTodaArvore();

			// Atualizar barra de ferramentas
			this.configurarExibicaoBarraFerramentas();

		} catch (NucleoRegraNegocioExcecao e) {
			exibirJanelaErro(e);
		}
	}

	/**
	 * Monta uma estrutura com os elementos a serem exibidos inicialmente na
	 * árvore da interface. Esse método deve ser implementado levando em
	 * consideração que nem todos os elementos da árvore precisam ser
	 * recuperados na primeira exibição, de forma a otimizar o número de
	 * registros recuperados da persistência. Os elementos que não estiverem
	 * disponíveis na estrutura inicial e forem requisitados, serão recuperados
	 * sob demanda.
	 * 
	 * @return Árvore cache montada contendo estrutura a ser exibida
	 *         inicialmente na árvore da interface.
	 * @throws NucleoRegraNegocioExcecao
	 *             Caso haja algum erro de regra de negócio
	 */
	protected abstract NucleoArvoreCache montarEstruturaInicialArvoreCache()
			throws NucleoRegraNegocioExcecao;

	/**
	 * Preenche toda a árvore da interface tendo por base a árvore cache atual.
	 * Para que esse método seja corretamente executado, é necessário que a
	 * estrutura de árvore cache já esteja devidamente preenchida.
	 */
	public void preencherTodaArvore() {
		// Obtém as raízes da árvore em cache
		List<NucleoItemArvoreCache> raizesArvore = arvoreCache.getRaizesArvore();

		// Limpa a árvore
		tree.clear();

		// Insere as colunas na árvore, se houver necessidade
		if (tree.getTreecols() == null) {
			if (treecols.getChildren().size() > 0) {
				treecols.setParent(tree);
			}
		}

		// Preenche a árvore
		Treechildren treechildren = tree.getTreechildren();
		if (treechildren == null) {
			treechildren = new Treechildren();
			treechildren.setParent(tree);
		}
		for (NucleoItemArvoreCache raiz : raizesArvore) {
			preencherItemArvore(raiz, treechildren);
		}

	}

	/**
	 * Preenche um item da árvore da interface e todos os seus subitens a partir
	 * de um item da árvore cache. Atribui também o pai do item.
	 * 
	 * @param itemArvoreCache
	 *            Item da árvore cache a ser transformado em um item da árvore
	 *            da interface.
	 * @param pai
	 *            Pai do novo item da árvore.
	 */
	private void preencherItemArvore(NucleoItemArvoreCache itemArvoreCache,
			Treechildren pai) {

		if (passouFiltro(itemArvoreCache.getObjeto())) {
			// Transforma o item da árvore cache em um tree item e altera seu
			// pai
			Treeitem treeitem = toTreeitem(itemArvoreCache);
			treeitem.setParent(pai);

			// Se treeitem não for folha, preenche sua subárvore
			if (!itemArvoreCache.isFolha()) {
				if (itemArvoreCache.isAcessouBD()) {

					// Se acessou o BD, preenche os potenciais filhos
					List<NucleoItemArvoreCache> filhos = itemArvoreCache
							.getFilhos();

					// Cria uma estrutura para receber filhos
					Treechildren treechildren = new Treechildren();
					treechildren.setParent(treeitem);

					// Se tiver filhos, então adiciona-os
					if (filhos != null && filhos.size() > 0) {
						for (NucleoItemArvoreCache filho : filhos) {
							preencherItemArvore(filho, treechildren);
						}
					}
				} else {
					// Se não acessou o BD, insere um filho falso para habilitar
					// o "+" da interface e permitir o acesso sob demanda
					Treechildren treechildren = new Treechildren();
					Treeitem falsoFilho = new Treeitem();
					falsoFilho.setParent(treechildren);
					treechildren.setParent(treeitem);

					// Indica que o treeitem não deve ser aberto, pois seu filho
					// é falso (será obtido sob demanda)
					treeitem.setOpen(false);
				}
			}
		}
		this.acaoAposPreencherItemArvore();
	}

	/**
	 * Ação que é executada após preencher um item da árvore.
	 * 
	 */
	protected void acaoAposPreencherItemArvore() {
	}

	/**
	 * Testa se um objeto passa no filtro para exibição na tela. Esse método é
	 * chamado a partir do método que preenche a árvore e deve ser sobrescrito
	 * caso a janela possua filtro.
	 * <p>
	 * Se esse método for sobrescrito, o método possuiFiltro deve ser
	 * sobrescrito para retornar verdadeiro.
	 * 
	 * @param obj
	 *            Objeto a ser testado de acordo com o filtro.
	 * @return Verdadeiro caso o objeto atenda aos critérios do filtro e falso
	 *         caso contrário.
	 */
	protected boolean passouFiltro(Object obj) {
		return true;
	}

	/**
	 * Cria um Treeitem (item da árvore da interface) a partir de um
	 * NucleoItemArvoreCache (item da estrutura de cache).
	 * 
	 * @param itemArvoreCache
	 *            Item da árvore cache a ser transformado em um Treeitem (item
	 *            da árvore da interface)
	 * @return Treeitem (item da árvore da interface) resultante correspondente
	 *         ao item da árvore de teste passado como argumento.
	 */
	protected Treeitem toTreeitem(NucleoItemArvoreCache itemArvoreCache) {
		// Cria o novo treeitem com os dados do itemArvoreCache
		Treeitem treeitem = new Treeitem(itemArvoreCache.getObjeto().toString());
		treeitem.setValue(itemArvoreCache);
		this.recuperarEnderecoImagem(itemArvoreCache.getObjeto());
		treeitem.addEventListener("onOpen",
				new EventListenerExpandirItemArvore());

		return treeitem;
	}

	/**
	 * Recupera o endereço de uma imagem de arcordo com o objeto passado por
	 * parâmetro.
	 * 
	 * @param objeto
	 *            Objeto o qual se deseja obter o endereço da imagem.
	 */
	protected String recuperarEnderecoImagem(Object objeto) {
		return null;
	}

	/**
	 * Evento chamado ao expandir um item da árvore. Primeiro, tenta obter os
	 * itens em cache. Se não houver itens em cache, recupera objetos do banco.
	 */
	public class EventListenerExpandirItemArvore implements EventListener {

		public void onEvent(Event event) {
			// Obtém o item da árvore da interface onde ocorreu o evento
			Treeitem itemSelecionado = (Treeitem) event.getTarget();

			if (itemSelecionado.isOpen()) {
				try {
					// Executa a ação de expansão do item da árvore
					acaoExpandirItemArvore(itemSelecionado);

				} catch (NucleoRegraNegocioExcecao e) {
					exibirJanelaErro(e);
				}
			}
		}

		public boolean isAsap() {
			return true;
		}

	}

	/**
	 * Expande o item da árvore, fazendo acesso ao BD caso o item não tenha
	 * acessado ainda.
	 */
	public void acaoExpandirItemArvore(Treeitem itemArvoreInterface)
			throws NucleoRegraNegocioExcecao {

		// Limpa os subitens do item selecionado
		itemArvoreInterface.getTreechildren().getChildren().clear();

		// Obtém o item selecionado da árvore cache
		NucleoItemArvoreCache itemArvore = (NucleoItemArvoreCache) itemArvoreInterface
				.getValue();

		// Se não acessou o BD, atualiza o cache
		if (!itemArvore.isAcessouBD()) {
			// Apaga os filhos
			itemArvore.getFilhos().clear();

			// Adiciona os potenciais filhos do item na árvore cache
			adicionarFilhosEstruturaCache(itemArvore);

			// Garante que o atributo de acesso ao BD é verdadeiro
			itemArvore.setAcessouBD(true);
		}

		// Preeche os subitens do item selecionado
		List<NucleoItemArvoreCache> filhos = itemArvore.getFilhos();
		for (NucleoItemArvoreCache filho : filhos) {
			preencherItemArvore(filho, itemArvoreInterface.getTreechildren());
		}

		// Limpa os itens da árvore selecionados e em seguida atualiza a
		// exibição dos botões
		tree.clearSelection();
		this.configurarExibicaoBarraFerramentas();

	}

	/**
	 * Recupera do BD e adiciona os filhos de um item à árvore cache. Esse
	 * método deve ser implementado para obter, sob demanda, os filhos de um
	 * item que ainda não acessou o BD.
	 * 
	 * @param itemArvoreCache
	 *            Item da árvore cache sob o qual serão inseridos os filhos.
	 */
	protected abstract void adicionarFilhosEstruturaCache(
			NucleoItemArvoreCache itemArvoreCache) throws NucleoRegraNegocioExcecao;

	/** Classe responsável pela criação de um novo objeto organizador na árvore. */
	private class EventListenerNovoOrganizador implements EventListener {

		public void onEvent(Event event) {
			// Obtém os itens selecionados
			Set itensSelecionados = tree.getSelectedItems();

			// Verifica se o número de itens selecionados é menor ou igual a
			// 1.
			if (itensSelecionados.size() <= 1) {
				acaoMenuitemNovoOrganizador();
			} else {
				try {
					String mensagem = NucleoMensagens
							.getMensagem(NucleoMensagens.MSG_SELECIONE_APENAS_UM_ITEM);
					Messagebox.show(mensagem, NucleoMensagens
							.getMensagem(NucleoMensagens.TERMO_AVISO), 1,
							Messagebox.INFORMATION);
				} catch (Exception e) {
					exibirJanelaErro(e);
				}
			}

		}

		public boolean isAsap() {
			return true;
		}
	}

	/**
	 * Define a ação do item novo organizador.
	 */
	protected void acaoMenuitemNovoOrganizador() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("objeto", criarNovoObjetoDadosOrganizador());
		parametros.put("janelaPai", this);

		if (tree.getSelectedItem() != null) {
			parametros.put("treeitemPai", tree.getSelectedItem());
		} else {
			parametros.put("treeitemPai", null);
		}

		NucleoWindowCadastroDados<O> win = (NucleoWindowCadastroDados<O>) Executions
				.createComponents(this
						.definirNomeWindowCadastroDadosObjetoOrganizador(),
						null, parametros);
		try {
			win.doModal();
		} catch (Exception e) {
			System.out.println("Erro ao abrir a janela.");
		}
	}

	/**
	 * Cria um novo objeto organizador vazio.
	 * 
	 * @return Novo objeto organizador criado.
	 */
	protected abstract O criarNovoObjetoDadosOrganizador();

	/**
	 * Define o nome da window de cadastro de dados relacionada ao objeto
	 * organizador.
	 * 
	 * @return Nome da window de cadastro de dados relacionada.
	 */
	protected abstract String definirNomeWindowCadastroDadosObjetoOrganizador();

	/** Classe responsável pela criação de um novo objeto meio na árvore. */
	private class EventListenerNovoObjetoMeio implements EventListener {

		public void onEvent(Event event) {
			// Obtém os itens selecionados
			Set itensSelecionados = tree.getSelectedItems();

			// Verifica se o número de itens selecionados é menor ou igual a
			// 1.
			if (itensSelecionados.size() <= 1) {
				acaoMenuitemNovoObjetoMeio();
			} else {
				try {
					String mensagem = NucleoMensagens
							.getMensagem(NucleoMensagens.MSG_SELECIONE_APENAS_UM_ITEM);
					Messagebox.show(mensagem, NucleoMensagens
							.getMensagem(NucleoMensagens.TERMO_AVISO), 1,
							Messagebox.INFORMATION);
				} catch (Exception e) {
					exibirJanelaErro(e);
				}
			}
		}

		public boolean isAsap() {
			return true;
		}

	}

	/**
	 * Define a ação do item novo objeto meio.
	 */
	protected void acaoMenuitemNovoObjetoMeio() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("objeto", criarNovoObjetoDadosObjetoMeio());
		parametros.put("janelaPai", this);

		if (tree.getSelectedItem() != null) {
			parametros.put("treeitemPai", tree.getSelectedItem());
		} else {
			parametros.put("treeitemPai", null);
		}

		NucleoWindowCadastroDados<M> win = (NucleoWindowCadastroDados<M>) Executions
				.createComponents(this
						.definirNomeWindowCadastroDadosObjetoMeio(), null,
						parametros);
		try {
			win.doModal();
		} catch (Exception e) {
			System.out.println("Erro ao abrir a janela.");
		}
	}

	/**
	 * Cria um novo objeto meio vazio.
	 * 
	 * @return Novo objeto meio criado
	 */
	protected abstract M criarNovoObjetoDadosObjetoMeio();

	/**
	 * Define o nome da window de cadastro de dados relacionada ao objeto meio.
	 * 
	 * @return Nome da window de cadastro de dados relacionada.
	 */
	protected abstract String definirNomeWindowCadastroDadosObjetoMeio();

	/** Classe responsável pela criação de um novo objeto fim na árvore. */
	private class EventListenerNovoObjetoFim implements EventListener {

		public void onEvent(Event event) {
			// Obtém os itens selecionados
			Set itensSelecionados = tree.getSelectedItems();

			// Verifica se o número de itens selecionados é menor ou igual a
			// 1.
			if (itensSelecionados.size() <= 1) {
				acaoMenuitemNovoObjetoFim();
			} else {
				try {
					String mensagem = NucleoMensagens
							.getMensagem(NucleoMensagens.MSG_SELECIONE_APENAS_UM_ITEM);
					Messagebox.show(mensagem, NucleoMensagens
							.getMensagem(NucleoMensagens.TERMO_AVISO), 1,
							Messagebox.INFORMATION);
				} catch (Exception e) {
					exibirJanelaErro(e);
				}
			}
		}

		public boolean isAsap() {
			return true;
		}

	}

	/**
	 * Define a ação do item novo objeto fim.
	 */
	protected void acaoMenuitemNovoObjetoFim() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("objeto", criarNovoObjetoDadosObjetoFim());
		parametros.put("janelaPai", this);

		if (tree.getSelectedItem() != null) {
			parametros.put("treeitemPai", tree.getSelectedItem());
		} else {
			parametros.put("treeitemPai", null);
		}

		NucleoWindowCadastroDados<F> win = (NucleoWindowCadastroDados<F>) Executions
				.createComponents(this
						.definirNomeWindowCadastroDadosObjetoFim(), null,
						parametros);
		try {
			win.doModal();
		} catch (Exception e) {
			System.out.println("Erro ao abrir a janela.");
		}
	}

	/**
	 * Cria um novo objeto fim vazio.
	 * 
	 * @return Novo objeto fim criado
	 */
	protected abstract F criarNovoObjetoDadosObjetoFim();

	/**
	 * Define o nome da window de cadastro de dados relacionada ao objeto fim
	 * 
	 * @return Nome da window de cadastro de dados relacionada.
	 */
	protected abstract String definirNomeWindowCadastroDadosObjetoFim();

	/** Classe responsável pela abertura de um objeto na árvore. */
	private class EventListenerAbrir implements EventListener {

		public void onEvent(Event event) {
			// Obtém os itens selecionados
			Set itensSelecionados = tree.getSelectedItems();

			// Verifica se o número de itens selecionados é igual a 1
			if (itensSelecionados.size() == 1) {
				acaoMenuitemAbrir();
			} else {
				try {
					String mensagem = NucleoMensagens
							.getMensagem(NucleoMensagens.MSG_SELECIONE_APENAS_UM_ITEM);
					Messagebox.show(mensagem, NucleoMensagens
							.getMensagem(NucleoMensagens.TERMO_AVISO), 1,
							Messagebox.INFORMATION);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

		public boolean isAsap() {
			return true;
		}
	}

	/**
	 * Define a ação do item abrir.
	 */
	protected void acaoMenuitemAbrir() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		Object objetoSelecionado = ((NucleoItemArvoreCache) tree
				.getSelectedItem().getValue()).getObjeto();
		parametros.put("objeto", objetoSelecionado);
		parametros.put("janelaPai", this);

		// Verifica se existe um nó pai para o item selecionado
		if (tree.getSelectedItem().getParent().getParent() instanceof Tree) {
			// Não existe um nó pai. O objeto pai é passado como null
			parametros.put("treeitemPai", null);
		} else {
			// Existe um pai.
			parametros.put("treeitemPai", (Treeitem) tree.getSelectedItem()
					.getParent().getParent());
		}

		try {
			if (objetoSelecionado.getClass() == this
					.criarNovoObjetoDadosOrganizador().getClass()) {
				NucleoWindowCadastroDados<O> win = (NucleoWindowCadastroDados<O>) Executions
						.createComponents(
								this
										.definirNomeWindowCadastroDadosObjetoOrganizador(),
								null, parametros);
				win.doModal();
			} else if (objetoSelecionado.getClass() == this
					.criarNovoObjetoDadosObjetoMeio().getClass()) {
				NucleoWindowCadastroDados<M> win = (NucleoWindowCadastroDados<M>) Executions
						.createComponents(this
								.definirNomeWindowCadastroDadosObjetoMeio(),
								null, parametros);
				win.doModal();
			} else {
				NucleoWindowCadastroDados<F> win = (NucleoWindowCadastroDados<F>) Executions
						.createComponents(this
								.definirNomeWindowCadastroDadosObjetoFim(),
								null, parametros);
				win.doModal();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Classe responsável pela exclusão de um objeto na árvore. */
	private class EventListenerExcluir implements EventListener {

		public void onEvent(Event event) {
			Set itensSelecionados = tree.getSelectedItems();
			// verifica se o número de itens selecionados é maior que zero.
			if (itensSelecionados.size() > 0) {
				String mensagemConfirmacao;
				if (itensSelecionados.size() > 1) {
					mensagemConfirmacao = NucleoMensagens
							.getMensagem(NucleoMensagens.MSG_CONFIRMACAO_EXCLUSAO_PLURAL);
				} else {
					mensagemConfirmacao = NucleoMensagens
							.getMensagem(NucleoMensagens.MSG_CONFIRMACAO_EXCLUSAO_SINGULAR);
				}
				try {
					if (Messagebox
							.show(
									mensagemConfirmacao,
									NucleoMensagens
											.getMensagem(NucleoMensagens.TERMO_CONFIRMACAO),
									Messagebox.YES | Messagebox.NO,
									Messagebox.QUESTION) == Messagebox.YES) {

						// Executa a ação de exclusão
						acaoMenuitemExcluir();

						// Atualiza a árvore
						atualizarArvore();
					}

				} catch (Exception e) {
					exibirJanelaErro(e);
				}
			}
		}

		public boolean isAsap() {
			return true;
		}
	}

	/**
	 * Exclui os objetos selecionados.
	 */
	@SuppressWarnings("unchecked")
	protected void acaoMenuitemExcluir() throws DataAccessException,
			NucleoRegraNegocioExcecao {

		// Exclui os itens selecionados
		Set<Treeitem> itensSelecionados = tree.getSelectedItems();
		Iterator<Treeitem> itItensSelecionados = itensSelecionados.iterator();
		while (itItensSelecionados.hasNext()) {
			Treeitem itemSelecionado = itItensSelecionados.next();
			Object objeto = (Object) ((NucleoItemArvoreCache) itemSelecionado
					.getValue()).getObjeto();
			try {
				if (objeto.getClass() == this.criarNovoObjetoDadosOrganizador()
						.getClass()) {
					nucleoAplCadastroBaseOrganizador.excluir((O) objeto);
				} else if (objeto.getClass() == this
						.criarNovoObjetoDadosObjetoMeio().getClass()) {
					nucleoAplCadastroBaseObjetoMeio.excluir((M) objeto);
				} else {
					nucleoAplCadastroBaseObjetoFim.excluir((F) objeto);
				}
			} catch (Exception e) {
				exibirJanelaErro(e);
			}
		}
	}

	/** Classe do evento do item para cima. */
	private class EventListenerParaCima implements EventListener {

		public void onEvent(Event event) {
			// Obtém os itens selecionados
			Set itensSelecionados = tree.getSelectedItems();

			// Verifica se o número de itens selecionados é igual a 1
			if (itensSelecionados.size() == 1) {
				try {
					acaoMenuItemParaCima();
				} catch (Exception e) {
					exibirJanelaErro(e);
				}
			} else {
				try {
					String mensagem = NucleoMensagens
							.getMensagem(NucleoMensagens.MSG_SELECIONE_APENAS_UM_ITEM);
					Messagebox.show(mensagem, NucleoMensagens
							.getMensagem(NucleoMensagens.TERMO_AVISO), 1,
							Messagebox.INFORMATION);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

		public boolean isAsap() {
			return true;
		}

	}

	/**
	 * Ação do evento para cima.
	 * 
	 * @throws NucleoRegraNegocioExcecao
	 */
	protected void acaoMenuItemParaCima() throws NucleoRegraNegocioExcecao {

		Treeitem itemSelecionado = tree.getSelectedItem();

		Treechildren treechildrenSelecionado = (Treechildren) itemSelecionado
				.getParent();

		List itensNo = treechildrenSelecionado.getChildren();

		int posicao = -1;
		for (int i = 0; i < itensNo.size(); i++) {
			if (itensNo.get(i).equals(itemSelecionado)) {
				posicao = i;
				break;
			}
		}

		// Se o objeto da posição inicial não for o primeiro e os objetos a
		// serem movidos forem da mesma classe
		if ((posicao != 0)
				&& (((NucleoItemArvoreCache) itemSelecionado.getValue())
						.getObjeto().getClass()
						.equals(((NucleoItemArvoreCache) ((Treeitem) itensNo
								.get(posicao - 1)).getValue()).getObjeto()
								.getClass()))) {
			Object objetoPai = treechildrenSelecionado.getParent();
			NucleoItemArvoreCache itemArvore = null;

			// Verifica se existe pai
			if (objetoPai instanceof Treeitem) {
				itemArvore = (NucleoItemArvoreCache) ((Treeitem) objetoPai)
						.getValue();
				objetoPai = itemArvore.getObjeto();
			} else {
				objetoPai = null;
			}

			// Move o objeto no banco
			moverObjetoBanco(
					objetoPai,
					((NucleoItemArvoreCache) itemSelecionado.getValue())
							.getObjeto(),
					(Long) ((NucleoObjetoPersistenteImpl) ((NucleoItemArvoreCache) ((Treeitem) itensNo
							.get(posicao)).getValue()).getObjeto()).getId(),
					(Long) ((NucleoObjetoPersistenteImpl) ((NucleoItemArvoreCache) ((Treeitem) itensNo
							.get(posicao - 1)).getValue()).getObjeto()).getId());

			if (itemArvore != null) {
				// Atribui o acessou o bd como false
				itemArvore.setAcessouBD(false);
			}

			// Atualiza a interface
			treechildrenSelecionado.insertBefore(itemSelecionado,
					(Treeitem) itensNo.get(posicao - 1));

		}

	}

	/**
	 * Salva os objetos movidos no banco de acordo com sua ordem na interface.
	 * 
	 * @param objetoPai
	 *            Objeto pai dos objetos movidos.
	 * @param objetoPosicaoInicio
	 *            Objeto da posição inicial.
	 * @param idObjetoPosicaoInicio
	 *            Id do objeto da posição inicial.
	 * @param idObjetoPosicaoFim
	 *            Id do objeto da posição final.
	 * @throws NucleoRegraNegocioExcecao
	 */
	protected void moverObjetoBanco(Object objetoPai,
			Object objetoPosicaoInicio, Long idObjetoPosicaoInicio,
			Long idObjetoPosicaoFim) throws NucleoRegraNegocioExcecao {
	}

	/** Classe do evento do item para baixo. */
	private class EventListenerParaBaixo implements EventListener {

		public void onEvent(Event event) {
			// Obtém os itens selecionados
			Set itensSelecionados = tree.getSelectedItems();

			// Verifica se o número de itens selecionados é igual a 1
			if (itensSelecionados.size() == 1) {
				try {
					acaoMenuItemParaBaixo();
				} catch (Exception e) {
					exibirJanelaErro(e);
				}
			} else {
				try {
					String mensagem = NucleoMensagens
							.getMensagem(NucleoMensagens.MSG_SELECIONE_APENAS_UM_ITEM);
					Messagebox.show(mensagem, NucleoMensagens
							.getMensagem(NucleoMensagens.TERMO_AVISO), 1,
							Messagebox.INFORMATION);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

		public boolean isAsap() {
			return true;
		}

	}

	/**
	 * Ação do evento para baixo.
	 * 
	 * @throws NucleoRegraNegocioExcecao
	 */
	protected void acaoMenuItemParaBaixo() throws NucleoRegraNegocioExcecao {

		Treeitem itemSelecionado = tree.getSelectedItem();

		Treechildren treechildrenSelecionado = (Treechildren) itemSelecionado
				.getParent();

		List itensNo = treechildrenSelecionado.getChildren();

		int posicao = -1;
		for (int i = 0; i < itensNo.size(); i++) {
			if (itensNo.get(i).equals(itemSelecionado)) {
				posicao = i;
				break;
			}
		}

		// Se o objeto da posição inicial não for o último e os objetos a
		// serem movidos forem da mesma classe
		if ((posicao != (itensNo.size() - 1))
				&& (((NucleoItemArvoreCache) itemSelecionado.getValue())
						.getObjeto().getClass()
						.equals(((NucleoItemArvoreCache) ((Treeitem) itensNo
								.get(posicao + 1)).getValue()).getObjeto()
								.getClass()))) {

			Object objetoPai = treechildrenSelecionado.getParent();
			NucleoItemArvoreCache itemArvore = null;

			// Verifica se existe pai
			if (objetoPai instanceof Treeitem) {
				itemArvore = (NucleoItemArvoreCache) ((Treeitem) objetoPai)
						.getValue();
				objetoPai = itemArvore.getObjeto();
			} else {
				objetoPai = null;
			}

			// Move o objeto no banco
			moverObjetoBanco(
					objetoPai,
					((NucleoItemArvoreCache) itemSelecionado.getValue())
							.getObjeto(),
					(Long) ((NucleoObjetoPersistenteImpl) ((NucleoItemArvoreCache) ((Treeitem) itensNo
							.get(posicao)).getValue()).getObjeto()).getId(),
					(Long) ((NucleoObjetoPersistenteImpl) ((NucleoItemArvoreCache) ((Treeitem) itensNo
							.get(posicao + 1)).getValue()).getObjeto()).getId());

			if (itemArvore != null) {
				// Atribui o acessou o bd como false
				itemArvore.setAcessouBD(false);
			}

			// Atualiza a interface
			treechildrenSelecionado.insertBefore((Treeitem) itensNo
					.get(posicao + 1), itemSelecionado);
		}

	}

	/** Classe do evento para não selecionar os itens da árvore. */
	private class EventListenerLimparSelecao implements EventListener {

		public void onEvent(Event event) {
			tree.clearSelection();
			configurarExibicaoBarraFerramentas();
		}

		public boolean isAsap() {
			return true;
		}

	}

	/** Classe do evento atualizar a barra de ferramentas. */
	private class EventListenerAtualizarBarraFerramentas implements
			EventListener {

		public void onEvent(Event event) {
			configurarExibicaoBarraFerramentas();
		}

		public boolean isAsap() {
			return true;
		}

	}

	/** Classe do evento de alteração do filtro. */
	public class EventListenerAlteracaoFiltro implements EventListener {

		public void onEvent(Event event) {
			preencherTodaArvore();
		}

		public boolean isAsap() {
			return true;
		}

	}

	public void acaoExpandirItemPaiArvore() {

		// Recupera o item da interface a ser atualizado
		Treeitem itemArvoreInterface = (Treeitem) tree.getSelectedItem()
				.getParent().getParent();

		// Configura o item da árvore de cache para forçá-lo a
		// fazer acesso ao BD quando for atualizar a interface
		NucleoItemArvoreCache itemArvoreCache = (NucleoItemArvoreCache) itemArvoreInterface
				.getValue();
		itemArvoreCache.setAcessouBD(false);
		try {
			this.acaoExpandirItemArvore(itemArvoreInterface);
		} catch (NucleoRegraNegocioExcecao e) {
			exibirJanelaErro(e);
		}
	}

	public void setNucleoAplCadastroBaseOrganizador(
			NucleoAplCadastroBase<O> nucleoAplCadastroBase) {
		this.nucleoAplCadastroBaseOrganizador = nucleoAplCadastroBase;
	}

	public NucleoAplCadastroBase<O> getNucleoAplCadastroBaseOrganizador() {
		return this.nucleoAplCadastroBaseOrganizador;
	}

	public void setNucleoAplCadastroBaseObjetoMeio(
			NucleoAplCadastroBase<M> nucleoAplCadastroBase) {
		this.nucleoAplCadastroBaseObjetoMeio = nucleoAplCadastroBase;
	}

	public NucleoAplCadastroBase<M> getNucleoAplCadastroBaseObjetoMeio() {
		return this.nucleoAplCadastroBaseObjetoMeio;
	}

	public void setNucleoAplCadastroBaseObjetoFim(
			NucleoAplCadastroBase<F> nucleoAplCadastroBase) {
		this.nucleoAplCadastroBaseObjetoFim = nucleoAplCadastroBase;
	}

	public NucleoAplCadastroBase<F> getNucleoAplCadastroBaseObjetoFim() {
		return this.nucleoAplCadastroBaseObjetoFim;
	}

	protected Splitter splitter = new Splitter();

	/** Layout vertical para o painel. */
	protected Vbox vboxPainel = new Vbox();

	/** Tamanho (largura) do toolbar. */
	public static final String WIDTH_TOOLBAR = "100%";

	/** Tamanho (altura) do toolbar. */
	public static final String HEIGHT_TOOLBAR = "16px";

	/** Toolbar da window. */
	protected Toolbar toolbar = new Toolbar();

	/** Toolbarbutton novo. */
	protected Toolbarbutton tbbtNovo = new Toolbarbutton();

	/** Toolbarbutton abrir. */
	protected Toolbarbutton tbbtAbrir = new Toolbarbutton();

	/** Toolbarbutton excluir. */
	protected Toolbarbutton tbbtExcluir = new Toolbarbutton();

	/** Toolbarbutton para cima. */
	protected Toolbarbutton tbbtParaCima = new Toolbarbutton();

	/** Toolbarbutton para baixo. */
	protected Toolbarbutton tbbtParaBaixo = new Toolbarbutton();

	/** Espaço(linha) em branco. */
	protected Separator separator = new Separator();

	/**
	 * Caixa de filtro
	 */
	protected Groupbox groupboxFiltro = new Groupbox();

	/** Caption do filtro. */
	private Caption captionFiltro = new Caption(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_FILTRO));

	/** árvore. */
	protected Tree tree = new Tree();

	/** Colunas da árvore */
	protected Treecols treecols = new Treecols();

	/** Menu popup. */
	protected Menupopup menupopup = new Menupopup();

	/** Menu popup novo. */
	protected Menupopup menupopupNovo = new Menupopup();

	/** Menu item novo organizador. */
	protected Menuitem menuitemNovoOrganizador = new Menuitem(
			"Novo Organizador");

	/** Menu item novo objeto meio. */
	protected Menuitem menuitemNovoObjetoMeio = new Menuitem();

	/** Menu item novo objeto fim. */
	protected Menuitem menuitemNovoObjetoFim = new Menuitem();

	/** Menu item abrir. */
	protected Menuitem menuitemAbrir = new Menuitem(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_ABRIR));

	/** Menu item excluir. */
	protected Menuitem menuitemExcluir = new Menuitem(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_EXCLUIR));

	/** Linha em branco. */
	protected Menuseparator menuseparator = new Menuseparator();

	/** Menu item acima. */
	protected Menuitem menuitemAcima = new Menuitem(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_MOVER_ITEM_PARA_CIMA));

	/** Menu item Abaixo. */
	protected Menuitem menuitemAbaixo = new Menuitem(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_MOVER_ITEM_PARA_BAIXO));

}
