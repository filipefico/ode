package nucleo.comuns.visao.paginacao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import nucleo.comuns.excecao.CtrlExcecoes;
import nucleo.comuns.persistencia.ObjetoPagina;
import nucleo.comuns.persistencia.ResultadoPaginado;
import nucleo.comuns.util.NucleoMensagens;

import org.hibernate.criterion.Order;
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
import org.zkoss.zul.Separator;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.api.Paging;
import org.zkoss.zul.event.PagingEvent;
import org.zkoss.zul.ext.Paginal;

public abstract class ListagemPaginada<T extends Object> extends Vlayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4501107226775255414L;

	// private static final int MAX_RESULS = 0;

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

	/**
	 * Interface resposavel por atualizar Pesquisa Paginada. Nos Cruds, o
	 * Controlador que faz esse papel
	 */
	protected IAtualizadorPesquisaPaginada atualizador;

	protected ObjetoPagina pagina;

	protected Paging paging = new org.zkoss.zul.Paging();

	final int PAGE_SIZE = 20;

	// Objetos da listagem
	private Collection<T> objetos;

	public ListagemPaginada() {
	}

	/**
	 * Inicia componentes gráficos.
	 */
	public void configurarComponentes() {

		definirComponentes();
		validarComponentes();

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
		configurarPaginacao();
		configurarComponentesExtensao();

		montar();
		montarComponentesExtensao();

	}

	private void configurarPaginacao() {

		paging.setDetailed(true);
		// pag.setMold("os");

		// cria o objeto pagina
		pagina = ObjetoPagina.factoryObjetoPagina(0, PAGE_SIZE, null);
		paging.addEventListener("onPaging", new EventListener() {

			public void onEvent(Event event) {
				PagingEvent pe = (PagingEvent) event;
				int pgno = pe.getActivePage();
				int firstResults = pgno * PAGE_SIZE;
				pagina.setFirstResults(firstResults);
				pagina.setPaginaAtual(pgno);
				// Redraw current paging
				atualizador.atualizarPesquisa(pagina);

			}
		});

		listBox.setPaginal(paging);
		listBox.setMold("paging");
	}

	protected void montarComponentesExtensao() {
		// TODO Auto-generated method stub

	}

	private void validarComponentes() {
		if (listheaders == null)
			throw CtrlExcecoes.factoryExcecaoDefinicao(
					"listheaders ou cabeçalhos", this.getClass());

		if (atualizador == null)
			throw CtrlExcecoes.factoryExcecaoDefinicao("atualizador", this
					.getClass());

	}

	private void definirComponentes() {
		listheaders = definirColunasTabela();
	}

	protected void configurarColunasTabela() {

		for (NucleoListHeader header : listheaders) {
			header.setParent(listhead);
			ativarOrdenacaoListHeader(header);
			// tenho que colocar um comparator simples para funcionar
			// NÃO RETIRAR
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

	/**
	 * Define os cabeçalhos e colunas Exemplo:
	 * 
	 * {@code List<NucleoListHeader> colunas = new
	 * ArrayList<NucleoListHeader>(); colunas.add(new
	 * NucleoListHeader("Nome","nome","210px"));
	 * colunas.add(newNucleoListHeader("Idade","idade","210px")); return
	 * colunas; * }
	 * 
	 * @return List<NucleoListHeader> - Lista dos Cabeçalhos
	 * */
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
	protected void montar() {

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
		listhead.setParent(listBox);
		listBox.setParent(this);

		paging.setParent(this);
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
	public void preencherLista() {

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
			atualizador.atualizarPesquisa(pagina);

		}
	}

	public Set<Listitem> getSelecionados() {
		return listBox.getSelectedItems();
	}

	public T getSelecionado() {
		T selecionado = null;

		if (listBox.getSelectedItem() != null)
			selecionado = (T) listBox.getSelectedItem().getValue();

		return selecionado;
	}

	public ObjetoPagina getPagina() {
		return pagina;
	}

	public void setPagina(ObjetoPagina pagina) {
		this.pagina = pagina;
	}

	public Collection<T> getObjetos() {
		return objetos;
	}

	public void setObjetos(Collection<T> objetos) {
		this.objetos = objetos;
	}

	public IAtualizadorPesquisaPaginada getAtualizador() {
		return atualizador;
	}

	public void setAtualizador(IAtualizadorPesquisaPaginada atualizador) {
		this.atualizador = atualizador;
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

	public void setResultadoAtualizarPesquisa(ResultadoPaginado resultado) {

		setObjetos(resultado.getListaObjetos());
		preencherLista();
		Paginal paginal = listBox.getPaginal();
		paginal.setTotalSize(resultado.getTamanhoTotal());
		// paginal.setActivePage(pagina.getPaginaAtual());

	}

}
