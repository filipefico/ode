package ode.conhecimento.processo.Cih;

import java.util.ArrayList;
import java.util.List;

import ode.conhecimento.processo.Cdp.KDominioAplicacao;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.NucleoListHeader;


public class ListagemKDominioAplicacao extends ListagemSimples<KDominioAplicacao> {

	@Override
	protected String[] recuperarDadosObjeto(KDominioAplicacao objeto) {
		return new String[]{objeto.getNome(),objeto.getDescricao()};
	}
	
	
	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","200px"));
		colunas.add(new NucleoListHeader("Descricao","descricao","400px"));
		return colunas;
	}


}
