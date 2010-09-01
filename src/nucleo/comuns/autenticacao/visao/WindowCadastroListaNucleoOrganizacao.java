package nucleo.comuns.autenticacao.visao;

import nucleo.comuns.autenticacao.acegi.aplicacao.AplCadastrarNucleoOrganizacao;
import nucleo.comuns.autenticacao.acegi.dominio.NucleoOrganizacao;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.old.NucleoWindowCadastroLista;

import org.zkoss.zkplus.spring.SpringUtil;


public class WindowCadastroListaNucleoOrganizacao extends NucleoWindowCadastroLista<NucleoOrganizacao> {

	private static final long serialVersionUID = 7565671437485604530L;
	
	// Nome da window de cadastro de dados chamada a partir da lista
	private static final String NOME_WINDOW_CADASTRO_DADOS = "/visao/admin/windowCadastroDadosOrganizacao.zul";

	public WindowCadastroListaNucleoOrganizacao() {
		this.setNucleoAplCadastroBase((AplCadastrarNucleoOrganizacao) SpringUtil
				.getBean("aplCadastrarNucleoOrganizacao"));
	}
	
	@Override
	protected String getTituloWindow() {
		return NucleoMensagens.getMensagem(NucleoMensagens.TERMO_ORGANIZACAO);
	}
	
	@Override
	protected void configurarComponentesExtensao() {
		this.setWidth("414px");
	}

	@Override
	protected NucleoOrganizacao criarNovoObjetoDados() {
		return new NucleoOrganizacao();
	}

	@Override
	protected String definirNomeWindowCadastroDados() {
		return NOME_WINDOW_CADASTRO_DADOS;
	}

	@Override
	protected String[] definirTamanhosCabecalho() {
		return new String[]{"200px","100px"};
	}

	@Override
	protected String[] definirTitulosCabecalho() {
		return new String[]{NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME)};
	}

	@Override
	protected String[] recuperarDadosObjeto(NucleoOrganizacao objeto) {
		return new String[]{objeto.getNome()};
	}

}
