package ode.processoPadrao.Cih;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Iterator;
import java.util.Set;

import nucleo.comuns.crud.visao.GridDados;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.componentes.NucleoTab;
import nucleo.comuns.visao.componentes.selecao.NucleoBandbox;
import nucleo.comuns.visao.old.NucleoWindowCadastroDados;
import ode.conhecimento.processo.Cdp.KProcesso;
import ode.conhecimento.processo.Cgt.AplCadastrarKProcesso;
import ode.exemplo2.pessoa.Cdp.PessoaExemplo;
import ode.processoPadrao.Cdp.CompPP;
import ode.processoPadrao.Cgt.AplDefinirProcessoPadrao;

import org.springframework.dao.DataAccessException;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
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

public class WindowDefinirProcesso extends NucleoWindowCadastroDados<CompPP>{

private static final long serialVersionUID = 1L;
	
	private AplCadastrarKProcesso aplCadastrarKProcesso;	


	public WindowDefinirProcesso() {
		this.setNucleoAplCadastroBase((AplDefinirProcessoPadrao) SpringUtil 
				.getBean("aplDefinirProcessoPadrao"));

		aplCadastrarKProcesso = (AplCadastrarKProcesso) SpringUtil
		.getBean("aplCadastrarKProcesso");
		
	}

	@Override
	protected String getTituloWindow() {
		return NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DEFINIR_PROCESSO);
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
				.getMensagem(NucleoMensagens.TERMO_PROCESSO));

		// Atribui o conteúdo à tab
		Grid gridDadosCadastro = new Grid();

		Rows rows = new Rows();
		rows.setParent(gridDadosCadastro);

		Row row = new Row();
		row.setParent(rows);
		lbRequisitos.setParent(row);
		tbRequisitos.setWidth("400px");
		tbRequisitos.setRows(5);
		tbRequisitos.setMaxlength(255);
		tbRequisitos.setParent(row);

		
		NucleoBandbox<CompPP> band = new NucleoBandbox<CompPP>(){

			@Override
			protected String[] definirTamanhosCabecalho() {

				return new String[]{"240px"};
			}

			@Override
			protected String[] definirTitulosCabecalho() {

				return new String[]{"Nome"};
			}

			@Override
			protected String[] recuperarDadosObjeto(CompPP objeto) {
 
				return new String[]{objeto.getNome()};
			}
			
			
		};
		
		Row row1 = new Row();
		row1.setParent(rows);
		lbBusca.setParent(row1);
		band.setParent(row1);
		band.setWidth("350px");

		//gridDadosCadastro.adicionarLinha("Pai", band);
		//adiciono o grid de dados na tab
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		listaTabs.add(tabDadosCadastro);
		
		
		//tabDadosCadastro.setConteudoTab(gridDadosCadastro);

		//listaTabs.add(tabDadosCadastro);

		return listaTabs;
	}

	@Override
	protected void preencherDadosTela() {
		CompPP objeto = this.getObjetoCadastroDados();
		tbRequisitos.setValue(objeto.getNome());
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
		objeto.setNome(tbRequisitos.getValue());
		
		//objeto.setPermiteVisualizarReservas(checkboxSim.isChecked());
		
		/*Set<KProcesso> processos = new HashSet<KProcesso>();
		Set<Listitem> listItems = listBoxTipo.getSelectedItems();
		for (Listitem item : listItems) {
			processos.add((KProcesso) item.getValue());
		}*/
		
		//objeto.setUsuarios(processos);
	}

	private Label lbRequisitos = new Label("Requisitos:");

	private Textbox tbRequisitos = new Textbox();

	/*private Label lbDescricao = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_DESCRICAO)
			+ ": ");
	
	private Textbox tbDescricao = new Textbox();*/
	
	

	private Label lbBusca = new Label("Buscar Processo Complexo Base:");

	/** Lista dos usuarios. */
	protected Listbox listBoxTipo = new Listbox();

	/** Grupo de cabecalhos. */
	protected Listhead listhead = new Listhead();

	/** Lista de cabecalhos. */
	protected List<Listheader> listheaders = new ArrayList<Listheader>();
	
}
