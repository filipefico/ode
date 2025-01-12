package ode.conhecimento.processo.ciu;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;
import ode.conhecimento.processo.cdp.KFerramentaSoftware;


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
		colunas.add(new NucleoListHeader("Descri��o","descricao","364px"));
		return colunas;
	}
}
