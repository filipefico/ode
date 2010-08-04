package nucleo.comuns.autenticacao.visao;

import java.util.ArrayList;
import java.util.List;

import nucleo.comuns.autenticacao.acegi.aplicacao.AplCadastrarNucleoPessoa;
import nucleo.comuns.autenticacao.acegi.dominio.NucleoPessoa;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.componentes.NucleoTab;
import nucleo.comuns.visao.old.NucleoWindowCadastroDados;

import org.springframework.dao.DataAccessException;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;


public class WindowCadastroDadosNucleoPessoa extends NucleoWindowCadastroDados<NucleoPessoa> {

	private static final long serialVersionUID = 1410190504248546207L;

	public WindowCadastroDadosNucleoPessoa() {
		this.setNucleoAplCadastroBase((AplCadastrarNucleoPessoa) SpringUtil
				.getBean("aplCadastrarNucleoPessoa"));
	}

	@Override
	protected String getTituloWindow() {
		return NucleoMensagens.getMensagem(NucleoMensagens.TERMO_PESSOA);
	}
	
	@Override
	protected void configurarComponentesExtensao() {
		this.setWidth("350px");
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
		lbRamal.setParent(row);
		tbRamal.setWidth("150px");
		tbRamal.setMaxlength(10);
		tbRamal.setParent(row);

		row = new Row();
		row.setParent(rows);
		lbTelefone.setParent(row);
		tbTelefone.setWidth("150px");
		tbTelefone.setMaxlength(50);
		tbTelefone.setParent(row);
		
		row = new Row();
		row.setParent(rows);
		lbCelular.setParent(row);
		tbCelular.setWidth("150px");
		tbCelular.setMaxlength(50);
		tbCelular.setParent(row);

		row = new Row();
		row.setParent(rows);
		lbEmail.setParent(row);
		tbEmail.setWidth("150px");
		tbEmail.setMaxlength(50);
		tbEmail.setParent(row);

		row = new Row();
		row.setParent(rows);
		lbFuncao.setParent(row);
		tbFuncao.setWidth("150px");
		tbFuncao.setMaxlength(200);
		tbFuncao.setParent(row);

		tabDadosCadastro.setConteudoTab(gridDadosCadastro);

		listaTabs.add(tabDadosCadastro);

		return listaTabs;
	}

	@Override
	protected void preencherDadosTela() {
		NucleoPessoa pessoa = this.getObjetoCadastroDados();
		tbNome.setValue(pessoa.getNome());
		tbRamal.setValue(pessoa.getRamal());
		tbTelefone.setValue(pessoa.getTelefone());
		tbCelular.setValue(pessoa.getCelular());
		tbEmail.setValue(pessoa.getEmail());
		tbFuncao.setValue(pessoa.getFuncao());
	}

	@Override
	protected void preencherDadosObjeto() {
		NucleoPessoa pessoa = this.getObjetoCadastroDados();
		pessoa.setNome(tbNome.getValue());
		pessoa.setRamal(tbRamal.getValue());
		pessoa.setTelefone(tbTelefone.getValue());
		pessoa.setCelular(tbCelular.getValue());
		pessoa.setEmail(tbEmail.getValue());
		pessoa.setFuncao(tbFuncao.getValue());
	}

	private Label lbNome = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_NOME)
			+ ": ");

	private Textbox tbNome = new Textbox();
	
	private Label lbRamal = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_RAMAL)
			+ ": ");

	private Textbox tbRamal = new Textbox();

	private Label lbTelefone = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_TELEFONE)
			+ ": ");

	private Textbox tbTelefone = new Textbox();
	
	private Label lbCelular = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_CELULAR)
			+ ": ");

	private Textbox tbCelular = new Textbox();

	private Label lbEmail = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_EMAIL)
			+ ": ");
	
	private Textbox tbEmail = new Textbox();

	private Label lbFuncao = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_FUNCAO)
			+ ": ");

	private Textbox tbFuncao = new Textbox();

}
