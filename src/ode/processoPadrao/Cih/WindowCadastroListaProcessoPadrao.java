package ode.processoPadrao.Cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.persistencia.ObjetoPagina;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.old.NucleoWindowCadastroLista;
import ode.processoPadrao.Cdp.CompPP;
import ode.processoPadrao.Cgt.AplCadastrarProcessoPadrao;

import org.hibernate.criterion.Criterion;
import org.springframework.dao.DataAccessException;
import org.zkoss.zkplus.spring.SpringUtil;

public class WindowCadastroListaProcessoPadrao extends
		NucleoWindowCadastroLista<CompPP> {

	
	public static String CAMINHO = "/visao/cadastros_gerais/windowCadastroListaProcessoPadrao.zul";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WindowCadastroListaProcessoPadrao() {
		WIDTH_WINDOW = "60%";
		this.setNucleoAplCadastroBase((AplCadastrarProcessoPadrao) SpringUtil
				.getBean("aplCadastrarProcessoPadrao"));
	}

	// Nome da window de cadastro de dados chamada a partir da lista
	private static final String NOME_WINDOW_CADASTRO_DADOS = "/visao/cadastros_gerais/windowCadastroDadosProcessoPadrao.zul";

	@Override
	protected CompPP criarNovoObjetoDados() {
		return new CompPP();
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
				NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME),"",
				NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DESCRICAO) ,""};
	}

	@Override
	protected String[] recuperarDadosObjeto(CompPP objeto) {
		return new String[] { objeto.getNome(),objeto.getDescricao()};
	}

	@Override
	protected String getTituloWindow() {
		return NucleoMensagens.getMensagem(NucleoMensagens.TERMO_COMPPP);
	}



	


}
