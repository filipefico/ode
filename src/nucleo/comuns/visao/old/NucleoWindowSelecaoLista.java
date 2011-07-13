package nucleo.comuns.visao.old;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import ode.nucleo.cgd.NucleoObjetoPersistenteImpl;
import ode.nucleo.cih.NucleoWindow;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;



public abstract class NucleoWindowSelecaoLista<T extends NucleoObjetoPersistenteImpl<Long, Long>> extends NucleoWindow {

	/**
	 * Títulos do cabeçalho
	 */
	private String[] titulosCabecalho;

	/**
	 * Tamanhos dos campos do cabeçalho
	 */
	private String[] tamanhosCabecalho;
	
	/**
	 * Parâmetros.
	 */
	protected Map<String,Object> parametros;
	
	/**
	 * Define os títulos dos campos do cabeçalho da lista.
	 * 
	 * @return Vetor com os títulos do cabeçalho.
	 */
	protected abstract String[] definirTitulosCabecalho();

	/**
	 * Define os tamanhos dos campos do cabeçalho da lista.
	 * 
	 * @return Vetor com os tamanhos dos campos do cabeçalho.
	 */
	protected abstract String[] definirTamanhosCabecalho();
	
	/**
	 * Método pode ser sobrescrito caso seja necessário
	 * realizar algum evento ao dar um duplo clique em um
	 * item da lista. 
	 *
	 */
	protected abstract void acaoAbrirItemSelecionado();


	/**
	 * Recupera as informações de um objeto a serem exibidos na lista do
	 * cadastro.
	 * 
	 * @param objeto
	 *            Objeto cujas informações serão exibidas na lista do cadastro.
	 * @return Vetor com as informações a serem exibidas.
	 */
	protected abstract String[] recuperarDadosObjeto(T objeto);

	public NucleoWindowSelecaoLista() {
		super();

		this.titulosCabecalho = this.definirTitulosCabecalho();
		this.tamanhosCabecalho = this.definirTamanhosCabecalho();
		this.iniciarComponentesInterface();
	}

	/**
	 * Inicia componentes gráficos.
	 */
	private void iniciarComponentesInterface() {

		// instancia componentes de interface
		listBox = new Listbox();
		listhead = new Listhead();
		listheaders = new ArrayList<Listheader>();

		// configura componentes
		listBox.setWidth(WIDTH_LISTBOX);
		listBox.setRows(8);
		listBox.setMultiple(true);
		configurarCabecalhoLista();
		
		// agrupa componentes
		listBox.setParent(this);
		listhead.setParent(listBox);
		
	}

	/**
	 * Configura o cabeçalho da lista com os títulos e os tamanhos de cada
	 * campo.
	 */
	private void configurarCabecalhoLista() {
		// Configura o cabeçalho da lista
		for (int i = 0; i < titulosCabecalho.length; i++) {
			Listheader listHeader = new Listheader(titulosCabecalho[i]);
			listHeader.setParent(listhead);
			listHeader.setSort("auto");
			listHeader.setWidth(tamanhosCabecalho[i]);
			listheaders.add(listHeader);
		}
	}
	
	/**
	 * Preenche a lista na tela com a lista de objetos.
	 */
	public void preencherLista(Collection<T> objetos) {

		// Excluir itens da lista
		listBox.getItems().clear();

		// Preenche a lista
		Iterator<T> itObjetos = objetos.iterator();
		while (itObjetos.hasNext()) {
			// Recupera o objeto da lista
			T obj = itObjetos.next();

			// Associa um listitem
			Listitem lt = new Listitem();
			lt.addEventListener("onDoubleClick", new EventListenerItemLista());
			lt.setValue(obj);

			// Recupera os dados do objeto a serem exibidos na lista
			String[] dadosLista = this.recuperarDadosObjeto(obj);

			// Preenche as células da listitem
			for (int j = 0; j < dadosLista.length; j++) {
				lt.appendChild(new Listcell(dadosLista[j]));
			}

			// Insere o listitem no listbox
			listBox.appendChild(lt);
		}
	}
	
	/** Classe do evento de um item da lista.  */
	private class EventListenerItemLista implements EventListener {

		public void onEvent(Event event) {
			acaoAbrirItemSelecionado();
		}

		public boolean isAsap() {
			return true;
		}

	}

	/** Tamanho do listbox. */
	public static final String WIDTH_LISTBOX = "400px";

	/** Lista dos objetos. */
	protected Listbox listBox;

	/** Grupo de cabecalhos. */
	private Listhead listhead;

	/** Lista de cabecalhos. */
	private List<Listheader> listheaders;
	
}
