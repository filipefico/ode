package ode.processoPadrao.Cih;

import java.util.ArrayList;
import java.util.List;

import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.componentes.NucleoTab;
import nucleo.comuns.visao.old.NucleoWindowCadastroDados;

import ode.exemplo2.pessoa.Cdp.PessoaExemplo;
import ode.exemplo2.pessoa.Cgt.AplCadastrarPessoaExemplo;
import ode.processoPadrao.Cdp.CompPP;
import ode.processoPadrao.Cgt.AplCadastrarProcessoPadrao;

import org.springframework.dao.DataAccessException;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;


public class WindowCadastroDadosProcessoPadrao extends NucleoWindowCadastroDados<CompPP> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WindowCadastroDadosProcessoPadrao() {
		this.setNucleoAplCadastroBase((AplCadastrarProcessoPadrao) SpringUtil
				.getBean("aplCadastrarProcessoPadrao"));
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
		tbNome.setWidth("150px");
		tbNome.setMaxlength(50);
		tbNome.setParent(row);
		
		row = new Row();
		row.setParent(rows);
		lbDescricao.setParent(row);
		tbDescricao.setWidth("150px");
		tbDescricao.setMaxlength(10);
		tbDescricao.setParent(row);

		tabDadosCadastro.setConteudoTab(gridDadosCadastro);

		listaTabs.add(tabDadosCadastro);

		return listaTabs;
	}

	@Override
	protected void preencherDadosObjeto() {
		CompPP compPP = this.getObjetoCadastroDados();
		compPP.setNome(tbNome.getValue());
		compPP.setDescricao(tbDescricao.getValue());
	}

	@Override
	protected void preencherDadosTela() throws NucleoRegraNegocioExcecao {
		CompPP compPP = this.getObjetoCadastroDados();
		tbNome.setValue(compPP.getNome());
		tbDescricao.setValue(compPP.getDescricao());
		tbNome.setConstraint("no empty");
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");
		tbDescricao.setConstraint("no empty");
		
	}

	@Override
	protected String getTituloWindow() {
		return NucleoMensagens.getMensagem(NucleoMensagens.TERMO_COMPPP);
	}
	
	private Label lbNome = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_NOME)
			+ ": ");

	private Textbox tbNome = new Textbox();
	
	private Label lbDescricao = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_DESCRICAO));
	
	private Textbox tbDescricao = new Textbox();

}
