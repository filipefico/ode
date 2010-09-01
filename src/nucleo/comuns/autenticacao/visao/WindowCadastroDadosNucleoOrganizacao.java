package nucleo.comuns.autenticacao.visao;

import java.util.ArrayList;
import java.util.List;

import nucleo.comuns.autenticacao.acegi.aplicacao.AplCadastrarNucleoOrganizacao;
import nucleo.comuns.autenticacao.acegi.dominio.NucleoOrganizacao;
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


public class WindowCadastroDadosNucleoOrganizacao extends NucleoWindowCadastroDados<NucleoOrganizacao> {

	private static final long serialVersionUID = 1410190504248546207L;

	public WindowCadastroDadosNucleoOrganizacao() {
		this.setNucleoAplCadastroBase((AplCadastrarNucleoOrganizacao) SpringUtil
				.getBean("aplCadastrarNucleoOrganizacao"));
	}

	@Override
	protected String getTituloWindow() {
		return NucleoMensagens.getMensagem(NucleoMensagens.TERMO_ORGANIZACAO);
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
		lbDescricao.setParent(row);
	//	tbDescricao.setWidth("150px");
	//	tbDescricao.setMaxlength(10);
	//	tbDescricao.setParent(row);

	
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);

		listaTabs.add(tabDadosCadastro);

		return listaTabs;
	}

	@Override
	protected void preencherDadosTela() {
//		tbNome.setValue(organizacao.getNome());
	//	tbDesricao.setValue(organizacao.getRamal());
	}

	@Override
	protected void preencherDadosObjeto() {
		NucleoOrganizacao organizacao = this.getObjetoCadastroDados();
		organizacao.setNome(tbNome.getValue());
//		organizacao.setDescricao(tbDescricao.getValue());
	}

	private Label lbNome = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_NOME)
			+ ": ");

	private Textbox tbNome = new Textbox();
	
	private Label lbDescricao = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_DESCRICAO)
			+ ": ");

}
