package nucleo.comuns.visao.paginacao;

import java.util.Collection;
import java.util.Comparator;

import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.persistencia.NucleoObjetoPersistenteImpl;
import nucleo.comuns.persistencia.ObjetoPagina;
import nucleo.comuns.visao.NucleoWindowCadastroLista;

import org.hibernate.criterion.Order;
import org.springframework.dao.DataAccessException;
import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.FieldComparator;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelExt;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.api.Paging;
import org.zkoss.zul.event.ListDataListener;
import org.zkoss.zul.event.PagingEvent;

public abstract class WindowCadastroListaPaginada<T extends NucleoObjetoPersistenteImpl<Long, Long>>
		extends NucleoWindowCadastroLista<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5550963059962932422L;

	ObjetoPagina pagina;
	
	final int PAGE_SIZE = 20;	

	public void afterCompose() {

	}

	protected void adicinarComponentesExtensao() {

		Paging pag = new org.zkoss.zul.Paging();

		pag.setTotalSize(100);
		pag.setDetailed(true);
	//	pag.setMold("os");
		
		//cria o objeto pagina
		pagina = ObjetoPagina.factory(0, PAGE_SIZE,null);

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
		pag.setParent(vboxPainel);

	}

	private void atualizarPesquisa() {
	
		Collection<T> objetos = null;
		try {
			objetos = getNucleoAplCadastroBase().recuperarTodosPaginado(pagina);
		} catch (NucleoRegraNegocioExcecao e) {

			e.printStackTrace();
		}
		setObjetos(objetos);
		preencherLista();

	}
	
	protected void configurarTabela() {

		// Configura cabeçalho do listbox
		String[] titulosCabecalho = this.definirTitulosCabecalho();
		String[] tamanhosCabecalho = this.definirTamanhosCabecalho();
		String[] atributosOrdenacao = this.definirAtributoOrdenacao();
		
		for (int i = 0; i < titulosCabecalho.length; i++) {
			NucleoListHeader listHeader = new NucleoListHeader(titulosCabecalho[i]);
			listHeader.setParent(listhead);
			ativarOrdenacaoListHeader(listHeader);
			listHeader.setWidth(tamanhosCabecalho[i]);
			listHeader.setAtributoBanco(atributosOrdenacao[i]);	
			Comparator comp = new Comparator(){

				public int compare(Object arg0, Object arg1) {					
					return 0;
				}};
			listHeader.setSortAscending(comp);			
			listHeader.setSortDescending(comp);

			
			listheaders.add(listHeader);
						
		}
		listhead.setSizable(true);
		
	}
	
	protected abstract String[] definirAtributoOrdenacao();
	
	

	protected Collection<T> recuperarObjetos() throws DataAccessException,
			NucleoRegraNegocioExcecao {

		Collection<T> objetos = getNucleoAplCadastroBase()
				.recuperarTodosPaginado(pagina);
		return objetos;
	}

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
	
	
	public final class OnSortEventListener implements EventListener {

		public void onEvent(Event event) throws Exception {
		
			final NucleoListHeader lh = (NucleoListHeader) event.getTarget();
			final String sortDirection = lh.getSortDirection();			
		
			
			String atributoOrdenacao = lh.getAtributoBanco();
			Order criterioOrdenacao = null;
			
			if ("ascending".equals(sortDirection)) {

				criterioOrdenacao = Order.desc(atributoOrdenacao);
				
			} else if ("descending".equals(sortDirection) || "natural".equals(sortDirection) ) {

				criterioOrdenacao = Order.asc(atributoOrdenacao);
				
			} else if (
					Strings.isBlank(sortDirection)) {
				criterioOrdenacao = null;
			}			
			pagina.setCriterioOrdenacao(criterioOrdenacao);
			
			atualizarPesquisa();

		}
	}

}
