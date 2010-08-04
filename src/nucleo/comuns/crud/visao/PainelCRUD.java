package nucleo.comuns.crud.visao;

import java.util.Set;

import nucleo.comuns.crud.controlador.CtrlCRUD;
import nucleo.comuns.excecao.CtrlExcecoes;
import nucleo.comuns.persistencia.ObjetoPersistente;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.paginacao.ListagemPaginada;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;

public abstract class PainelCRUD<T extends ObjetoPersistente> extends Vbox {

	protected Toolbar toolbar;

	protected ListagemPaginada<T> listagemPaginada;

	protected CtrlCRUD<T> controlador;

	public PainelCRUD() {
	}

	public abstract ListagemPaginada<T> definirListagem();

	public void configurarComponentes() {
		definirComponentes();
		definirComponentesExtensao();
		validarComponentesExtensao();
		setWidth("98%");
		// configuro o responsavel por atualizar a listagem. No caso, é o
		// controlador
		listagemPaginada.setAtualizador(controlador);
		listagemPaginada.configurarComponentes();

		montar();
	}

	private void montar() {
		toolbar.setParent(this);
		listagemPaginada.setParent(this);		
	}

	protected void definirComponentesExtensao() {
		listagemPaginada = definirListagem();

	}

	protected void validarComponentesExtensao() {

		if (controlador == null)
			throw CtrlExcecoes.factoryExcecaoDefinicao("controlador",
					this.getClass());

		if (listagemPaginada == null)
			throw CtrlExcecoes.factoryExcecaoDefinicao(
					"listagemPaginada", this.getClass());

	}

	public ListagemPaginada<T> getListagemPaginada() {
		return listagemPaginada;
	}

	public void setListagemPaginada(ListagemPaginada<T> listagemPaginada) {
		this.listagemPaginada = listagemPaginada;
	}

	protected Toolbar definirBarraFerramentas() {

		toolbar = new Toolbar();

		Toolbarbutton tbbtNovo = new Toolbarbutton();
		Toolbarbutton tbbtAbrir = new Toolbarbutton();
		Toolbarbutton tbbtExcluir = new Toolbarbutton();

		tbbtNovo.setImage("/imagens/filenew.png");
		tbbtAbrir.setImage("/imagens/fileopen.png");
		tbbtExcluir.setImage("/imagens/editdelete.png");
		tbbtNovo.setTooltiptext(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_NOVO));
		tbbtAbrir.setTooltiptext(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_ABRIR));
		tbbtExcluir.setTooltiptext(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_EXCLUIR));

		tbbtNovo.setParent(toolbar);
		tbbtAbrir.setParent(toolbar);
		tbbtExcluir.setParent(toolbar);

		tbbtNovo.addEventListener("onClick", new EventListenerNovo());
		tbbtAbrir.addEventListener("onClick", new EventListenerAbrir());
		tbbtExcluir.addEventListener("onClick", new EventListenerExcluir());

		return toolbar;

	}

	protected void definirComponentes() {

		toolbar = definirBarraFerramentas();
	}

	/** Classe do evento do botao novo. */
	private class EventListenerNovo implements EventListener {

		public void onEvent(Event event) {
			acaoBotaoNovo();
		}

		public boolean isAsap() {
			return true;
		}

	}

	protected void acaoBotaoNovo() {
		controlador.acaoNovo();

	}

	/** Classe do evento do botao excluir. */
	private class EventListenerExcluir implements EventListener {

		public void onEvent(Event event) {

			controlador.acaoExcluir();
		}

		public boolean isAsap() {
			return true;
		}

	}

	/** Classe do evento do botao abrir. */
	private class EventListenerAbrir implements EventListener {

		public void onEvent(Event event) {
			// Obtém os itens selecionados
			Set itensSelecionados = getListagemPaginada().getSelecionados();

			// Verifica se o número de itens selecionados é maior que zero.
			if (itensSelecionados.size() == 1) {
				controlador.acaoAbrir();
			} else {
				try {
					String mensagem = NucleoMensagens
							.getMensagem(NucleoMensagens.MSG_NENHUM_ELEMENTO_SELECIONADO);
					if (itensSelecionados.size() > 1) {
						mensagem = NucleoMensagens
								.getMensagem(NucleoMensagens.MSG_SELECIONE_APENAS_UM_ITEM);
					}
					Messagebox.show(mensagem, NucleoMensagens
							.getMensagem(NucleoMensagens.TERMO_AVISO), 1,
							Messagebox.INFORMATION);
				} catch (Exception e) {

					CtrlExcecoes.tratarExcecao(e);
				}
			}
		}

		public boolean isAsap() {
			return true;
		}

	}

	public CtrlCRUD<T> getControlador() {
		return controlador;
	}

	public void setControlador(CtrlCRUD<T> controlador) {
		this.controlador = controlador;
	}

}
