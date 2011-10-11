package ode.processoPadrao.ciu;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;
import ode.processoPadrao.cdp.CompPP;

public class ListagemDefinirProcessoPadrao extends ListagemSimples<CompPP> {

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
		colunas.add(new NucleoListHeader("Nome","nome","150px"));
		colunas.add(new NucleoListHeader("Descricao","descricao","150px"));
	//	colunas.add(new NucleoListHeader("Objetivo","objetivo","150px"));
		return colunas;
	}


}
