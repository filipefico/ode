package ode.medicao.analiseMedicao.ciu;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;
import ode.medicao.analiseMedicao.cdp.AcaoCorretiva;

public class ListagemAcaoCorretiva extends ListagemSimples<AcaoCorretiva>{

	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Ação","ação","40%"));
		colunas.add(new NucleoListHeader("Descrição","descrição","60%"));
		return colunas;
	}

	@Override
	protected String[] recuperarDadosObjeto(AcaoCorretiva objeto) {
		return new String[]{objeto.getNome(),objeto.getDescricao()};
	}

}
