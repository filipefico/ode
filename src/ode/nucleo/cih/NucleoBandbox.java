package ode.nucleo.cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ode.nucleo.cgd.ObjetoPersistente;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
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

        public NucleoBandbox(){
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
        
        /**
         * Recupera o label a ser exibido do objeto dentro do bandbox.
         * @param objeto Objeto contido dentro do bandbox.
         * @return Nome do label.
         */
        public abstract String recuperarLabelObjetoSelecionado(T objeto);

        /**
         * Inicia componentes gráficos.
         */
        private void iniciarComponentesInterface() {
        	
        		this.setWidth("200px");

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
        
        /**
         * Ação do evento Item da lista. 
         *
         */
        @SuppressWarnings("unchecked")
        protected void acaoAbrirItemSelecionado(){
                this.setObjetoSelecionado((T)listBox.getSelectedItem().getValue());
                this.setValue(this.getObjetoSelecionado().toString());
                this.close();
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
        public static final String WIDTH_LISTBOX = "600px";
        
        /** Tamanho do listbox. */
        public static final String WIDTH_BANDPOPUP = "300px";


        /** Bandpopup. */
        private Bandpopup bandpopup;
        
        /** Lista dos objetos. */
        protected Listbox listBox;

        /** Grupo de cabecalhos. */
        private Listhead listhead;

        /** Lista de cabecalhos. */
        private List<Listheader> listheaders;
        
}
