package ode.conhecimentoMedicao.cih;

import java.util.ArrayList;
import java.util.List;

import ode.conhecimentoMedicao.cdp.KElementoMensuravel;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;

public class ListagemKElementoMensuravel extends ListagemSimples<KElementoMensuravel>{

	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		
		List<NucleoListHeader> cabecalhos = new ArrayList<NucleoListHeader>();
		
		cabecalhos.add(new NucleoListHeader("Nome", "nome", "40%"));
		cabecalhos.add(new NucleoListHeader("Descrição", "descricao", "60%"));
		
		return cabecalhos;
	}

	@Override
	protected String[] recuperarDadosObjeto(KElementoMensuravel objeto) {
		return new String[]{objeto.getNome(),objeto.getDescricao()};
	}

}
