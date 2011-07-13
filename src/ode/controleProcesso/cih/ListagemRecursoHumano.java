package ode.controleProcesso.cih;

import java.util.ArrayList;
import java.util.List;

import nucleo.comuns.visao.listagem.ListagemSimples;
import nucleo.comuns.visao.paginacao.NucleoListHeader;
import ode.controleProcesso.cdp.RecursoHumano;

public class ListagemRecursoHumano extends ListagemSimples<RecursoHumano> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String[] recuperarDadosObjeto(RecursoHumano objeto) {
		return new String[]{objeto.getNome()};
	}
	
	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","100%"));
		return colunas;
	}

}
