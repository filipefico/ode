package ode.controleProjeto.cih;

import java.util.ArrayList;
import java.util.List;

import ode.controleProjeto.cdp.Projeto;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.NucleoListHeader;

public class ListagemProjeto extends ListagemSimples<Projeto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String[] recuperarDadosObjeto(Projeto objeto) {
		return new String[]{objeto.getNome(), objeto.getDescricao()};
	}
	
	
	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","50%"));
		colunas.add(new NucleoListHeader("Descrição","descricao","50%"));
		return colunas;
		
	}

}
