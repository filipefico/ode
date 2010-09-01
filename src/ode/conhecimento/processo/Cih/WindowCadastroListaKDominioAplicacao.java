package ode.conhecimento.processo.Cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.persistencia.ObjetoPagina;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.old.NucleoWindowCadastroLista;
import ode.conhecimento.processo.Cdp.KDominioAplicacao;
import ode.conhecimento.processo.Cgt.AplCadastrarKDominioAplicacao;

import org.hibernate.criterion.Criterion;
import org.springframework.dao.DataAccessException;
import org.zkoss.zkplus.spring.SpringUtil;

public class WindowCadastroListaKDominioAplicacao extends
		NucleoWindowCadastroLista<KDominioAplicacao> {

	
	public static String CAMINHO = "/visao/exemplo/windowCadastroListaKDominioAplicacao.zul";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WindowCadastroListaKDominioAplicacao() {
		WIDTH_WINDOW = "60%";
		this.setNucleoAplCadastroBase((AplCadastrarKDominioAplicacao) SpringUtil
				.getBean("aplCadastrarKDominioAplicacao"));
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
				NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME),"Nome"};
	}

	@Override
	protected String[] recuperarDadosObjeto(KDominioAplicacao objeto) {
		return new String[] { objeto.getNome(),objeto.getDescricao()};
	}

	@Override
	protected String getTituloWindow() {
		return NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME);
	}



	


}
