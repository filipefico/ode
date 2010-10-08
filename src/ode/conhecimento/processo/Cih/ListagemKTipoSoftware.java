package ode.conhecimento.processo.Cih;

import java.util.ArrayList;
import java.util.List;

import nucleo.comuns.visao.paginacao.ListagemPaginada;
import nucleo.comuns.visao.paginacao.NucleoListHeader;
import ode.conhecimento.processo.Cdp.KTipoSoftware;

public class ListagemKTipoSoftware extends ListagemPaginada<KTipoSoftware> {

	@Override
	protected String[] recuperarDadosObjeto(KTipoSoftware objeto) {
		return new String[]{objeto.getNome()};
	}
	
	
	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","200px"));
		return colunas;
	}


}
