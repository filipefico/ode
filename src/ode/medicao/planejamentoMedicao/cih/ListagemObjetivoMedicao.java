package ode.medicao.planejamentoMedicao.cih;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ode.medicao.planejamentoMedicao.cdp.ObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoSoftware;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;


@Component
public class ListagemObjetivoMedicao extends ListagemSimples<ObjetivoMedicao>{
	
	
	@Override
	public List<NucleoListHeader> definirColunasTabela() {

		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","40%"));
		colunas.add(new NucleoListHeader("Descrição","descrição","60%"));
		return colunas;
	}

	@Override
	protected String[] recuperarDadosObjeto(ObjetivoMedicao objeto) {
		return new String[]{objeto.getNome(),objeto.getDescricao()};
	}
}
