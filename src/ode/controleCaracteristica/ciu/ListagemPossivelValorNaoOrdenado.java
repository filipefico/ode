package ode.controleCaracteristica.ciu;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.zkoss.zul.Listitem;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;
import ode.controleCaracteristica.cdp.PossivelValorNaoOrdenado;

public class ListagemPossivelValorNaoOrdenado extends ListagemSimples<PossivelValorNaoOrdenado> {

	private static final long serialVersionUID = 8580487562426512786L; 
	
	@Override
	protected String[] recuperarDadosObjeto(PossivelValorNaoOrdenado objeto) {
		return new String[]{objeto.getNome(), objeto.getDescricao()};
	}
	
	
	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","150px"));
		colunas.add(new NucleoListHeader("Descrição","descricao","350px"));
		return colunas;
	}
	
	public Set<PossivelValorNaoOrdenado> getValoresSelecionados() {
		return listBox.getSelectedItems();
	}
	
	
}