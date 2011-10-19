package ode.conhecimentoMedicao.cih;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ode.conhecimentoMedicao.cdp.KObjetivoEstrategico;
import ode.conhecimentoMedicao.cdp.KObjetivoMedicao;
import ode.conhecimentoMedicao.cdp.KObjetivoSoftware;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;


@Component
public class ListagemKObjetivoMedicao extends ListagemSimples<KObjetivoMedicao>{
	
	
	@Override
	public List<NucleoListHeader> definirColunasTabela() {

		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","40%"));
		colunas.add(new NucleoListHeader("Descrição","descrição","60%"));
		return colunas;
	}

	@Override
	protected String[] recuperarDadosObjeto(KObjetivoMedicao objeto) {
		return new String[]{objeto.getNome(),objeto.getDescricao()};
	}
}
