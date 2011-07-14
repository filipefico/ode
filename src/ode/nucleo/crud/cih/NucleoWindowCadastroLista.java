package ode.nucleo.crud.cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import ode.nucleo.cgd.NucleoObjetoPersistenteImpl;
import ode.nucleo.cgt.NucleoAplCadastroBase;
import ode.nucleo.cih.NucleoWindow;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;
import ode.nucleo.util.NucleoMensagens;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;


/**
 * Classe abstrata que representa a base para uma janela de cadastro com a lista
 * de objetos.
 * 
 * @author Alexandre G. N. Coelho
 * @param <T>
 *            Classe dos objetos que ser�o exibidos na lista.
 */
public abstract class NucleoWindowCadastroLista<T extends NucleoObjetoPersistenteImpl<Long, Long>>
		extends NucleoWindow {

	/**
	 * Interface de aplica��o usada para cadastros b�sicos (CRUD)
	 */
	private NucleoAplCadastroBase<T> nucleoAplCadastroBase;

	public void setNucleoAplCadastroBase(
			NucleoAplCadastroBase<T> nucleoAplCadastroBase) {
		this.nucleoAplCadastroBase = nucleoAplCadastroBase;
	}

	public NucleoAplCadastroBase<T> getNucleoAplCadastroBase() {
		return nucleoAplCadastroBase;
	}

	/**
	 * Lista de objetos recuperados do Banco de Dados para a janela de lista.
	 * Nem todos os objetos ser�o exibidos na janela, pois cada um dever� passar
	 * por um potencial crit�rio de filtro.
	 */
	private Collection<T> objetos;

	public Collection<T> getObjetos() {
		return objetos;
	}

	public void setObjetos(Collection<T> objetos) {
		this.objetos = objetos;
	}

	/**
	 * Define os t�tulos dos campos do cabe�alho da lista.
	 * 
	 * @return Vetor com os t�tulos do cabe�alho.
	 */
	protected abstract String[] definirTitulosCabecalho();

	/**
	 * Define os tamanhos dos campos do cabe�alho da lista.
	 * 
	 * @return Vetor com os tamanhos dos campos do cabe�alho.
	 */
	protected abstract String[] definirTamanhosCabecalho();

	/**
	 * Define o nome da window de cadastro de dados relacionada a esta window de
	 * cadastro de lista.
	 * 
	 * @return Nome da window de cadastro de dados relacionada.
	 */
	protected abstract String definirNomeWindowCadastroDados();

	@Override
	public void onCreateWindow() {
		// Realiza a configura��o inicial da janela
		this.iniciarComponentesInterface();

		// Preenche a lista
		this.atualizarLista();
	}

	/**
	 * Inicia componentes gr�ficos.
	 */
	private void iniciarComponentesInterface() {
		// Configura os componentes
		this.configurarComponentes();

		// Adiciona os componentes
		this.adicionarComponentes();
	}

	/**
	 * Define a configura��o dos componentes da interface no que diz respeito a
	 * dimens�es, imagem utilizada, tooltip, eventos, etc.
	 */
	
	protected void configurarBarraSuperior(){
		//toolbar.setHeight("100%");
		//toolbar.setWidth("450px");
		 
		tbbtNovo.setImage("/imagens/filenew.png");
		tbbtAbrir.setImage("/imagens/fileopen.png");
		tbbtExcluir.setImage("/imagens/editdelete.png");
		tbbtNovo.setTooltiptext(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_NOVO));
		tbbtAbrir.setTooltiptext(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_ABRIR));
		tbbtExcluir.setTooltiptext(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_EXCLUIR));
		

		// ////////////////////////////////////
		// Define as a��es de cada bot�o
		// ////////////////////////////////////
		tbbtNovo.addEventListener("onClick", new EventListenerNovo());
		tbbtAbrir.addEventListener("onClick", new EventListenerAbrir());
		tbbtExcluir.addEventListener("onClick", new EventListenerExcluir());


		
		
	}
	private void configurarComponentes() {

		// ////////////////////////////////////
		// Configura��o da barra de ferramentas
		// ////////////////////////////////////
		configurarBarraSuperior();
		
		configurarTabela();
		
		
		// ////////////////////////////////////
		// Configura��o da lista de elementos
		// ////////////////////////////////////

		// Configura atributos do listbox
		//listBox.setWidth("100%");
		listBox.setRows(8);
		listBox.setCheckmark(true);
		listBox.setMultiple(true);

		// ////////////////////////////////////
		// Configura��o do filtro
		// ////////////////////////////////////
		captionFiltro.setImage("/imagens/viewmag.png");
		// groupbox.setWidth(WIDTH_GROUPBOX);
		// groupbox.setMold("3d");

		// ////////////////////////////////////
		// Define configura��es extras
		// ////////////////////////////////////
		configurarComponentesExtensao();
	}

	protected void configurarTabela() {

		// Configura cabe�alho do listbox
		String[] titulosCabecalho = this.definirTitulosCabecalho();
		String[] tamanhosCabecalho = this.definirTamanhosCabecalho();
		for (int i = 0; i < titulosCabecalho.length; i++) {
			Listheader listHeader = new Listheader(titulosCabecalho[i]);
			listHeader.setParent(listhead);
			ativarOrdenacaoListHeader(listHeader);
			listHeader.setWidth(tamanhosCabecalho[i]);
			
			
			listheaders.add(listHeader);
						
		}
		listhead.setSizable(true);
		
	}

	/**
	 * Ativa a ordena��o da coluna do listheader.
	 * 
	 * @param listheader
	 *            Listheader o qual ser� ativado a ordena��o.
	 */
	protected void ativarOrdenacaoListHeader(Listheader listheader) {
		
	//	listheader.addEventListener("onSort", new OnSortEventListener());

		listheader.setSort("auto");
		
	}
	


	/**
	 * Define configura��es extras (inclusive reconfigura��es) para os
	 * componentes da interface no que diz respeito a dimens�es, imagem
	 * utilizada, tooltip, eventos, etc. Esse m�todo deve ser sobrescrito por
	 * classes que herdam de NucleoWindowCadastroLista e necessitem de realizar
	 * configura��es extras sobre componentes j� existentes ou configurar novos
	 * componentes
	 */
	protected void configurarComponentesExtensao() {
	}

	/**
	 * Adiciona os componentes, posicionando-os na janela.
	 */
	protected void adicionarComponentes() {

		// ////////////////////////////////////
		// Barra de ferramentas
		// ////////////////////////////////////
		vboxPainel.setParent(this);
		vboxPainel.setWidth("98%");
		toolbar.setParent(vboxPainel);
		tbbtNovo.setParent(toolbar);
		tbbtAbrir.setParent(toolbar);
		tbbtExcluir.setParent(toolbar);

		// Adiciona componentes � barra de ferramentas
		adicionarComponentesExtensaoBarraFerramentas(toolbar);

		// ////////////////////////////////////
		// Filtro da consulta
		// ////////////////////////////////////
		if (possuiFiltro()) {
			groupboxFiltro.setParent(vboxPainel);
			adicionarComponentesExtensaoFiltro(groupboxFiltro);
			captionFiltro.setParent(groupboxFiltro);
		} else {
			// Se n�o possui filtro, coloca um separador por motivos decorativos
			(new Separator()).setParent(vboxPainel);
		}

		// ////////////////////////////////////
		// Lista de elementos
		// ////////////////////////////////////
		listBox.setParent(vboxPainel);
		listhead.setParent(listBox);
		
		adicinarComponentesExtensao();
	}

	protected void adicinarComponentesExtensao() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Adiciona novos componentes � barra de ferramentas
	 */
	protected void adicionarComponentesExtensaoBarraFerramentas(Toolbar toolbar) {
	}

	/**
	 * Adiciona componentes ao filtro
	 */
	protected void adicionarComponentesExtensaoFiltro(Groupbox groupbox) {
	
	}

	/**
	 * Indica se a janela possui caixa de filtro de elementos.
	 * <p>
	 * Se esse m�todo for sobrescrito, o m�todo passouFiltro deve ser
	 * sobrescrito para testar objetos quanto ao atendimento aos crit�rios do
	 * filtro.
	 * 
	 * @return Verdadeiro caso a janela possua caixa de filtro e falso caso
	 *         contr�rio.
	 */
	protected boolean possuiFiltro() {
		return false;
	}

	/**
	 * Recupera os objetos referentes � lista da janela a partir do Banco de
	 * Dados e exibe todos os que passarem no potencial crit�rio de filtro.
	 */
	public void atualizarLista() {
		System.out.println("atualizarlista");
		try {
			// Obt�m os objetos
			this.objetos = recuperarObjetos();

			// Preenche a lista com os objetos que passarem no potencial
			// crit�rio de filtro
			preencherLista();

		} catch (Exception e) {
			exibirJanelaErro(e);
		}
	}

	/**
	 * Recupera, a partir da aplica��o, os objetos referentes � janela de lista
	 * em quest�o. Nem todos os objetos recuperados ser�o sempre exibidos, pois
	 * dever�o passar por um potencial crit�rio de filtro antes da exibi��o na
	 * lista.
	 * 
	 * @return Os objetos referentes � janela de lista.
	 */
	protected Collection<T> recuperarObjetos() throws DataAccessException,
			NucleoRegraNegocioExcecao {

		// Retorna todos os objetos					
		Collection<T> objetos = nucleoAplCadastroBase.recuperarTodos();
		return objetos;
	}

	/**
	 * Preenche a lista na tela com os objetos recuperados do Banco de Dados de
	 * acordo com os crit�rios do filtro.
	 */
	protected void preencherLista() {

		try {
			// Recupera os objetos do Banco de Dados se eles n�o foram ainda
			// recuperados
			if (this.objetos == null) {
				this.objetos = recuperarObjetos();
			}

			// Excluir itens da lista
			listBox.getItems().clear();

			// Preenche a lista
			if (possuiFiltro()) {
				for (T obj : this.objetos) {
					if (passouFiltro(obj)) {
						insereItemLista(obj, listBox);
					}
				}
			} else {
				for (T obj : this.objetos) {
					insereItemLista(obj, listBox);
				}
			}

		} catch (Exception e) {
			exibirJanelaErro(e);
		}

	}

	/**
	 * Testa se um objeto passa no filtro para exibi��o na tela. Esse m�todo �
	 * chamado a partir do m�todo que preenche a lista de objetos e deve ser
	 * sobrescrito caso a janela possua filtro.
	 * <p>
	 * Se esse m�todo for sobrescrito, o m�todo possuiFiltro deve ser
	 * sobrescrito para retornar verdadeiro.
	 * 
	 * @param obj
	 *            Objeto a ser testado de acordo com o filtro.
	 * @return Verdadeiro caso o objeto atenda aos crit�rios do filtro e falso
	 *         caso contr�rio.
	 */
	private boolean passouFiltro(T obj) {
		return true;
	}

	/**
	 * Insere uma linha na lista passada com os dados do objeto passado.
	 * 
	 * @param obj
	 *            Objeto a ser inserido na lista.
	 * @param lista
	 *            Lista onde deve ser inserido o objeto.
	 */
	private void insereItemLista(T obj, Listbox lista) {
		// Associa um listitem
		Listitem lt = new Listitem();
		lt.setValue(obj);

		// Recupera os dados do objeto a serem exibidos na lista
		String[] dadosLista = this.recuperarDadosObjeto(obj);

		// Preenche as c�lulas da listitem
		for (int j = 0; j < dadosLista.length; j++) {
			Listcell listCell = new Listcell(dadosLista[j]);
			if (estilos != null && !estilos[j].equals("")) {
				listCell.setStyle(estilos[j]);
			}
			lt.appendChild(listCell);
		}

		// Insere o listitem no listbox
		lista.appendChild(lt);
	}

	/**
	 * Recupera as informa��es de um objeto a serem exibidos na lista do
	 * cadastro.
	 * 
	 * @param objeto
	 *            Objeto cujas informa��es ser�o exibidas na lista do cadastro.
	 * @return Vetor com as informa��es a serem exibidas.
	 */
	protected abstract String[] recuperarDadosObjeto(T objeto);

	/** Classe do evento do botao novo. */
	private class EventListenerNovo implements EventListener {

		public void onEvent(Event event) {
			acaoBotaoNovo();
		}

		public boolean isAsap() {
			return true;
		}

	}

	/** Classe do evento do botao abrir. */
	private class EventListenerAbrir implements EventListener {

		public void onEvent(Event event) {
			// Obt�m os itens selecionados
			Set itensSelecionados = listBox.getSelectedItems();

			// Verifica se o n�mero de itens selecionados � maior que zero.
			if (itensSelecionados.size() == 1) {
				acaoBotaoAbrir();
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
					// TODO: handle exception
				}
			}
		}

		public boolean isAsap() {
			return true;
		}

	}

	/** Classe do evento do botao excluir. */
	private class EventListenerExcluir implements EventListener {

		public void onEvent(Event event) {
			Set itensSelecionados = listBox.getSelectedItems();

			// verifica se o n�mero de itens selecionados � maior que zero.
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

						// Executa a a��o de exclus�o
						acaoBotaoExcluir();

						// Atualiza os objetos da lista
						objetos = recuperarObjetos();
						preencherLista();
					}
				} catch (Exception e) {
					exibirJanelaErro(e);
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

	/**
	 * Exclui os objetos selecionados
	 */
	@SuppressWarnings("unchecked")
	public void acaoBotaoExcluir() throws DataAccessException,
			NucleoRegraNegocioExcecao {
		// Exclui os itens selecionados
		Set<Listitem> itensSelecionados = listBox.getSelectedItems();
		Iterator<Listitem> itItensSelecionados = itensSelecionados.iterator();
		while (itItensSelecionados.hasNext()) {
			Listitem itemSelecionado = itItensSelecionados.next();
			T objeto = (T) itemSelecionado.getValue();
			nucleoAplCadastroBase.excluir(objeto);
		}
	}

	/**
	 * Define a a��o do bot�o novo.
	 */
	protected void acaoBotaoNovo() {
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
	}

	/**
	 * Cria um novo objeto de dados vazio.
	 * 
	 * @return Novo objeto de dados criado
	 */
	protected abstract T criarNovoObjetoDados();

	/**
	 * Define a a��o do bot�o abrir.
	 */
	protected void acaoBotaoAbrir() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("objeto", listBox.getSelectedItem().getValue());
		parametros.put("janelaPai", this);
		NucleoWindowCadastroDados<T> win = (NucleoWindowCadastroDados<T>) Executions
				.createComponents(this.definirNomeWindowCadastroDados(), null,
						parametros);
		try {
			win.doModal();
		} catch (Exception e) {
			System.out.println("Deu errado a abertura da janela.");
		}
	}

	/**
	 * Layout vertical para conter toolbar e hbox.
	 */
	protected Vbox vboxPainel = new Vbox();

	/** Toolbar da window. */
	protected Toolbar toolbar = new Toolbar();

	/** Toolbarbutton novo. */
	protected Toolbarbutton tbbtNovo = new Toolbarbutton();

	/** Toolbarbutton abrir. */
	protected Toolbarbutton tbbtAbrir = new Toolbarbutton();

	/** Toolbarbutton excluir. */
	protected Toolbarbutton tbbtExcluir = new Toolbarbutton();

	/** Lista dos objetos. */
	protected Listbox listBox = new Listbox();

	/**
	 * Lista de estilos a serem aplicados nos valores do objeto demonstrado.
	 */
	protected String[] estilos;

	/** Grupo de cabecalhos. */
	protected Listhead listhead = new Listhead();

	/** Lista de cabecalhos. */
	protected List<Listheader> listheaders = new ArrayList<Listheader>();

	/** Filtro. */
	protected Groupbox groupboxFiltro = new Groupbox();

	/** Caption do filtro. */
	protected Caption captionFiltro = new Caption(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_FILTRO));

	/** Filtro utilizado na listagem de elementos. */
	protected Hashtable<String, String> filtro;

}
