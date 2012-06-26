package ode.problema.ciu;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;
import ode.problema.cdp.KCategoriaProblema;




public class ListagemKCategoriaProblema extends ListagemSimples<KCategoriaProblema> {


	private static final long serialVersionUID = -1166960025985318288L;

	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","155px"));
		return colunas;
	}

	@Override
	protected String[] recuperarDadosObjeto(KCategoriaProblema objeto) {
		// TODO Auto-generated method stub
		return new String[]{objeto.getNome()} ;
	}


}
