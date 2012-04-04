package ode.gerenciaRequisitos.cih;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;
import ode.gerenciaRequisitos.cdp.Requisito;

public class ListagemRequisito extends ListagemSimples<Requisito> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	protected String[] recuperarDadosObjeto(Requisito objeto) {
		return new String[]{objeto.getIdentificador(), objeto.getDescricao()};
	}
	
	
	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Identificador","identificador","150px"));
		colunas.add(new NucleoListHeader("Descrição","descricao","350px"));
		return colunas;
	}
}