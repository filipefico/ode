package ode.nucleo.crud.cih;

import java.util.Set;

import nucleo.comuns.excecao.CtrlExcecoes;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.listagem.ListagemSimples;

import ode.nucleo.cgd.ObjetoPersistente;
import ode.nucleo.crud.cci.CtrlCRUD;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vlayout;

public abstract class PainelCRUD<T extends ObjetoPersistente> extends Vlayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8896639132796378556L;

	protected Toolbar toolbar;

	protected ListagemSimples<T> listagem;

	protected CtrlCRUD<T> controlador;

	public PainelCRUD() {
	}

	public abstract ListagemSimples<T> definirListagem();

	public void configurarComponentes() {
		definirComponentes();
		definirComponentesExtensao();
		validarComponentesExtensao();
		// configuro o responsavel por atualizar a listagem. No caso, � o
		// controlador
		listagem.setAtualizador(controlador);
		listagem.configurarComponentes();

		montar();
	}

	private void montar() {
		toolbar.setParent(this);
		listagem.setParent(this);		
	}

	protected void definirComponentesExtensao() {
		listagem = definirListagem();

	}

	protected void validarComponentesExtensao() {

		if (controlador == null)
			throw CtrlExcecoes.factoryExcecaoDefinicao("controlador",
					this.getClass());

		if (listagem == null)
			throw CtrlExcecoes.factoryExcecaoDefinicao(
					"listagemPaginada", this.getClass());

	}

	public ListagemSimples<T> getListagem() {
		return listagem;
	}

	public void setListagem(ListagemSimples<T> listagemPaginada) {
		this.listagem = listagemPaginada;
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
		toolbar.setStyle("border:0px;background:white;");
		
	}

	/** Classe do evento do botao novo. */
	private class EventListenerNovo implements EventListener {

		public void onEvent(Event event) {
			acaoBotaoNovo();
		}

		@SuppressWarnings("unused")
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

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}

	}

	/** Classe do evento do botao abrir. */
	private class EventListenerAbrir implements EventListener {

		public void onEvent(Event event) {
			// Obt�m os itens selecionados
			Set itensSelecionados = getListagem().getSelecionados();

			// Verifica se o n�mero de itens selecionados � maior que zero.
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

		@SuppressWarnings("unused")
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
