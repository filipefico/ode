package ode.exemplo.visao;

import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.NucleoWindowCadastroLista;

import ode.exemplo.aplicacao.AplCadastrarPessoaExemplo;
import ode.exemplo.dominio.PessoaExemplo;

import org.zkoss.zkplus.spring.SpringUtil;


public class WindowCadastroListaPessoaExemplo extends NucleoWindowCadastroLista<PessoaExemplo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WindowCadastroListaPessoaExemplo() {
		this.setNucleoAplCadastroBase((AplCadastrarPessoaExemplo) SpringUtil
				.getBean("aplCadastrarPessoaExemplo"));
	}
	
	// Nome da window de cadastro de dados chamada a partir da lista
	private static final String NOME_WINDOW_CADASTRO_DADOS = "/visao/exemplo/windowCadastroDadosPessoaExemplo.zul";
	
	@Override
	protected PessoaExemplo criarNovoObjetoDados() {
		return new PessoaExemplo();
	}

	@Override
	protected String definirNomeWindowCadastroDados() {
		return NOME_WINDOW_CADASTRO_DADOS;
	}

	@Override
	protected String[] definirTamanhosCabecalho() {
		return new String[] { "210px", "210px" };
	}

	@Override
	protected String[] definirTitulosCabecalho() {
		return new String[] {
				NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME),
				NucleoMensagens.getMensagem(NucleoMensagens.TERMO_EMAIL)};
	}

	@Override
	protected String[] recuperarDadosObjeto(PessoaExemplo objeto) {
		return new String[] { objeto.getNome(), objeto.getEmail()};
	}

	@Override
	protected String getTituloWindow() {
		return NucleoMensagens.getMensagem(NucleoMensagens.TERMO_PESSOAS);
	}

}
