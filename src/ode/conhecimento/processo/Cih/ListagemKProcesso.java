package ode.conhecimento.processo.Cih;

import java.util.ArrayList;
import java.util.List;

import ode.conhecimento.processo.Cdp.KProcesso;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.NucleoListHeader;

public class ListagemKProcesso extends ListagemSimples<KProcesso> {

	@Override
	protected String[] recuperarDadosObjeto(KProcesso objeto) {
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
