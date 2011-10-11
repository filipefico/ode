package ode.conhecimento.processo.ciu;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;
import ode.conhecimento.processo.cdp.KAtividade;


public class ListagemKAtividade extends ListagemSimples<KAtividade> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 767335923010066407L;


	@Override
	protected String[] recuperarDadosObjeto(KAtividade objeto) {
		return new String[]{objeto.getNome(),objeto.getDescricao()};
	}
	
	
	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","120px"));
		colunas.add(new NucleoListHeader("Descricao","descricao","150px"));
		return colunas;
	}


}
