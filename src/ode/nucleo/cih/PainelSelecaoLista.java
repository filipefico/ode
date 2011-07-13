package ode.nucleo.cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Vbox;

public abstract class PainelSelecaoLista<T extends Object> extends Vbox {

        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		/**
         * T�tulos do cabe�alho
         */
        private String[] titulosCabecalho;

        /**
         * Tamanhos dos campos do cabe�alho
         */
        private String[] tamanhosCabecalho;

        /**
         * Par�metros.
         */
        protected Map<String, Object> parametros;

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
         * M�todo pode ser sobrescrito caso seja necess�rio realizar algum evento ao
         * dar um duplo clique em um item da lista.
         * 
         * @param objetoSelecionado
         * 
         */
        protected abstract void acaoAbrirItemSelecionado(T objetoSelecionado);

        /**
         * Recupera as informa��es de um objeto a serem exibidos na lista do
         * cadastro.
         * 
         * @param objeto
         *            Objeto cujas informa��es ser�o exibidas na lista do cadastro.
         * @return Vetor com as informa��es a serem exibidas.
         */
        protected abstract String[] recuperarDadosObjeto(T objeto);

        public PainelSelecaoLista() {
                super();
                this.titulosCabecalho = this.definirTitulosCabecalho();
                this.tamanhosCabecalho = this.definirTamanhosCabecalho();
                this.iniciarComponentesInterface();
        }

        /**
         * Inicia componentes gr�ficos.
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
         * Configura o cabe�alho da lista com os t�tulos e os tamanhos de cada
         * campo.
         */
        private void configurarCabecalhoLista() {
                // Configura o cabe�alho da lista
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

                        // Preenche as c�lulas da listitem
                        for (int j = 0; j < dadosLista.length; j++) {
                                lt.appendChild(new Listcell(dadosLista[j]));
                        }

                        // Insere o listitem no listbox
                        listBox.appendChild(lt);
                }
        }

        /** Classe do evento de um item da lista. */
        private class EventListenerItemLista implements EventListener {

                public void onEvent(Event event) {
                        acaoAbrirItemSelecionado(getSelecionado());
                }

                public boolean isAsap() {
                        return true;
                }

        }

        /**
         * Utilizar o getSelecionados
         * */
        @Deprecated
        public Set<Listitem> getItensSelecionados() {
                return listBox.getSelectedItems();
        }

        public Collection<T> getSelecionados() {
                Collection<T> resultado = new ArrayList<T>();
                Set<Listitem> selecionados = listBox.getSelectedItems();

                if (selecionados != null) {
                        for (Listitem listitem : selecionados) {
                                resultado.add((T) listitem.getValue());
                        }
                }
                return resultado;
                
                

        }

        public T getSelecionado() {
                T selecionado = null;

                if (listBox.getSelectedItem() != null)
                        selecionado = (T) listBox.getSelectedItem().getValue();

                return selecionado;
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
