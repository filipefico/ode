package ode.conhecimento.requisito.cih;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;
import ode.conhecimento.requisito.cdp.CategoriaRequisito;

@SuppressWarnings("serial")
public class ListagemCategoriaRequisito extends ListagemSimples<CategoriaRequisito> {

	@Override
	protected String[] recuperarDadosObjeto(CategoriaRequisito objeto) {
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