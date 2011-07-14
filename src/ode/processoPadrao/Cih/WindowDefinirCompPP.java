package ode.processoPadrao.Cih;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ode.conhecimento.processo.Cdp.KProcesso;
import ode.conhecimento.processo.Cgd.KProcessoDAO;
import ode.conhecimento.processo.Cgt.AplCadastrarKProcesso;
import ode.nucleo.cih.NucleoTab;
import ode.nucleo.crud.cih.NucleoWindowCadastroDados;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;
import ode.nucleo.util.NucleoMensagens;
import ode.processoPadrao.Cdp.CompPP;
import ode.processoPadrao.Cgt.AplDefinirProcessoPadrao;

import org.springframework.dao.DataAccessException;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;

public class WindowDefinirCompPP extends NucleoWindowCadastroDados<CompPP>{

private static final long serialVersionUID = 1L;
	
	private AplCadastrarKProcesso aplCadastrarKProcesso;	


	public WindowDefinirCompPP() {
		this.setNucleoAplCadastroBase((AplDefinirProcessoPadrao) SpringUtil 
				.getBean("aplDefinirProcessoPadrao"));

		aplCadastrarKProcesso = (AplCadastrarKProcesso) SpringUtil
		.getBean("aplCadastrarKProcesso");
		
	}

	@Override
	protected String getTituloWindow() {
		return NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_COMPPP);
	}

	@Override
	protected void configurarComponentesExtensao() {
		this.setWidth("550px");
	}

	public static KProcesso buscaCategoriaPorNome (String nome){
		KProcessoDAO Categoria = (KProcessoDAO) SpringUtil.getApplicationContext().getBean(KProcessoDAO.class);
		Collection<KProcesso> listaCategorias = Categoria.recuperarTodos();
		for (KProcesso K : listaCategorias) {
			if (K.getNome().equals(nome)){
				return K;
			}
		}
		return null;
	}
	
	@Override
	protected List<NucleoTab> definirTabs() throws DataAccessException,
			NucleoRegraNegocioExcecao {
		// Cria a nova lista
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();

		// ////////////////////////////
		// Dados Cadastro
		// ////////////////////////////
		NucleoTab tabDadosCadastro = new NucleoTab();

		// Atribui o nome à tab
		tabDadosCadastro.setNomeTab(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));

		// Atribui o conteúdo à tab
		Grid gridDadosCadastro = new Grid();

		Rows rows = new Rows();
		rows.setParent(gridDadosCadastro);

		Row row = new Row();
		row.setParent(rows);
		lbNome.setParent(row);
		tbNome.setWidth("400px");
		tbNome.setMaxlength(255);
		tbNome.setParent(row);

		row = new Row();
		row.setParent(rows);
		lbDescricao.setParent(row);
		tbDescricao.setWidth("400px");
		tbDescricao.setRows(5);
		tbDescricao.setMaxlength(255);
		tbDescricao.setParent(row);
		
		row = new Row();
		row.setParent(rows);
		lbObjetivo.setParent(row);
		tbObjetivo.setWidth("400px");
		tbObjetivo.setRows(5);
		tbObjetivo.setMaxlength(255);
		tbObjetivo.setParent(row);
		
		row = new Row();
		row.setParent(rows);
		lbNivelGranularidade.setParent(row);
		groupbox.setParent(row);
		checkboxProcComp.setParent(groupbox);
		checkboxProcSimp.setParent(groupbox);
		checkboxMacro.setParent(groupbox);
		checkboxProcComp.setChecked(true);
		
		row = new Row();
		row.setParent(rows);
		lbTipo.setParent(row);
		coCategoria.setWidth("350px");
		coCategoria.setParent(row);
		
		KProcessoDAO Categoria = (KProcessoDAO) SpringUtil
			.getBean("kProcessoDao");
			Collection<KProcesso> listaCategorias = Categoria.recuperarTodos();
			for (KProcesso K : listaCategorias) {
				coCategoria.appendItem(K.getNome());
			}
			coCategoria.setAutocomplete(true);
				
		/*row = new Row();
		row.setParent(rows);
		lbTipo.setParent(row);
		listBoxTipo.setParent(row);
		listBoxTipo.setRows(5);
		listBoxTipo.setCheckmark(true);
		listBoxTipo.setMultiple(true);
		listhead.setParent(listBoxTipo);*/

		// Configura cabeçalho do listbox
		Listheader listHeader = new Listheader(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME));
		listHeader.setParent(listhead);
		listHeader.setSort("auto");
		
		// Preenche dados do Listbox
		Collection<KProcesso> processos = aplCadastrarKProcesso.recuperarTodos();
		Iterator<KProcesso> i = processos.iterator();
		while (i.hasNext()){
			KProcesso processo = i.next();
			Listitem listitem = new Listitem(processo.getNome());
			listitem.setValue(processo);
			listBoxTipo.appendChild(listitem);
		}		
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);

		listaTabs.add(tabDadosCadastro);

		return listaTabs;
	}

	@Override
	protected void preencherDadosTela() {
		CompPP objeto = this.getObjetoCadastroDados();
		tbNome.setValue(objeto.getNome());
		tbDescricao.setValue(objeto.getDescricao());
	/*	if (objeto.getPermiteVisualizarReservas()){
			checkboxSim.setChecked(true);
		}*/
	
	/*	List<Listitem> listItems = listBoxTipo.getItems();
		for (Listitem item : listItems) {
		/*	if (objeto.getProcesso().contains((KProcesso)item.getValue())) {
				item.setSelected(true);
			}
		}*/
		
		coCategoria.setValue("Teste");
		
		
	}

	@Override
	protected void preencherDadosObjeto() {
		CompPP objeto = this.getObjetoCadastroDados();
		objeto.setNome(tbNome.getValue());
		objeto.setDescricao(tbDescricao.getValue());
				//objeto.setPermiteVisualizarReservas(checkboxSim.isChecked());
		
		Set<KProcesso> processos = new HashSet<KProcesso>();
		Set<Listitem> listItems = listBoxTipo.getSelectedItems();
		for (Listitem item : listItems) {
			processos.add((KProcesso) item.getValue());
		}
//		//objeto.setUsuarios(processos);
	}

	private Label lbNome = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_NOME)
			+ ": ");

	private Textbox tbNome = new Textbox();

	private Label lbDescricao = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_DESCRICAO)
			+ ": ");
	
	private Textbox tbDescricao = new Textbox();
	
	private Label lbObjetivo = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_OBJETIVO)
			+ ": ");

	private Textbox tbObjetivo = new Textbox();
	
	private Label lbNivelGranularidade = new Label("Nível de Granularidade");
	
	private Radiogroup groupbox = new Radiogroup();

	private Radio checkboxProcComp =  new Radio("Processo Complexo");
	
	private Radio checkboxProcSimp =  new Radio("Processo Simples");
	
	private Radio checkboxMacro =  new Radio("Macroatividade");
	
	private Label lbTipo = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_TIPO)
			+ ": ");
	
	
	
	private Combobox coCategoria = new Combobox();
	
	/** Lista dos usuarios. */
	protected Listbox listBoxTipo = new Listbox();

	/** Grupo de cabecalhos. */
	protected Listhead listhead = new Listhead();

	/** Lista de cabecalhos. */
	protected List<Listheader> listheaders = new ArrayList<Listheader>();
	
}
