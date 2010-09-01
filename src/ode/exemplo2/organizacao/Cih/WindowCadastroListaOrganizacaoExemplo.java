package ode.exemplo2.organizacao.Cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.persistencia.ObjetoPagina;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.old.NucleoWindowCadastroLista;
import ode.exemplo2.organizacao.Cdp.OrganizacaoExemplo;
import ode.exemplo2.organizacao.Cgt.AplCadastrarOrganizacaoExemplo;

import org.hibernate.criterion.Criterion;
import org.springframework.dao.DataAccessException;
import org.zkoss.zkplus.spring.SpringUtil;

public class WindowCadastroListaOrganizacaoExemplo extends
		NucleoWindowCadastroLista<OrganizacaoExemplo> {

	
	public static String CAMINHO = "/visao/exemplo/windowCadastroListaOrganizacaoExemplo.zul";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WindowCadastroListaOrganizacaoExemplo() {
		WIDTH_WINDOW = "60%";
		this.setNucleoAplCadastroBase((AplCadastrarOrganizacaoExemplo) SpringUtil
				.getBean("aplCadastrarOrganizacaoExemplo"));
	}

	// Nome da window de cadastro de dados chamada a partir da lista
	private static final String NOME_WINDOW_CADASTRO_DADOS = "/visao/exemplo/windowCadastroDadosOrganizacaoExemplo.zul";

	@Override
	protected OrganizacaoExemplo criarNovoObjetoDados() {
		return new OrganizacaoExemplo();
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
	protected String[] recuperarDadosObjeto(OrganizacaoExemplo objeto) {
		return new String[] { objeto.getNome(),objeto.getDescricao()};
	}

	@Override
	protected String getTituloWindow() {
		return NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME);
	}



	


}
