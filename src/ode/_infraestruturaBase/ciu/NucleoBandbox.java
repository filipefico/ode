package ode._infraestruturaBase.ciu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import ode._infraestruturaBase.cdp.ObjetoPersistente;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Bandpopup;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;

/**
 * BandBox funciona como um combo box
 * */

public abstract class NucleoBandbox<T extends ObjetoPersistente> extends Bandbox {

	private static final long serialVersionUID = 1740909544218270482L;

	public NucleoBandbox() {
		super();

		this.titulosCabecalho = this.definirTitulosCabecalho();
		this.tamanhosCabecalho = this.definirTamanhosCabecalho();
		this.iniciarComponentesInterface();
	}

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
	protected Map<String, Object> parametros;

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
	 * Recupera as informações de um objeto a serem exibidos na lista do
	 * cadastro.
	 * 
	 * @param objeto
	 *            Objeto cujas informações serão exibidas na lista do cadastro.
	 * @return Vetor com as informações a serem exibidas.
	 */
	protected abstract String[] recuperarDadosObjeto(T objeto);

	/**
	 * Objeto selecionado.
	 */
	private T objetoSelecionado;

	public T getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(T objetoSelecionado) {
		this.setValue(this.recuperarLabelObjetoSelecionado(objetoSelecionado));
		this.objetoSelecionado = objetoSelecionado;
	}

	private Collection<T> objetos;

	/**
	 * Recupera o label a ser exibido do objeto dentro do bandbox.
	 * 
	 * @param objeto
	 *            Objeto contido dentro do bandbox.
	 * @return Nome do label.
	 */
	public abstract String recuperarLabelObjetoSelecionado(T objeto);

	/**
	 * Inicia componentes gráficos.
	 */
	private void iniciarComponentesInterface() {

		this.setWidth("194px");
		this.setAutodrop(true);

		// instancia componentes de interface
		listBox = new Listbox();
		listhead = new Listhead();
		listheaders = new ArrayList<Listheader>();
		bandpopup = new Bandpopup();

		// configura componentes
		listBox.setWidth(WIDTH_LISTBOX);
		listBox.setRows(8);
		listBox.setMultiple(true);
		bandpopup.setWidth(WIDTH_BANDPOPUP);
		configurarCabecalhoLista();

		// agrupa componentes
		bandpopup.setParent(this);
		listBox.setParent(bandpopup);
		listhead.setParent(listBox);

		this.addEventListener("onChanging", new EventListenerChange());

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

	public void setObjetos(Collection<T> objetos) {
		this.objetos = objetos;
		preencherLista(objetos);
	}

	private void filtrarLista(String str) {
		List<T> objetosComFiltro = new ArrayList<T>();
		for (T objeto : this.objetos) {
			if (recuperarLabelObjetoSelecionado(objeto).matches("(?i:.*" + str + ".*)"))
				objetosComFiltro.add(objeto);
		}
		preencherLista(objetosComFiltro);
	}

	/**
	 * Preenche a lista na tela com a lista de objetos.
	 */
	private void preencherLista(Collection<T> objetos) {
		// Excluir itens da lista
		listBox.getItems().clear();

		for (T obj : objetos) {
			// Associa um listitem
			Listitem lt = new Listitem();
			lt.addEventListener("onClick", new EventListenerItemLista());
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

	private class EventListenerChange implements EventListener {

		public void onEvent(Event event1) {
			InputEvent event = (InputEvent) event1;
			if (event.getValue() != event.getPreviousValue())
				filtrarLista(event.getValue());
		}
	}

	/**
	 * Ação do evento Item da lista.
	 * 
	 */
	@SuppressWarnings("unchecked")
	protected void acaoAbrirItemSelecionado() {
		this.setObjetoSelecionado((T) listBox.getSelectedItem().getValue());
		this.setValue(this.getObjetoSelecionado().toString());
		this.close();
	}

	/** Classe do evento de um item da lista. */
	private class EventListenerItemLista implements EventListener {
		public void onEvent(Event event) {
			acaoAbrirItemSelecionado();
		}

	}

	/** Tamanho do listbox. */
	public static final String WIDTH_LISTBOX = "194px";

	/** Tamanho do listbox. */
	public static final String WIDTH_BANDPOPUP = "194px";

	/** Bandpopup. */
	private Bandpopup bandpopup;

	/** Lista dos objetos. */
	protected Listbox listBox;

	/** Grupo de cabecalhos. */
	private Listhead listhead;

	/** Lista de cabecalhos. */
	private List<Listheader> listheaders;

}
