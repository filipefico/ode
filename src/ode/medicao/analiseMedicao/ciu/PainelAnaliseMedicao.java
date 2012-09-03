package ode.medicao.analiseMedicao.ciu;

import java.util.Set;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.excecao.CtrlExcecoes;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.medicao.analiseMedicao.cdp.AnaliseMedicao;

public class PainelAnaliseMedicao extends Vbox{

	private static final long serialVersionUID = 8896639132796378556L;

	protected Toolbar toolbar;

	protected ListagemSimples<AnaliseMedicao> listagem;

	protected CtrlAnaliseMedicao controlador;

	public PainelAnaliseMedicao(CtrlAnaliseMedicao ctrlAnaliseMedicao) {
		this.controlador = ctrlAnaliseMedicao;
	}

	public void configurarComponentes() {
		definirComponentes();
		definirComponentesExtensao();
		validarComponentesExtensao();
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

	public ListagemSimples<AnaliseMedicao> getListagem() {
		return listagem;
	}

	public void setListagem(ListagemSimples<AnaliseMedicao> listagemPaginada) {
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

	public void definirComponentes() {

		
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

	public CtrlAnaliseMedicao getControlador() {
		return controlador;
	}

	public void setControlador(CtrlAnaliseMedicao controlador) {
		this.controlador = controlador;
	}

	public Toolbar getToolbar() {
		return this.toolbar;
	}
	
	public ListagemSimples<AnaliseMedicao> definirListagem() {
		return new ListagemAnaliseMedicao();
	}

	public void mostrar() {
		this.getListagem().atualizar(controlador.getAnaliseMedicao());
	}

}
