package nucleo.comuns.crud.visao;

import java.util.List;

import nucleo.comuns.crud.controlador.CtrlCRUD;
import nucleo.comuns.excecao.CtrlExcecoes;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.persistencia.ObjetoPersistente;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.componentes.NucleoTab;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.impl.XulElement;

public abstract class FormularioDadosCRUD<T extends ObjetoPersistente> extends Vbox {

	public enum ModoExibicao {
		EDITAR, NOVO, CONSULTAR
	}

	private ModoExibicao modoExibicao = ModoExibicao.NOVO;
	/**
	 * 
	 */
	private static final long serialVersionUID = 2269516935940124572L;

	/** Tamanho do botão. */
	public static final String WIDTH_BUTTON = "100px";

	/** Tamanho do hbox. */
	public static final String WIDTH_HBOX = "1000px";

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

	List<NucleoTab> listaTab;

	private T objetoCadastroDados;

	private CtrlCRUD<T> controlador;

	public T getObjetoCadastroDados() {
		return objetoCadastroDados;
	}

	public void setObjetoCadastroDados(T objetoCadastroDados) {
		this.objetoCadastroDados = objetoCadastroDados;
	}

	public void configurarComponentes() {
		this.setWidth("100%");
		this.setHeight("100%");
		definirComponentes();
		validarComponentes();
		configurarBarraFerramentas();
		configurarComponentesExtensaoBarraFerramentas(toolbar);
		configurarComponentesExtensao();
		configurarConstraints();

		montar();

	}

	private void montar() {
		toolbar.setParent(this);
		tbbtSalvar.setParent(toolbar);
		separator.setParent(this);
		montarTabs();
	}

	private void validarComponentes() {
		if (listaTab == null)
			throw CtrlExcecoes.factoryExcecaoDefinicao("listaTab", this
					.getClass());
		if (controlador == null)
			throw CtrlExcecoes.factoryExcecaoDefinicao("controlador",
					this.getClass());

	}

	private void definirComponentes() {
		listaTab = definirTabs();

	}

	protected void montarTabs() {

		tabbox.setParent(this);
		tabs.setParent(tabbox);
		tabpanels.setParent(tabbox);

		// //////////////////////////////////
		// Adiciona os componentes específicos
		// /////////////////////////////////

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

	protected void configurarBarraFerramentas() {
		// Configuração da barra de ferramentas

		toolbar.setWidth(WIDTH_TOOLBAR);
		tbbtSalvar.setTooltiptext(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_SALVAR));
		tbbtSalvar.setImage("/imagens/filesave.png");
		tbbtSalvar.addEventListener("onClick", new EventListenerSalvar());

		configurarComponentesExtensaoBarraFerramentas(toolbar);

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

	/**
	 * Adiciona novos componentes à barra de ferramentas
	 */
	protected void configurarComponentesExtensaoBarraFerramentas(Toolbar toolbar) {
	}

	protected abstract List<NucleoTab> definirTabs();

	public void atualizarObjeto() {

		preencherDadosObjeto(getObjetoCadastroDados());

	};

	public void atualizarDadosTela() throws NucleoRegraNegocioExcecao {

		preencherDadosTela(getObjetoCadastroDados());

	};
/**
 * Copia os dados do objeto para a tela
 * */
	protected abstract void preencherDadosTela(T objeto) throws NucleoRegraNegocioExcecao;

	/**
	 * Copia os dados da tela para o objeto do cadastro
	 * */
	protected abstract void preencherDadosObjeto(T objeto);

	/**
	 * Configura as constraints a serem associadas aos campos das telas. É
	 * fundamental que as constraints sejam configuradas após o preenchimento
	 * dos dados das telas, pois o próprio preenchimento pode gerar disparo de
	 * exceções caso não esteja de acordo com as constraints.
	 */
	protected void configurarConstraints() {

	}

	/** Classe do evento do botão salvar. */
	public class EventListenerSalvar implements EventListener {

		public void onEvent(Event event) {

			if (isValido()) {
				controlador.acaoSalvar();
			}

		}

		public boolean isAsap() {
			return true;
		}

	}

	private boolean isValido() {
		return true;
	}

	public CtrlCRUD<T> getControlador() {
		return controlador;
	}

	public void setControlador(CtrlCRUD<T> controlador) {
		this.controlador = controlador;
	}

	public void setModoExibicao(ModoExibicao modoExibicao) {
		this.modoExibicao = modoExibicao;

	}

}
