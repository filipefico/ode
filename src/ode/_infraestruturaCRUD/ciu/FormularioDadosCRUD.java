package ode._infraestruturaCRUD.ciu;

import java.util.List;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.ciu.NucleoTabbox;
import ode._infraestruturaBase.excecao.CtrlExcecoes;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;

import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.impl.XulElement;

public abstract class FormularioDadosCRUD<T extends ObjetoPersistente> extends Vlayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2269516935940124572L;

	/** Tamanho do botão. */
	public static final String WIDTH_BUTTON = "80px";

	/** Toolbar da window. */
	protected Toolbar toolbar = new Toolbar();

	/** Button cancelar. */
	protected Button buttonCancelar = new Button();
	
	/** Button salvar. */
	protected Button buttonSalvar = new Button();
	
	/** Grupo de abas. */
	protected NucleoTabbox tabbox = new NucleoTabbox();

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
		definirComponentes();
		validarComponentes();
		configurarBarraFerramentas();
		configurarComponentesExtensaoBarraFerramentas(toolbar);
		configurarComponentesExtensao();
		configurarConstraints();

		montar();

	}

	private void montar() {
		montarTabs();
		toolbar.setParent(this);
		buttonSalvar.setParent(toolbar);
		buttonCancelar.setParent(toolbar);
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
		tabbox.setTabs(listaTab);
	}

	protected void configurarBarraFerramentas() {
		
		// Configuração da barra de ferramenpreencherDadosTelatas

		toolbar.setStyle("border:0px;background:white;");
		toolbar.setAlign("end");
		
		buttonSalvar.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_SALVAR));
		buttonSalvar.addEventListener("onClick", new EventListenerSalvar());
		buttonSalvar.setWidth(WIDTH_BUTTON);
		buttonCancelar.setLabel(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_CANCELAR));
		buttonCancelar.addEventListener("onClick", new EventListenerCancelar());
		buttonCancelar.setWidth(WIDTH_BUTTON);	

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
	
	/** Classe do evento do botão cancelar. */
	public class EventListenerCancelar implements EventListener {

		public void onEvent(Event event) {
			controlador.atualizarPesquisa();
			controlador.getJanDados().onClose();
		}

		public boolean isAsap() {
			return true;
		}

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

	protected boolean isValido()
	{
		return true;
	}
	public CtrlCRUD<T> getControlador() {
		return controlador;
	}

	public void setControlador(CtrlCRUD<T> controlador) {
		this.controlador = controlador;
	}
	
	public void disparaErro(XulElement element, String message) {
			throw new WrongValueException(element,	message);
	}

}
