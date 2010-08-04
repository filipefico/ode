package nucleo.comuns.autenticacao.visao;

import java.util.ArrayList;
import java.util.List;

import nucleo.comuns.autenticacao.acegi.aplicacao.NucleoAplCadastrarNucleoUserDetails;
import nucleo.comuns.autenticacao.acegi.aplicacao.AplCadastrarNucleoPessoa;
import nucleo.comuns.autenticacao.acegi.dominio.NucleoGrantedAuthority;
import nucleo.comuns.autenticacao.acegi.dominio.NucleoPessoa;
import nucleo.comuns.autenticacao.acegi.dominio.NucleoUserDetails;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.componentes.NucleoTab;
import nucleo.comuns.visao.old.NucleoWindowCadastroDados;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.springframework.dao.DataAccessException;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;


/**
 * Janela de cadastro de dados de um Usuário
 * 
 * @author Alexandre G. N. Coelho
 */
public class WindowCadastroDadosNucleoUsuario extends
		NucleoWindowCadastroDados<NucleoUserDetails> {

	private static final long serialVersionUID = 7683064124609270042L;

	private NucleoAplCadastrarNucleoUserDetails nucleoAplCadastrarNucleoUserDetails;

	private AplCadastrarNucleoPessoa aplCadastrarNucleoPessoa;

	@Override
	protected String getTituloWindow() {
		return NucleoMensagens.getMensagem(NucleoMensagens.TERMO_USUARIO);
	}

	@Override
	protected void configurarComponentesExtensao() {
		this.setWidth("360px");
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
		tbNome.setWidth("200px");
		tbNome.setMaxlength(50);
		tbNome.setParent(row);

		row = new Row();
		row.setParent(rows);
		lbRamal.setParent(row);
		tbRamal.setWidth("200px");
		tbRamal.setMaxlength(10);
		tbRamal.setParent(row);

		row = new Row();
		row.setParent(rows);
		lbTelefone.setParent(row);
		tbTelefone.setWidth("200px");
		tbTelefone.setMaxlength(50);
		tbTelefone.setParent(row);

		row = new Row();
		row.setParent(rows);
		lbCelular.setParent(row);
		tbCelular.setWidth("200px");
		tbCelular.setMaxlength(50);
		tbCelular.setParent(row);

		row = new Row();
		row.setParent(rows);
		lbEmail.setParent(row);
		tbEmail.setWidth("200px");
		tbEmail.setMaxlength(50);
		tbEmail.setParent(row);

		row = new Row();
		row.setParent(rows);
		lbFuncao.setParent(row);
		tbFuncao.setWidth("200px");
		tbFuncao.setMaxlength(200);
		tbFuncao.setParent(row);

		row = new Row();
		row.setParent(rows);
		lbLogin.setParent(row);
		tbLogin.setWidth("200px");
		tbLogin.setMaxlength(50);
		tbLogin.setParent(row);

		row = new Row();
		row.setParent(rows);
		lbSenha.setParent(row);
		tbSenha.setWidth("200px");
		tbSenha.setMaxlength(50);
		tbSenha.setParent(row);
		tbSenha.setType("password");

		row = new Row();
		row.setParent(rows);
		lbTipoUsuario.setParent(row);
		cbxTipoUsuario.setWidth("200px");
		cbxTipoUsuario.appendItem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_TIPO_USUARIO_ADMINISTRADOR));
		cbxTipoUsuario.appendItem(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_TIPO_USUARIO_COMUM));
		cbxTipoUsuario.setReadonly(true);
		cbxTipoUsuario.setParent(row);

		tabDadosCadastro.setConteudoTab(gridDadosCadastro);

		listaTabs.add(tabDadosCadastro);

		return listaTabs;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void preencherDadosTela() {
		// Preenche dados da tela a partir do objeto
		NucleoUserDetails nucleoUserDetails = this.getObjetoCadastroDados();

		// Preenche os dados da pessoa
		NucleoPessoa pessoa = nucleoUserDetails.getPessoa();

		if (pessoa != null) {
			tbNome.setValue(pessoa.getNome());
			tbRamal.setValue(pessoa.getRamal());
			tbTelefone.setValue(pessoa.getTelefone());
			tbCelular.setValue(pessoa.getCelular());
			tbEmail.setValue(pessoa.getEmail());
			tbFuncao.setValue(pessoa.getFuncao());
		}

		// Preenche os dados do usuário
		tbLogin.setValue(nucleoUserDetails.getUsername());

		// Não preenche a senha na tela
		tbSenha.setValue("");

		// Preenche a authority internacionalizável
		if (nucleoUserDetails.getAuthorities().length > 0 && nucleoUserDetails.getAuthorities()[0].getAuthority().equals(
				NucleoGrantedAuthority.AUTHORITY_ADMINISTRADOR)) {
			cbxTipoUsuario
					.setValue(NucleoMensagens
							.getMensagem(NucleoMensagens.TERMO_TIPO_USUARIO_ADMINISTRADOR));
		} else {
			cbxTipoUsuario.setValue(NucleoMensagens
					.getMensagem(NucleoMensagens.TERMO_TIPO_USUARIO_COMUM));
		}

	}

	@Override
	protected void acaoBotaoSalvar() throws NucleoRegraNegocioExcecao {
		// Obtém as classes de aplicação
		nucleoAplCadastrarNucleoUserDetails = (NucleoAplCadastrarNucleoUserDetails) SpringUtil
				.getBean("nucleoUserDetailsService");
		aplCadastrarNucleoPessoa = (AplCadastrarNucleoPessoa) SpringUtil
				.getBean("aplCadastrarNucleoPessoa");

		// Preenche os dados do objeto com os dados da tela
		this.preencherDadosObjeto();
		
		// Salva a pessoa
		aplCadastrarNucleoPessoa.salvar(this.getObjetoCadastroDados().getPessoa());

		// Salva o objeto
		nucleoAplCadastrarNucleoUserDetails.salvar(this.getObjetoCadastroDados());

	}

	@Override
	protected void preencherDadosObjeto() {

		// Preenche um NucleoUserDetails a partir da tela de cadastro de dados
		NucleoUserDetails nucleoUserDetails = this.getObjetoCadastroDados();

		// Preenche dados da pessoa
		NucleoPessoa pessoa = this.getObjetoCadastroDados().getPessoa();

		if (pessoa == null) {
			pessoa = new NucleoPessoa();
			nucleoUserDetails.setPessoa(pessoa);
		}

		pessoa.setNome(tbNome.getValue());
		pessoa.setRamal(tbRamal.getValue());
		pessoa.setTelefone(tbTelefone.getValue());
		pessoa.setCelular(tbCelular.getValue());
		pessoa.setEmail(tbEmail.getValue());
		pessoa.setFuncao(tbFuncao.getValue());

		// Preenche dados do usuário
		nucleoUserDetails.setUsername(tbLogin.getValue());

		// Só modifica a senha caso algo seja digitado
		if (tbSenha.getValue().length() > 0) {
			nucleoUserDetails.setPassword(new Md5PasswordEncoder()
					.encodePassword(tbSenha.getValue(), null));
		}
		// Modifica as authorities caso esteja dentre as possíveis
		if (cbxTipoUsuario
				.getValue()
				.equals(
						NucleoMensagens
								.getMensagem(NucleoMensagens.TERMO_TIPO_USUARIO_ADMINISTRADOR))
				|| cbxTipoUsuario
						.getValue()
						.equals(
								NucleoMensagens
										.getMensagem(NucleoMensagens.TERMO_TIPO_USUARIO_COMUM))) {
			// Limpa as authorities atuais
			nucleoUserDetails.getGrantedAuthorities().clear();

			// Associa à nova authority
			NucleoGrantedAuthority nucleoGrantedAuthority = new NucleoGrantedAuthority();
			if (cbxTipoUsuario
					.getValue()
					.equals(
							NucleoMensagens
									.getMensagem(NucleoMensagens.TERMO_TIPO_USUARIO_ADMINISTRADOR))) {
				nucleoGrantedAuthority
						.setAuthority(NucleoGrantedAuthority.AUTHORITY_ADMINISTRADOR);
			} else {
				nucleoGrantedAuthority
						.setAuthority(NucleoGrantedAuthority.AUTHORITY_USUARIO_COMUM);
			}
			nucleoUserDetails.getGrantedAuthorities().add(nucleoGrantedAuthority);
		}

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

	private Label lbLogin = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_LOGIN)
			+ ": ");

	private Textbox tbLogin = new Textbox();

	private Label lbSenha = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_SENHA)
			+ ": ");

	private Textbox tbSenha = new Textbox();

	private Label lbTipoUsuario = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_TIPO_USUARIO)
			+ ": ");

	private Combobox cbxTipoUsuario = new Combobox();

}
