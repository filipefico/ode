package ode.uml.cih;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;
import ode.uml.cdp.Pacote;

public class ListagemPacote extends ListagemSimples<Pacote> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2456840228686669426L;


	@Override
	protected String[] recuperarDadosObjeto(Pacote objeto) {
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