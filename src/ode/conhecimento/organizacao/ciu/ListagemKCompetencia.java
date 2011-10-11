package ode.conhecimento.organizacao.ciu;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;
import ode.conhecimento.organizacao.cdp.KCompetencia;

public class ListagemKCompetencia extends ListagemSimples<KCompetencia> {

	private static final long serialVersionUID = 1L;

	@Override
	protected String[] recuperarDadosObjeto(KCompetencia objeto) {
		return new String[]{objeto.getNome()};
	}
	
	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","100%"));
		return colunas;
	}

}
