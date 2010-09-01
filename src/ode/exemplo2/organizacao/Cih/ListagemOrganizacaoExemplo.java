package ode.exemplo2.organizacao.Cih;

import java.util.ArrayList;
import java.util.List;

import nucleo.comuns.visao.paginacao.ListagemPaginada;
import nucleo.comuns.visao.paginacao.NucleoListHeader;
import ode.exemplo2.organizacao.Cdp.OrganizacaoExemplo;


public class ListagemOrganizacaoExemplo extends ListagemPaginada<OrganizacaoExemplo> {

	@Override
	protected String[] recuperarDadosObjeto(OrganizacaoExemplo objeto) {
		return new String[]{objeto.getNome(),objeto.getDescricao()};
	}
	
	
	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","200px"));
		colunas.add(new NucleoListHeader("Descricao","descricao","400px"));
		return colunas;
	}


}
