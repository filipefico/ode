package ode.conhecimento.requisito.Cih;

import java.util.ArrayList;
import java.util.List;

import ode.conhecimento.requisito.Cdp.KTipoRequisito;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.NucleoListHeader;

public class ListagemKTipoRequisito extends ListagemSimples<KTipoRequisito> {

	@Override
	protected String[] recuperarDadosObjeto(KTipoRequisito objeto) {
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