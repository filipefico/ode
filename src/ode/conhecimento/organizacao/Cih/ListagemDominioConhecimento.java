package ode.conhecimento.organizacao.Cih;

import java.util.ArrayList;
import java.util.List;

import nucleo.comuns.visao.paginacao.ListagemPaginada;
import nucleo.comuns.visao.paginacao.NucleoListHeader;
import ode.conhecimento.organizacao.Cdp.KDominioConhecimento;

public class ListagemDominioConhecimento extends ListagemPaginada<KDominioConhecimento> {
	
	@Override
	protected String[] recuperarDadosObjeto(KDominioConhecimento objeto) {
		return new String[]{objeto.getNome(), objeto.getDescricao()};
	}
	
	
	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","150px"));
		colunas.add(new NucleoListHeader("Descricao","descricao","350px"));
		return colunas;
	}


}
