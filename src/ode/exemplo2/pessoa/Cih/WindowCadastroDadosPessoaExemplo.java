package ode.exemplo2.pessoa.Cih;

import java.util.ArrayList;
import java.util.List;

import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.componentes.NucleoTab;
import nucleo.comuns.visao.old.NucleoWindowCadastroDados;

import ode.exemplo2.pessoa.Cdp.PessoaExemplo;
import ode.exemplo2.pessoa.Cgt.AplCadastrarPessoaExemplo;

import org.springframework.dao.DataAccessException;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;


public class WindowCadastroDadosPessoaExemplo extends NucleoWindowCadastroDados<PessoaExemplo> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WindowCadastroDadosPessoaExemplo() {
		this.setNucleoAplCadastroBase((AplCadastrarPessoaExemplo) SpringUtil
				.getBean("aplCadastrarPessoaExemplo"));
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
		lbSobrenome.setParent(row);
		tbSobrenome.setWidth("150px");
		tbSobrenome.setMaxlength(10);
		tbSobrenome.setParent(row);

		row = new Row();
		row.setParent(rows);
		lbTelefone.setParent(row);
		tbTelefone.setWidth("150px");
		tbTelefone.setMaxlength(50);
		tbTelefone.setParent(row);
		
		row = new Row();
		row.setParent(rows);
		lbIdade.setParent(row);
		tbIdade.setWidth("150px");
		tbIdade.setMaxlength(50);
		tbIdade.setParent(row);

		row = new Row();
		row.setParent(rows);
		lbEmail.setParent(row);
		tbEmail.setWidth("150px");
		tbEmail.setMaxlength(50);
		tbEmail.setParent(row);;

		tabDadosCadastro.setConteudoTab(gridDadosCadastro);

		listaTabs.add(tabDadosCadastro);

		return listaTabs;
	}

	@Override
	protected void preencherDadosObjeto() {
		PessoaExemplo pessoa = this.getObjetoCadastroDados();
		pessoa.setNome(tbNome.getValue());
		pessoa.setSobrenome(tbSobrenome.getValue());
		pessoa.setTelefone(tbTelefone.getValue());
		pessoa.setIdade(Integer.parseInt(tbIdade.getValue()));
		pessoa.setEmail(tbEmail.getValue());
	}

	@Override
	protected void preencherDadosTela() throws NucleoRegraNegocioExcecao {
		PessoaExemplo pessoa = this.getObjetoCadastroDados();
		tbNome.setValue(pessoa.getNome());
		tbSobrenome.setValue(pessoa.getSobrenome());
		tbTelefone.setValue(pessoa.getTelefone());
		tbIdade.setValue(String.valueOf(pessoa.getIdade()));
		tbEmail.setValue(pessoa.getEmail());
		tbNome.setConstraint("no empty");
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");
		tbSobrenome.setConstraint("no empty");
		tbTelefone.setConstraint("no empty");
		tbIdade.setConstraint("no negative, no zero");
		tbEmail.setConstraint("no empty");
		
	}

	@Override
	protected String getTituloWindow() {
		return NucleoMensagens.getMensagem(NucleoMensagens.TERMO_PESSOA);
	}
	
	private Label lbNome = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_NOME)
			+ ": ");

	private Textbox tbNome = new Textbox();
	
	private Label lbSobrenome = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_SOBRENOME)
			+ ": ");

	private Textbox tbSobrenome = new Textbox();

	private Label lbTelefone = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_TELEFONE)
			+ ": ");

	private Textbox tbTelefone = new Textbox();
	
	private Label lbIdade = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_IDADE)
			+ ": ");

	private Textbox tbIdade = new Textbox();

	private Label lbEmail = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_EMAIL)
			+ ": ");
	
	private Textbox tbEmail = new Textbox();

}
