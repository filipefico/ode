package ode.medicao.planejamentoMedicao.cih;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ode.medicao.planejamentoMedicao.cdp.KObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoSoftware;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;


@Component
public class ListagemKObjetivoSoftware extends ListagemSimples<KObjetivoSoftware>{
	
	
	@Override
	public List<NucleoListHeader> definirColunasTabela() {

		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","40%"));
		colunas.add(new NucleoListHeader("Descrição","descrição","60%"));
		return colunas;
	}

	@Override
	protected String[] recuperarDadosObjeto(KObjetivoSoftware objeto) {
		return new String[]{objeto.getNome(),objeto.getDescricao()};
	}
}
