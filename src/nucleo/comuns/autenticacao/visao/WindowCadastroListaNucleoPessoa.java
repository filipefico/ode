package nucleo.comuns.autenticacao.visao;

import nucleo.comuns.autenticacao.acegi.aplicacao.AplCadastrarNucleoPessoa;
import nucleo.comuns.autenticacao.acegi.dominio.NucleoPessoa;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.NucleoWindowCadastroLista;

import org.zkoss.zkplus.spring.SpringUtil;


public class WindowCadastroListaNucleoPessoa extends NucleoWindowCadastroLista<NucleoPessoa> {

	private static final long serialVersionUID = 7565671437485604530L;
	
	// Nome da window de cadastro de dados chamada a partir da lista
	private static final String NOME_WINDOW_CADASTRO_DADOS = "/visao/admin/windowCadastroDadosPessoa.zul";

	public WindowCadastroListaNucleoPessoa() {
		this.setNucleoAplCadastroBase((AplCadastrarNucleoPessoa) SpringUtil
				.getBean("aplCadastrarNucleoPessoa"));
	}
	
	@Override
	protected String getTituloWindow() {
		return NucleoMensagens.getMensagem(NucleoMensagens.TERMO_PESSOAS);
	}
	
	@Override
	protected void configurarComponentesExtensao() {
		this.setWidth("414px");
	}

	@Override
	protected NucleoPessoa criarNovoObjetoDados() {
		return new NucleoPessoa();
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
		return new String[]{NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME),NucleoMensagens.getMensagem(NucleoMensagens.TERMO_TELEFONE)};
	}

	@Override
	protected String[] recuperarDadosObjeto(NucleoPessoa objeto) {
		return new String[]{objeto.getNome(), objeto.getTelefone()};
	}

}
