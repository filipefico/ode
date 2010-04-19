package ode.exemplo.visao;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.spring.SpringUtil;

import nucleo.comuns.visao.paginacao.ListagemPaginada;
import nucleo.comuns.visao.paginacao.PainelCrud;
import ode.exemplo.aplicacao.AplCadastrarPessoaExemplo;
import ode.exemplo.dominio.PessoaExemplo;

public class PainelCrudPessoa extends PainelCrud<PessoaExemplo> {

	private AplCadastrarPessoaExemplo aplicacao = (AplCadastrarPessoaExemplo) SpringUtil
			.getBean("aplCadastrarPessoaExemplo");

	public PainelCrudPessoa() {
		super();
		this.setNucleoAplCadastroBase(aplicacao);

	}

	@Override
	public ListagemPaginada<PessoaExemplo> definirListagem() {
		ListPessoaExemplo listagem = new ListPessoaExemplo();
		AplCadastrarPessoaExemplo aplicacao = (AplCadastrarPessoaExemplo) SpringUtil
		.getBean("aplCadastrarPessoaExemplo");
		listagem.setNucleoAplCadastroBase(aplicacao);
		return listagem;

	}

	@Override
	public PessoaExemplo criarNovoObjetoDados() {
		return new PessoaExemplo();
	}

	@Override
	public Component definirFormularioCadastro() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
