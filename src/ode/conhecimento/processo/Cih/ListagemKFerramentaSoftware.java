package ode.conhecimento.processo.Cih;

import java.util.ArrayList;
import java.util.List;

import ode.conhecimento.processo.Cdp.KFerramentaSoftware;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.NucleoListHeader;


public class ListagemKFerramentaSoftware extends ListagemSimples<KFerramentaSoftware> {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected String[] recuperarDadosObjeto(KFerramentaSoftware objeto) {
		return new String[]{objeto.getNome(), objeto.getDescricao()};
	}
	
	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","200px"));
		colunas.add(new NucleoListHeader("Descrição","descricao","364px"));
		return colunas;
	}
}
