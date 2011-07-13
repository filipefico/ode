package ode.processoPadrao.Cih;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.NucleoWindowCadastroDados;
import ode.conhecimento.processo.Cdp.KProcesso;
import ode.conhecimento.processo.Cgt.AplCadastrarKProcesso;
import ode.nucleo.cih.NucleoTab;
import ode.processoPadrao.Cdp.CompPP;
import ode.processoPadrao.Cgt.AplDefinirProcessoPadrao;

import org.springframework.dao.DataAccessException;
import org.zkoss.zkplus.spring.SpringUtil;
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

public class WindowNovoProjeto extends NucleoWindowCadastroDados<CompPP>{

private static final long serialVersionUID = 1L;
	
	private AplCadastrarKProcesso aplCadastrarKProcesso;	


	public WindowNovoProjeto() {
		this.setNucleoAplCadastroBase((AplDefinirProcessoPadrao) SpringUtil 
				.getBean("aplDefinirProcessoPadrao"));

		aplCadastrarKProcesso = (AplCadastrarKProcesso) SpringUtil
		.getBean("aplCadastrarKProcesso");
		
	}

	@Override
	protected String getTituloWindow() {
		return NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_NOVO_PROJETO);
	}

	@Override
	protected void configurarComponentesExtensao() {
		this.setWidth("550px");
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
		lbTitulo.setParent(row);
		tbTitulo.setWidth("400px");
		tbTitulo.setMaxlength(255);
		tbTitulo.setParent(row);

		row = new Row();
		row.setParent(rows);
		lbDescricao.setParent(row);
		tbDescricao.setWidth("400px");
		tbDescricao.setRows(5);
		tbDescricao.setMaxlength(255);
		tbDescricao.setParent(row);
		
	/*	row = new Row();
		row.setParent(rows);
		lbObjetivo.setParent(row);
		tbObjetivo.setWidth("200px");
		tbObjetivo.setRows(5);
		tbObjetivo.setMaxlength(255);
		tbObjetivo.setParent(row);*/
		
		row = new Row();
		row.setParent(rows);
		lbNivelGranularidade.setParent(row);
		groupbox.setParent(row);
		checkboxDesenvolvimento.setParent(groupbox);
		checkboxManutencao.setParent(groupbox);
		checkboxDesenvolvimento.setChecked(true);
		
	/*	row = new Row();
		row.setParent(rows);
		lbTipo.setParent(row);
		listBoxTipo.setParent(row);
		listBoxTipo.setRows(5);
		listBoxTipo.setCheckmark(true);
		listBoxTipo.setMultiple(true);
		listhead.setParent(listBoxTipo);

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
		}		*/
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);

		listaTabs.add(tabDadosCadastro);

		return listaTabs;
	}

	@Override
	protected void preencherDadosTela() {
		CompPP objeto = this.getObjetoCadastroDados();
		tbTitulo.setValue(objeto.getNome());
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
		
	}

	@Override
	protected void preencherDadosObjeto() {
		CompPP objeto = this.getObjetoCadastroDados();
		objeto.setNome(tbTitulo.getValue());
		objeto.setDescricao(tbDescricao.getValue());
		//objeto.setPermiteVisualizarReservas(checkboxSim.isChecked());
		
		Set<KProcesso> processos = new HashSet<KProcesso>();
		Set<Listitem> listItems = listBoxTipo.getSelectedItems();
		for (Listitem item : listItems) {
			processos.add((KProcesso) item.getValue());
		}
		
		//objeto.setUsuarios(processos);
	}

	private Label lbTitulo = new Label("Titulo :");

	private Textbox tbTitulo = new Textbox();

	private Label lbDescricao = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_DESCRICAO)
			+ ": ");
	
	private Textbox tbDescricao = new Textbox();
	
	
	private Label lbNivelGranularidade = new Label("Tipo de Projeto");
	
	private Radiogroup groupbox = new Radiogroup();

	private Radio checkboxDesenvolvimento =  new Radio("Desenvolvimento");
	
	private Radio checkboxManutencao =  new Radio("Manutenção");


	/** Lista dos usuarios. */
	protected Listbox listBoxTipo = new Listbox();

	/** Grupo de cabecalhos. */
	protected Listhead listhead = new Listhead();

	/** Lista de cabecalhos. */
	protected List<Listheader> listheaders = new ArrayList<Listheader>();
	
}
