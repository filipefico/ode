package ode.exemplo2.organizacao.Cih;

import java.util.ArrayList;

import nucleo.comuns.visao.componentes.selecao.PainelSelecaoLista;
import nucleo.comuns.visao.old.NucleoWindowSelecaoLista;
import ode.exemplo2.organizacao.Cdp.OrganizacaoExemplo;
import antlr.collections.List;

public class PainelSelecaoOrganizacao extends PainelSelecaoLista<OrganizacaoExemplo> {

	public PainelSelecaoOrganizacao() {
		super();
		OrganizacaoExemplo a = new OrganizacaoExemplo();
		a.setNome("Bruno");
		ArrayList objetos = new ArrayList<OrganizacaoExemplo>();
		objetos.add(a);
		a = new OrganizacaoExemplo();
		a.setNome("Bruno 2");
		objetos.add(a);
		a = new OrganizacaoExemplo();
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
	protected String[] recuperarDadosObjeto(OrganizacaoExemplo objeto) {
		// TODO Auto-generated method stub
		return new String[] { objeto.getNome() };
	}

	@Override
	protected void acaoAbrirItemSelecionado(OrganizacaoExemplo objetoSelecionado) {

		System.out.println("Selecionou a organizacao " + objetoSelecionado.getNome());

	}

}
