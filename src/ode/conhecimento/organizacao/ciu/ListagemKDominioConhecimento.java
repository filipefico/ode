package ode.conhecimento.organizacao.ciu;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;
import ode.conhecimento.organizacao.cdp.KDominioConhecimento;

public class ListagemKDominioConhecimento extends ListagemSimples<KDominioConhecimento> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3037716600332703360L;


	@Override
	protected String[] recuperarDadosObjeto(KDominioConhecimento objeto) {
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
