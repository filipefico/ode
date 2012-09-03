package ode.medicao.analiseMedicao.ciu;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;
import ode.medicao.analiseMedicao.cdp.AnaliseMedicao;
import ode.medicao.analiseMedicao.cdp.MonitoramentoObjetivo;

public class ListagemMonitoramento extends ListagemSimples<MonitoramentoObjetivo> {

	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Monitoramento","Monitoramento","65%"));
		colunas.add(new NucleoListHeader("Responsável","Responsável","35%"));
		return colunas;
	}

	@Override
	protected String[] recuperarDadosObjeto(MonitoramentoObjetivo objeto) {
		return new String[]{objeto.getObjetivo()+" - "+objeto.getData(),objeto.getExecutorMonitoramento().toString()};
	}

}
