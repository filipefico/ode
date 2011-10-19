package ode.conhecimentoMedicao.cih;

import java.util.ArrayList;
import java.util.List;

import ode.conhecimentoMedicao.cdp.KNecessidadeInformacao;
import ode.conhecimentoMedicao.cdp.KObjetivoEstrategico;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;

public class ListagemKNecessidadeInformacao extends ListagemSimples<KNecessidadeInformacao>{

	@Override
	public List<NucleoListHeader> definirColunasTabela() {

		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","40%"));
		colunas.add(new NucleoListHeader("Descrição","descricao","60%"));
		return colunas;
	}

	@Override
	protected String[] recuperarDadosObjeto(KNecessidadeInformacao objeto) {
		return new String[]{objeto.getNome(),objeto.getDescricao()};
	}

}
