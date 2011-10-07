package ode._controleRecursoHumano.ciu;

import java.util.ArrayList;
import java.util.List;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;

public class ListagemRecursoHumano extends ListagemSimples<RecursoHumano> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String[] recuperarDadosObjeto(RecursoHumano objeto) {
		return new String[]{objeto.getNome()};
	}
	
	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","100%"));
		return colunas;
	}

}
