package ode.problema.ciu;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;
import ode.problema.cdp.KCriterioSelecaoSolucao;




public class ListagemKCriterioSelecaoSolucao extends ListagemSimples<KCriterioSelecaoSolucao> {


	private static final long serialVersionUID = 5870885727856286893L;

	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","155px"));
		return colunas;
	}

	@Override
	protected String[] recuperarDadosObjeto(KCriterioSelecaoSolucao objeto) {
		// TODO Auto-generated method stub
		return new String[]{objeto.getNome()} ;
	}

}
