package ode.conhecimento.processo.Cih;

import java.util.ArrayList;
import java.util.List;

import ode.conhecimento.processo.Cdp.KTipoSoftware;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.NucleoListHeader;

public class ListagemKTipoSoftware extends ListagemSimples<KTipoSoftware> {

	@Override
	protected String[] recuperarDadosObjeto(KTipoSoftware objeto) {
		return new String[]{objeto.getNome(), objeto.getDescricao()};
	}
	
	
	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","150px"));
		colunas.add(new NucleoListHeader("Descrição","descricao","350px"));
		return colunas;
	}


}
