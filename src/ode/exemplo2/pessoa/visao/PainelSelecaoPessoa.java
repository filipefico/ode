package ode.exemplo2.pessoa.visao;

import java.util.ArrayList;

import nucleo.comuns.visao.componentes.selecao.PainelSelecaoLista;
import nucleo.comuns.visao.old.NucleoWindowSelecaoLista;
import ode.exemplo2.pessoa.dominio.PessoaExemplo;
import antlr.collections.List;

public class PainelSelecaoPessoa extends PainelSelecaoLista<PessoaExemplo> {

	public PainelSelecaoPessoa() {
		super();
		PessoaExemplo a = new PessoaExemplo();
		a.setNome("Bruno");
		ArrayList objetos = new ArrayList<PessoaExemplo>();
		objetos.add(a);
		a = new PessoaExemplo();
		a.setNome("Bruno 2");
		objetos.add(a);
		a = new PessoaExemplo();
		a.setNome("Bruno 3");
		objetos.add(a);
		preencherLista(objetos);

	}

	@Override
	protected String[] definirTamanhosCabecalho() {
		// TODO Auto-generated method stub
		return new String[] { "100px" };
	}

	@Override
	protected String[] definirTitulosCabecalho() {
		// TODO Auto-generated method stub
		return new String[] { "Nome" };
	}

	@Override
	protected String[] recuperarDadosObjeto(PessoaExemplo objeto) {
		// TODO Auto-generated method stub
		return new String[] { objeto.getNome() };
	}

	@Override
	protected void acaoAbrirItemSelecionado(PessoaExemplo objetoSelecionado) {

		System.out.println("Selecionou a pessoa " + objetoSelecionado.getNome());

	}

}
