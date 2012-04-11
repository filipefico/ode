package ode.gerenciaConhecimento.ciu;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;
import ode.gerenciaConhecimento.cdp.Tema;

public class ListagemTema extends ListagemSimples<Tema>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		// TODO Auto-generated method stub
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","150px"));
		colunas.add(new NucleoListHeader("Descrição","descricao","350px"));
		return colunas;
	}

	@Override
	protected String[] recuperarDadosObjeto(Tema objeto) {
		// TODO Auto-generated method stub
		return new String[]{objeto.getNome(), objeto.getDescricao()};
	}

}
