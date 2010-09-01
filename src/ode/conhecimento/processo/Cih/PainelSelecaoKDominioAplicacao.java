package ode.conhecimento.processo.Cih;

import java.util.ArrayList;

import nucleo.comuns.visao.componentes.selecao.PainelSelecaoLista;
import nucleo.comuns.visao.old.NucleoWindowSelecaoLista;
import ode.conhecimento.processo.Cdp.KDominioAplicacao;
import antlr.collections.List;

public class PainelSelecaoKDominioAplicacao extends PainelSelecaoLista<KDominioAplicacao> {

	public PainelSelecaoKDominioAplicacao() {
		super();
		KDominioAplicacao a = new KDominioAplicacao();
		a.setNome("Bruno");
		ArrayList objetos = new ArrayList<KDominioAplicacao>();
		objetos.add(a);
		a = new KDominioAplicacao();
		a.setNome("Bruno 2");
		objetos.add(a);
		a = new KDominioAplicacao();
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
	protected String[] recuperarDadosObjeto(KDominioAplicacao objeto) {
		// TODO Auto-generated method stub
		return new String[] { objeto.getNome() };
	}

	@Override
	protected void acaoAbrirItemSelecionado(KDominioAplicacao objetoSelecionado) {

		System.out.println("Selecionou a organizacao " + objetoSelecionado.getNome());

	}

}
