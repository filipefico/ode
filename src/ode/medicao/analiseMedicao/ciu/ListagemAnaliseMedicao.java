package ode.medicao.analiseMedicao.ciu;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;
import ode.medicao.analiseMedicao.cdp.AnaliseMedicao;

public class ListagemAnaliseMedicao extends ListagemSimples<AnaliseMedicao> {

	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Analise","Analise","65%"));
		colunas.add(new NucleoListHeader("Responsável","Responsável","35%"));
		return colunas;
	}

	@Override
	protected String[] recuperarDadosObjeto(AnaliseMedicao objeto) {
		return new String[]{objeto.getMedida()+" - "+objeto.getData(),objeto.getExecutorAnalise().toString()};
	}

}
