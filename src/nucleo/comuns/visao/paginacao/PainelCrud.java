package nucleo.comuns.visao.paginacao;

import java.util.Iterator;
import java.util.Set;
import nucleo.comuns.aplicacao.NucleoAplCadastroBase;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.persistencia.ObjetoPersistente;
import nucleo.comuns.util.NucleoMensagens;
import org.springframework.dao.DataAccessException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;

public abstract class PainelCrud<T extends ObjetoPersistente> extends Vbox {

	/** Toolbar da window. */
	protected Toolbar toolbar;

	/** Toolbarbutton novo. */
	protected Toolbarbutton tbbtNovo = new Toolbarbutton();

	/** Toolbarbutton abrir. */
	protected Toolbarbutton tbbtAbrir = new Toolbarbutton();

	/** Toolbarbutton excluir. */
	protected Toolbarbutton tbbtExcluir = new Toolbarbutton();
	
	protected ListagemPaginada<T> listagemPaginada;
	
	/**
	 * Interface de aplicação usada para cadastros básicos (CRUD)
	 */
	private NucleoAplCadastroBase<T> nucleoAplCadastroBase;

	public PainelCrud() {			
		adicionarComponentes();
	}
	

	public void setNucleoAplCadastroBase(
			NucleoAplCadastroBase<T> nucleoAplCadastroBase) {
		this.nucleoAplCadastroBase = nucleoAplCadastroBase;
	}

	public NucleoAplCadastroBase<T> getNucleoAplCadastroBase() {
		return nucleoAplCadastroBase;
	}

	protected Toolbar definirBarraFerramentas() {

		toolbar = new Toolbar();
		
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
	//	tbbtAbrir.addEventListener("onClick", new EventListenerAbrir());
		tbbtExcluir.addEventListener("onClick", new EventListenerExcluir());


		return toolbar;

	}

	protected void adicionarComponentes() {
		// ////////////////////////////////////
		// Barra de ferramentas
		// ////////////////////////////////////
		setWidth("98%");
		toolbar = definirBarraFerramentas();
		toolbar.setParent(this);
		
		listagemPaginada = definirListagem();
		//adiciona a listagem no painel
		if ( listagemPaginada != null)
			listagemPaginada.setParent(this);
		else throw new RuntimeException("Não foi configurado o objeto de listagemPaginada em "+this.getClass());
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
		/*
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("objeto", criarNovoObjetoDados());
		parametros.put("janelaPai", this);
		
		NucleoWindowCadastroDados<T> win = (NucleoWindowCadastroDados<T>) Executions
				.createComponents(this.definirNomeWindowCadastroDados(), null,
						parametros);
		try {
			win.doModal();
		} catch (Exception e) {
			System.out.println("Deu errado a abertura da janela.");
		}
		*/
	}
	
	/** Classe do evento do botao excluir. */
	private class EventListenerExcluir implements EventListener {

		public void onEvent(Event event) {
			Set itensSelecionados = listagemPaginada.getSelecionados();

			// verifica se o número de itens selecionados é maior que zero.
			if (itensSelecionados.size() > 0) {
				try {
					String mensagemConfirmacao;
					if (itensSelecionados.size() > 1) {
						mensagemConfirmacao = NucleoMensagens
								.getMensagem(NucleoMensagens.MSG_CONFIRMACAO_EXCLUSAO_PLURAL);
					} else {
						mensagemConfirmacao = NucleoMensagens
								.getMensagem(NucleoMensagens.MSG_CONFIRMACAO_EXCLUSAO_SINGULAR);
					}
					if (Messagebox
							.show(
									mensagemConfirmacao,
									NucleoMensagens
											.getMensagem(NucleoMensagens.TERMO_CONFIRMACAO),
									Messagebox.YES | Messagebox.NO,
									Messagebox.QUESTION) == Messagebox.YES) {

						// Executa a ação de exclusão
						acaoBotaoExcluir();

					
					}
				} catch (Exception e) {
					ErrorHandling.exibirJanelaErro(e);
				}
			} else {
				try {
					Messagebox
							.show(
									NucleoMensagens
											.getMensagem(NucleoMensagens.MSG_NENHUM_ELEMENTO_SELECIONADO),
									NucleoMensagens
											.getMensagem(NucleoMensagens.TERMO_AVISO),
									1, Messagebox.INFORMATION);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		public boolean isAsap() {
			return true;
		}

	}
	
	
	@SuppressWarnings("unchecked")
	public void acaoBotaoExcluir() throws DataAccessException,
			NucleoRegraNegocioExcecao {
		// Exclui os itens selecionados
		Set<Listitem> itensSelecionados = listagemPaginada.getSelecionados();
		Iterator<Listitem> itItensSelecionados = itensSelecionados.iterator();
		while (itItensSelecionados.hasNext()) {
			Listitem itemSelecionado = itItensSelecionados.next();
			T objeto = (T) itemSelecionado.getValue();
			nucleoAplCadastroBase.excluir(objeto);
		}
	}


	public abstract T criarNovoObjetoDados();
	public abstract ListagemPaginada<T> definirListagem();
	public abstract Component  definirFormularioCadastro();
	
		

}
