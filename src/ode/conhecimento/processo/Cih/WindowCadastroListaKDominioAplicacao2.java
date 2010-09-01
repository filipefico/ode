package ode.conhecimento.processo.Cih;

import org.zkoss.zkplus.spring.SpringUtil;

import ode.conhecimento.processo.Cdp.KDominioAplicacao;
import ode.conhecimento.processo.Cgt.AplCadastrarKDominioAplicacao;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.old.GuilexWindowCadastroLista;

public class WindowCadastroListaKDominioAplicacao2 extends GuilexWindowCadastroLista<KDominioAplicacao> {

	
	public static String CAMINHO = "/visao/exemplo/windowCadastroListaKDominioAplicacao.zul";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WindowCadastroListaKDominioAplicacao2() {
		WIDTH_WINDOW = "60%";
		//this.setNucleoAplCadastroBase((AplCadastrarKDominioAplicacao) SpringUtil
			//	.getBean("aplCadastrarKDominioAplicacao"));
	}

	// Nome da window de cadastro de dados chamada a partir da lista
	private static final String NOME_WINDOW_CADASTRO_DADOS = "/visao/exemplo/windowCadastroDadosKDominioAplicacao.zul";

	@Override
	protected KDominioAplicacao criarNovoObjetoDados() {
		return new KDominioAplicacao();
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
				NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME),"Nome",
				NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DESCRICAO) ,"Descricao"};
	}

	@Override
	protected String[] recuperarDadosObjeto(KDominioAplicacao objeto) {
		return new String[] { objeto.getNome()};
	}

	@Override
	protected String getTituloWindow() {
		return NucleoMensagens.getMensagem(NucleoMensagens.TERMO_ORGANIZACAO);
	}



	

}
