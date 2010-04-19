package nucleo.comuns.visao.paginacao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import nucleo.comuns.aplicacao.NucleoAplCadastroBase;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.persistencia.ObjetoPagina;
import nucleo.comuns.persistencia.ObjetoPersistente;
import nucleo.comuns.util.NucleoMensagens;

import ode.exemplo.dominio.PessoaExemplo;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.dao.DataAccessException;
import org.zkoss.lang.Strings;
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
import org.zkoss.zul.api.Paging;
import org.zkoss.zul.event.PagingEvent;

public abstract class ListagemPaginada<T extends ObjetoPersistente> extends
		Vbox {

	private static final int MAX_RESULS = 0;

	
	/** Lista dos objetos. */
	protected Listbox listBox = new Listbox();

	/**
	 * Lista de estilos a serem aplicados nos valores do objeto demonstrado.
	 */
	protected String[] estilos;

	/** Grupo de cabecalhos. */
	protected Listhead listhead = new Listhead();

	/** Lista de cabecalhos. */
	protected List<NucleoListHeader> listheaders = new ArrayList<NucleoListHeader>();

	/** Filtro. */
	protected Groupbox groupboxFiltro = new Groupbox();

	/** Caption do filtro. */
	protected Caption captionFiltro = new Caption(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_FILTRO));

	/** Filtro utilizado na listagem de elementos. */
	protected Hashtable<String, String> filtro;

	ObjetoPagina pagina;

	final int PAGE_SIZE = 20;

	/**
	 * Lista de objetos recuperados do Banco de Dados para a janela de lista.
	 * Nem todos os objetos serão exibidos na janela, pois cada um deverá passar
	 * por um potencial critério de filtro.
	 */
	private Collection<T> objetos;

	public Collection<T> getObjetos() {
		return objetos;
	}

	public void setObjetos(Collection<T> objetos) {
		this.objetos = objetos;
	}

	public ListagemPaginada() {

		iniciarComponentesInterface();
	}

	/**
	 * Inicia componentes gráficos.
	 */
	public void iniciarComponentesInterface() {
		// Configura os componentes
		this.configurarComponentes();

		// Adiciona os componentes
		this.adicionarComponentes();

	}

	

	private void configurarComponentes() {

		configurarColunasTabela();

		listBox.setRows(8);
		listBox.setCheckmark(true);
		listBox.setMultiple(true);

		// ////////////////////////////////////
		// Configuração do filtro
		// ////////////////////////////////////
		captionFiltro.setImage("/imagens/viewmag.png");
		// groupbox.setWidth(WIDTH_GROUPBOX);
		// groupbox.setMold("3d");

		// ////////////////////////////////////
		// Define configurações extras
		// ////////////////////////////////////
		configurarComponentesExtensao();
	}

	protected void configurarColunasTabela() {

		listheaders = definirColunasTabela();

		if (listheaders == null) {
			throw new RuntimeException("Não definiu as colunas da tabela");
		}

		for (NucleoListHeader header : listheaders) {
			header.setParent(listhead);
			ativarOrdenacaoListHeader(header);

			Comparator comp = new Comparator() {

				public int compare(Object arg0, Object arg1) {
					return 0;
				}
			};
			header.setSortAscending(comp);
			header.setSortDescending(comp);

		}

		listhead.setSizable(true);

	}

	public abstract List<NucleoListHeader> definirColunasTabela();

	/**
	 * Ativa a ordenação da coluna do listheader.
	 * 
	 * @param listheader
	 *            Listheader o qual será ativado a ordenação.
	 */
	protected void ativarOrdenacaoListHeader(Listheader listheader) {

		listheader.addEventListener("onSort", new OnSortEventListener());
		listheader.setSort("auto");

	}

	/**
	 * Define configurações extras (inclusive reconfigurações) para os
	 * componentes da interface no que diz respeito a dimensões, imagem
	 * utilizada, tooltip, eventos, etc. Esse método deve ser sobrescrito por
	 * classes que herdam de NucleoWindowCadastroLista e necessitem de realizar
	 * configurações extras sobre componentes já existentes ou configurar novos
	 * componentes
	 */
	protected void configurarComponentesExtensao() {
	}

	/**
	 * Adiciona os componentes, posicionando-os na janela.
	 */
	protected void adicionarComponentes() {

		// ////////////////////////////////////
		// Filtro da consulta
		// ////////////////////////////////////
		if (possuiFiltro()) {
			groupboxFiltro.setParent(this);
			adicionarComponentesExtensaoFiltro(groupboxFiltro);
			captionFiltro.setParent(groupboxFiltro);
		} else {
			// Se não possui filtro, coloca um separador por motivos decorativos
			(new Separator()).setParent(this);
		}

		// ////////////////////////////////////
		// Lista de elementos
		// ////////////////////////////////////
		listBox.setParent(this);
		listhead.setParent(listBox);

		adicinarComponentesExtensao();
	}

	protected void adicinarComponentesExtensao() {

		Paging pag = new org.zkoss.zul.Paging();	
		pag.setDetailed(true);
		
		// pag.setMold("os");

		// cria o objeto pagina
		pagina = ObjetoPagina.factory(0, PAGE_SIZE, null);

		pag.addEventListener("onPaging", new EventListener() {

			public void onEvent(Event event) {
				PagingEvent pe = (PagingEvent) event;
				int pgno = pe.getActivePage();
				int firstResults = pgno * PAGE_SIZE;

				pagina.setFirstResults(firstResults);
				// Redraw current paging
				atualizarPesquisa();
			}
		});

		listBox.setPaginal(pag);
		listBox.setMold("paging");
		pag.setParent(this);

	}

	protected void atualizarPesquisa() {
		Collection<T> objetos = null;
		try {
			objetos = getNucleoAplCadastroBase().recuperarTodosPaginado(pagina);	
			
			listBox.getPaginal().setTotalSize(50);
		} catch (NucleoRegraNegocioExcecao e) {

			e.printStackTrace();
		}
		setObjetos(objetos);
		preencherLista();

	}

	/**
	 * Adiciona novos componentes à barra de ferramentas
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
	 * Preenche a lista na tela com os objetos recuperados do Banco de Dados de
	 * acordo com os critérios do filtro.
	 */
	protected void preencherLista() {

		// Excluir itens da lista
		listBox.getItems().clear();

		for (T obj : this.objetos) {

			insereItemLista(obj, listBox);

		}

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

		// Preenche as células da listitem
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
	 * Recupera as informações de um objeto a serem exibidos na lista do
	 * cadastro.
	 * 
	 * @param objeto
	 *            Objeto cujas informações serão exibidas na lista do cadastro.
	 * @return Vetor com as informações a serem exibidas.
	 */
	protected abstract String[] recuperarDadosObjeto(T objeto);

	public final class OnSortEventListener implements EventListener {

		public void onEvent(Event event) throws Exception {

			final NucleoListHeader lh = (NucleoListHeader) event.getTarget();
			final String sortDirection = lh.getSortDirection();

			String atributoOrdenacao = lh.getAtributoBanco();
			Order criterioOrdenacao = null;

			if ("ascending".equals(sortDirection)) {

				criterioOrdenacao = Order.desc(atributoOrdenacao);

			} else if ("descending".equals(sortDirection)
					|| "natural".equals(sortDirection)) {

				criterioOrdenacao = Order.asc(atributoOrdenacao);

			} else if (Strings.isBlank(sortDirection)) {
				criterioOrdenacao = null;
			}
			pagina.setCriterioOrdenacao(criterioOrdenacao);

			atualizarPesquisa();

		}
	}

	/**
	 * Interface de aplicação usada para cadastros básicos (CRUD)
	 */
	private NucleoAplCadastroBase<T> nucleoAplCadastroBase;

	public void setNucleoAplCadastroBase(
			NucleoAplCadastroBase<T> nucleoAplCadastroBase) {
		this.nucleoAplCadastroBase = nucleoAplCadastroBase;
	}

	public NucleoAplCadastroBase<T> getNucleoAplCadastroBase() {
		return nucleoAplCadastroBase;
	}

	public Set<Listitem> getSelecionados() {
		return  listBox.getSelectedItems();
	}
	
	public T getSelecionado() {
		T selecionado =null;
		
		if (listBox.getSelectedItem() != null)
			selecionado=(T)listBox.getSelectedItem().getValue();
				
		return selecionado ;
	}

}
