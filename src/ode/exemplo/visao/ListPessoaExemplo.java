package ode.exemplo.visao;

import java.util.ArrayList;
import java.util.List;

import ode.exemplo.dominio.PessoaExemplo;
import nucleo.comuns.visao.paginacao.ListagemPaginada;
import nucleo.comuns.visao.paginacao.NucleoListHeader;

public class ListPessoaExemplo extends ListagemPaginada<PessoaExemplo> {

	@Override
	protected String[] recuperarDadosObjeto(PessoaExemplo objeto) {
		return new String[]{objeto.getNome(),objeto.getIdade()+""};
	}
	
	
	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","210px"));
		colunas.add(new NucleoListHeader("Idade","idade","210px"));
		return colunas;
	}



}
