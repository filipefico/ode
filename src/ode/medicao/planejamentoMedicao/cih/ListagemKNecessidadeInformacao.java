package ode.medicao.planejamentoMedicao.cih;

import java.util.ArrayList;
import java.util.List;

import ode.medicao.planejamentoMedicao.cdp.KNecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoEstrategico;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;

public class ListagemKNecessidadeInformacao extends ListagemSimples<KNecessidadeInformacao>{

	@Override
	public List<NucleoListHeader> definirColunasTabela() {

		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","40%"));
		colunas.add(new NucleoListHeader("Descri��o","descricao","60%"));
		return colunas;
	}

	@Override
	protected String[] recuperarDadosObjeto(KNecessidadeInformacao objeto) {
		return new String[]{objeto.getNome(),objeto.getDescricao()};
	}

}
