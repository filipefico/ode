package ode._controleFerramenta.ciu;

import java.util.ArrayList;
import java.util.List;

import ode._controleFerramenta.cdp.FerramentaSoftware;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;

public class ListagemFerramentaSoftware extends ListagemSimples<FerramentaSoftware> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String[] recuperarDadosObjeto(FerramentaSoftware objeto) {
		return new String[]{objeto.getNome()};
	}
	
	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","100%"));
		return colunas;
	}

}
