package ode.medicao.planejamentoMedicao.cih;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ode.medicao.planejamentoMedicao.cdp.ObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoSoftware;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;


@Component
public class ListagemObjetivoSoftware extends ListagemSimples<ObjetivoSoftware>{
	
	
	@Override
	public List<NucleoListHeader> definirColunasTabela() {

		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","40%"));
		colunas.add(new NucleoListHeader("Descri��o","descri��o","60%"));
		return colunas;
	}

	@Override
	protected String[] recuperarDadosObjeto(ObjetivoSoftware objeto) {
		return new String[]{objeto.getNome(),objeto.getDescricao()};
	}
}
