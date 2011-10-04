package ode.conhecimento.organizacao.Cih;

import java.util.ArrayList;
import java.util.List;

import ode.conhecimento.organizacao.Cdp.KCompetencia;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.NucleoListHeader;

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
