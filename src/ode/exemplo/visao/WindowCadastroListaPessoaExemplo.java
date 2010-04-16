package ode.exemplo.visao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.persistencia.ObjetoPagina;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.NucleoWindowCadastroLista;
import nucleo.comuns.visao.paginacao.WindowCadastroListaPaginada;
import ode.exemplo.aplicacao.AplCadastrarPessoaExemplo;
import ode.exemplo.dominio.PessoaExemplo;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;

public class WindowCadastroListaPessoaExemplo extends
		WindowCadastroListaPaginada<PessoaExemplo> {

	
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
	protected String[] definirAtributoOrdenacao() {
	
		return new String[]{"nome","sobrenome","email","idade"};
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

	protected Collection<PessoaExemplo> recuperarObjetos()
			throws DataAccessException, NucleoRegraNegocioExcecao {

		List<Criterion> criterios = new ArrayList<Criterion>();
		ObjetoPagina pagina =ObjetoPagina.factory(0,5, criterios);
		//criterios.add(cIdade);
		Collection<PessoaExemplo> objetos = getNucleoAplCadastroBase()
				.recuperarTodosPaginado(pagina);
		return objetos;
	}


	


}
