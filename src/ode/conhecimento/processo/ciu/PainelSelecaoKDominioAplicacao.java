package ode.conhecimento.processo.ciu;

import java.util.ArrayList;

import ode._infraestruturaBase.ciu.PainelSelecaoLista;
import ode.conhecimento.processo.cdp.KDominioAplicacao;

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
