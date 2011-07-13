package nucleo.comuns.visao.listagem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import nucleo.comuns.excecao.CtrlExcecoes;
import nucleo.comuns.visao.paginacao.NucleoListHeader;

import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vlayout;

public abstract class ListagemSimples<T extends Object> extends Vlayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4501107226775255414L;



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

	/** Filtro utilizado na listagem de elementos. */
	protected Hashtable<String, String> filtro;

	/**
	 * Interface resposavel por atualizar Pesquisa Paginada. Nos Cruds, o
	 * Controlador que faz esse papel
	 */
	protected IAtualizaPesquisa atualizador;

	// Objetos da listagem
	private Collection<T> objetos;

	public ListagemSimples() {
	}

	/**
	 * Inicia componentes gráficos.
	 */
	public void configurarComponentes() {

		definirComponentes();
		validarComponentes();

		configurarColunasTabela();

		listBox.setHeight("350px");
		listBox.setCheckmark(true);
		listBox.setMultiple(true);
		listBox.setMold("paging");
		
		// ////////////////////////////////////
		// Define configurações extras
		// ////////////////////////////////////
		
		configurarComponentesExtensao();

		montar();
		montarComponentesExtensao();

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
		// Lista de elementos
		// ////////////////////////////////////
		listhead.setParent(listBox);
		listBox.setParent(this);

	//	paging.setParent(this);
	}

	/**
	 * Adiciona novos componentes à barra de ferramentas
	 */
	protected void adicionarComponentesExtensaoBarraFerramentas(Toolbar toolbar) {
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
	public Set<Listitem> getSelecionados() {
		return listBox.getSelectedItems();
	}

	public T getSelecionado() {
		T selecionado = null;

		if (listBox.getSelectedItem() != null)
			selecionado = (T) listBox.getSelectedItem().getValue();

		return selecionado;
	}

	public Collection<T> getObjetos() {
		return objetos;
	}

	public void setObjetos(Collection<T> objetos) {
		this.objetos = objetos;
	}

	public IAtualizaPesquisa getAtualizador() {
		return atualizador;
	}

	public void setAtualizador(IAtualizaPesquisa atualizador) {
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

	
	public void atualizar(Collection<T> objetos){
		
		setObjetos(objetos);
		preencherLista();
	}

}
