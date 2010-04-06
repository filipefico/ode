package nucleo.comuns.visao;

import java.util.List;
import java.util.Map;

import nucleo.comuns.aplicacao.NucleoAplCadastroBase;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.persistencia.NucleoObjetoPersistenteImpl;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.cache.arvore.NucleoItemArvoreCache;
import nucleo.comuns.visao.componentes.NucleoTab;

import org.springframework.dao.DataAccessException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.XulElement;


/**
 * Classe básica para criação de janelas de cadastro com dados para alteração.
 * 
 * @author Alexandre G. N. Coelho
 * 
 * @param <T>
 *            Classe do objeto cujos dados são alterados na janela.
 */
public abstract class NucleoWindowCadastroDados<T extends NucleoObjetoPersistenteImpl<Long, Long>>
		extends NucleoWindow {

	/**
	 * Interface de aplicação usada para cadastros básicos (CRUD)
	 */
	private NucleoAplCadastroBase<T> nucleoAplCadastroBase;

	/**
	 * Parâmetros passados para a janela.
	 */
	private Map<String, Object> parametros;

	/**
	 * Objeto cujos dados são apresentados na janela de cadastro de dados.
	 */
	private T objetoCadastroDados;

	public void setNucleoAplCadastroBase(
			NucleoAplCadastroBase<T> nucleoAplCadastroBase) {
		this.nucleoAplCadastroBase = nucleoAplCadastroBase;
	}

	public NucleoAplCadastroBase<T> getNucleoAplCadastroBase() {
		return nucleoAplCadastroBase;
	}

	public Map<String, Object> getParametros() {
		return parametros;
	}

	public void setParametros(Map<String, Object> parametros) {
		this.parametros = parametros;
	}

	public T getObjetoCadastroDados() {
		return objetoCadastroDados;
	}

	public void setObjetoCadastroDados(T objetoCadastroDados) {
		this.objetoCadastroDados = objetoCadastroDados;
	}

	@SuppressWarnings("unchecked")
	public NucleoWindowCadastroDados() {
		super();

		parametros = Executions.getCurrent().getArg();
		T objeto = (T) parametros.get("objeto");
		this.setObjetoCadastroDados(objeto);
	}

	@Override
	public void onCreateWindow() {
		try {

			// Realiza a configuração inicial da janela
			iniciarComponentesInterface();

			// Preenche os conteúdos dos componentes
			preencherDadosTela();

			// Configura constraints dos campos
			// As constraints devem ser configuradas à parte, após o
			// preenchimento dos dados, para evitar disparo de exceções
			configurarConstraints();

			// Altera o modo da janela pai para Highlighted
			Window parentWin = (Window) parametros.get("janelaPai");
			if (parentWin != null) {
				parentWin.doEmbedded();
			}

		} catch (Exception e) {
			exibirJanelaErro(e);
		}
	}

	/**
	 * Inicia componentes de interface. Deve ser passado como parâmetro: um
	 * vetor contendo os nomes das abas; um vetor contendo o conteudo de cada
	 * aba.
	 * 
	 * @throws NucleoRegraNegocioExcecao
	 * @throws DataAccessException
	 * 
	 */
	private void iniciarComponentesInterface() throws DataAccessException,
			NucleoRegraNegocioExcecao {
		// Configura os componentes
		this.configurarComponentes();

		// Adiciona os componentes
		this.adicionarComponentes();

	}

	private void configurarComponentes() {
		// ////////////////////////////////////
		// Configuração da janela
		// ////////////////////////////////////
		this.addEventListener("onClose", new EventListenerFechar());

		// ////////////////////////////////////
		// Configuração da barra de ferramentas
		// ////////////////////////////////////
		toolbar.setWidth(WIDTH_TOOLBAR);
		tbbtSalvar.setTooltiptext(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_SALVAR));
		tbbtSalvar.setImage("/imagens/filesave.png");
		tbbtSalvar.addEventListener("onClick", new EventListenerSalvar());

		// ////////////////////////////////////
		// Define configurações extras
		// ////////////////////////////////////
		configurarComponentesExtensao();
	}

	/**
	 * Define configurações extras (inclusive reconfigurações) para os
	 * componentes da interface no que diz respeito a dimensões, imagem
	 * utilizada, tooltip, eventos, etc. Esse método deve ser sobrescrito por
	 * classes que herdam de NucleoWindowCadastroDados e necessitem de realizar
	 * configurações extras sobre componentes já existentes ou configurar novos
	 * componentes.
	 */
	protected void configurarComponentesExtensao() {
	}

	private void adicionarComponentes() throws DataAccessException,
			NucleoRegraNegocioExcecao {

		// //////////////////////////////////
		// Barra de ferramentas
		// /////////////////////////////////
		toolbar.setParent(this);
		tbbtSalvar.setParent(toolbar);

		// Adiciona novos elementos à barra de ferramentas
		adicionarComponentesExtensaoBarraFerramentas(toolbar);

		// //////////////////////////////////
		// Painel de abas
		// /////////////////////////////////
		separator.setParent(this);
		tabbox.setParent(this);
		tabs.setParent(tabbox);
		tabpanels.setParent(tabbox);

		// //////////////////////////////////
		// Adiciona os componentes específicos
		// /////////////////////////////////

		List<NucleoTab> listaTab = definirTabs();
		for (NucleoTab nucleoTab : listaTab) {
			// Cria tab
			Tab tab = new Tab(nucleoTab.getNomeTab());
			tab.setParent(tabs);

			// Cria painel com conteúdo
			Tabpanel tabpanel = new Tabpanel();
			tabpanel.setParent(tabpanels);
			XulElement conteudo = nucleoTab.getConteudoTab();
			conteudo.setParent(tabpanel);
		}
	}

	/**
	 * Adiciona novos componentes à barra de ferramentas
	 */
	protected void adicionarComponentesExtensaoBarraFerramentas(Toolbar toolbar) {
	}

	protected abstract List<NucleoTab> definirTabs() throws DataAccessException,
			NucleoRegraNegocioExcecao;

	protected abstract void preencherDadosTela() throws NucleoRegraNegocioExcecao;

	/**
	 * Configura as constraints a serem associadas aos campos das telas. É
	 * fundamental que as constraints sejam configuradas após o preenchimento
	 * dos dados das telas, pois o próprio preenchimento pode gerar disparo de
	 * exceções caso não esteja de acordo com as constraints.
	 */
	protected void configurarConstraints() {

	}

	/**
	 * Salva o objeto.
	 */
	protected void acaoBotaoSalvar() throws DataAccessException,
			NucleoRegraNegocioExcecao {

		// Preenche os dados do objeto com os dados da tela
		this.preencherDadosObjeto();

		// Salva o objeto e o atribui como objeto cadastro dados
		setObjetoCadastroDados(nucleoAplCadastroBase.salvar(this
				.getObjetoCadastroDados()));

		// Preenche novamente os dados da tela
		preencherDadosTela();
	}

	protected abstract void preencherDadosObjeto();

	/**
	 * Esse método pode ser implementado casa haja necessidade de executar algo
	 * após salvar
	 */
	protected void acaoDepoisAcaoBotaoSalvar() {
	}

	/** Classe do evento fechar janela */
	private class EventListenerFechar implements EventListener {

		@SuppressWarnings("unchecked")
		public void onEvent(Event event) {
			acaoFecharJanela();
		}

		public boolean isAsap() {
			return true;
		}

	}

	protected void acaoFecharJanela() {
		if (getParent() != null) {
			Component parentWin = (Window) parametros.get("janelaPai");
			if (parentWin instanceof NucleoWindowCadastroLista) {
				NucleoWindowCadastroLista<T> nucleoWindowCadastroLista = (NucleoWindowCadastroLista<T>) parentWin;
				nucleoWindowCadastroLista.atualizarLista();

			} else if (parentWin instanceof NucleoWindowCadastroTree) {

				// Expande o item, atualizando seus dados a partir do BD
				NucleoWindowCadastroTree nucleoWindowCadastroTree = (NucleoWindowCadastroTree) parentWin;

				// Recupera o item da interface a ser atualizado
				Treeitem itemArvoreInterface = (Treeitem) getParametros().get(
						"treeitemPai");

				if (itemArvoreInterface == null) {
					nucleoWindowCadastroTree.atualizarArvore();
				} else {
					// Configura o item da árvore de cache para forçá-lo a fazer
					// acesso ao BD quando for atualizar a interface
					NucleoItemArvoreCache itemArvoreCache = (NucleoItemArvoreCache) itemArvoreInterface
							.getValue();
					itemArvoreCache.setAcessouBD(false);

					try {
						nucleoWindowCadastroTree
								.acaoExpandirItemArvore(itemArvoreInterface);
					} catch (NucleoRegraNegocioExcecao e) {
						exibirJanelaErro(e);
					}
				}
			} else if (parentWin instanceof NucleoWindowCadastroDuploTree) {

				// Expande o item, atualizando seus dados a partir do BD
				NucleoWindowCadastroDuploTree nucleoWindowCadastroDuploTree = (NucleoWindowCadastroDuploTree) parentWin;

				// Recupera o item da interface a ser atualizado
				Treeitem itemArvoreInterface = (Treeitem) getParametros().get(
						"treeitemPai");

				if (itemArvoreInterface == null) {
					nucleoWindowCadastroDuploTree.atualizarArvore();
				} else {
					// Configura o item da árvore de cache para forçá-lo a
					// fazer acesso ao BD quando for atualizar a interface
					NucleoItemArvoreCache itemArvoreCache = (NucleoItemArvoreCache) itemArvoreInterface
							.getValue();
					itemArvoreCache.setAcessouBD(false);

					try {
						nucleoWindowCadastroDuploTree
								.acaoExpandirItemArvore(itemArvoreInterface);
					} catch (NucleoRegraNegocioExcecao e) {
						exibirJanelaErro(e);
					}
				}
			} else if (parentWin instanceof NucleoWindowCadastroTriploTree) {

				// Expande o item, atualizando seus dados a partir do BD
				NucleoWindowCadastroTriploTree nucleoWindowCadastroTriploTree = (NucleoWindowCadastroTriploTree) parentWin;

				// Recupera o item da interface a ser atualizado
//				Treeitem itemArvoreInterface = (Treeitem) getParametros().get(
//						"treeitemPai");

//				if (itemArvoreInterface == null) {
					nucleoWindowCadastroTriploTree.atualizarArvore();
//				} else {
//					// Configura o item da árvore de cache para forçá-lo a
//					// fazer acesso ao BD quando for atualizar a interface
//					NucleoItemArvoreCache itemArvoreCache = (NucleoItemArvoreCache) itemArvoreInterface
//							.getValue();
//					itemArvoreCache.setAcessouBD(false);
//
//					try {
//						nucleoWindowCadastroTriploTree
//								.acaoExpandirItemArvore(itemArvoreInterface);
//					} catch (NucleoRegraNegocioExcecao e) {
//						exibirJanelaErro(e);
//					}
//				}
			}

			// Altera o modo da janela pai para Overlapped
			if (parentWin != null) {
				((Window) parentWin).doOverlapped();
			}
		}
	}

	/** Classe do evento do botão salvar. */
	public class EventListenerSalvar implements EventListener {

		public void onEvent(Event event) {
			try {
				acaoBotaoSalvar();
				Messagebox.show(NucleoMensagens
						.getMensagem(NucleoMensagens.MSG_DADOS_SALVOS_SUCESSO),
						NucleoMensagens
								.getMensagem(NucleoMensagens.TERMO_INFORMACAO),
						Messagebox.OK, Messagebox.INFORMATION);
				acaoDepoisAcaoBotaoSalvar();
			} catch (Exception e) {
				exibirJanelaErro(e);
				acaoExcecao(e);
			}
		}

		public boolean isAsap() {
			return true;
		}

	}

	/** Tamanho do botão. */
	public static final String WIDTH_BUTTON = "80px";

	/** Tamanho do hbox. */
	public static final String WIDTH_HBOX = "200px";

	/** Tamanho do toolbar. */
	public static final String WIDTH_TOOLBAR = "99%";

	/** Toolbar da window. */
	protected Toolbar toolbar = new Toolbar();

	/** Toolbarbutton salvar. */
	protected Toolbarbutton tbbtSalvar = new Toolbarbutton();

	/** Espaço(linha) em branco. */
	private Separator separator = new Separator();

	/** Grupo de abas. */
	protected Tabbox tabbox = new Tabbox();

	/** União de abas. */
	protected Tabs tabs = new Tabs();

	/** Grupo de paineis */
	protected Tabpanels tabpanels = new Tabpanels();

}
