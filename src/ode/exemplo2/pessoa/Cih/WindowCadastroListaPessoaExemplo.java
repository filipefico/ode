package ode.exemplo2.pessoa.Cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.persistencia.ObjetoPagina;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.old.NucleoWindowCadastroLista;
import ode.exemplo2.pessoa.Cdp.PessoaExemplo;
import ode.exemplo2.pessoa.Cgt.AplCadastrarPessoaExemplo;

import org.hibernate.criterion.Criterion;
import org.springframework.dao.DataAccessException;
import org.zkoss.zkplus.spring.SpringUtil;

public class WindowCadastroListaPessoaExemplo extends
		NucleoWindowCadastroLista<PessoaExemplo> {

	
	public static String CAMINHO = "/visao/exemplo/windowCadastroListaPessoaExemplo.zul";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WindowCadastroListaPessoaExemplo() {
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
