package ode.exemplo.visao;

import org.zkoss.zkplus.spring.SpringUtil;

import ode.exemplo.aplicacao.AplCadastrarPessoaExemplo;
import ode.exemplo.dominio.PessoaExemplo;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.old.GuilexWindowCadastroLista;

public class WindowCadastroListaPessoaExemplo2 extends GuilexWindowCadastroLista<PessoaExemplo> {

	
	public static String CAMINHO = "/visao/exemplo/windowCadastroListaPessoaExemplo2.zul";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WindowCadastroListaPessoaExemplo2() {
		WIDTH_WINDOW = "60%";
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
		return new String[] { "210px", "210px","210px", "210px" };
	}
	

	@Override
	protected String[] definirTitulosCabecalho() {
		return new String[] {
				NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME),"Sobrenome",
				NucleoMensagens.getMensagem(NucleoMensagens.TERMO_EMAIL) ,"Idade"};
	}

	@Override
	protected String[] recuperarDadosObjeto(PessoaExemplo objeto) {
		return new String[] { objeto.getNome(),objeto.getSobrenome(), objeto.getEmail(),""+objeto.getIdade() };
	}

	@Override
	protected String getTituloWindow() {
		return NucleoMensagens.getMensagem(NucleoMensagens.TERMO_PESSOAS);
	}



	

}
