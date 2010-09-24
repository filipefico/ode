package ode.processoPadrao.Cih;

import java.util.ArrayList;
import java.util.List;

import nucleo.comuns.visao.paginacao.ListagemPaginada;
import nucleo.comuns.visao.paginacao.NucleoListHeader;
import ode.exemplo2.pessoa.Cdp.PessoaExemplo;
import ode.processoPadrao.Cdp.CompPP;

public class ListagemProcessoPadrao extends ListagemPaginada<CompPP> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	protected String[] recuperarDadosObjeto(CompPP objeto) {
		return new String[]{objeto.getNome(),objeto.getDescricao()};
	}
	
	
	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","200px"));
		colunas.add(new NucleoListHeader("Descricao","descricao","300px"));
		return colunas;
	}


}
