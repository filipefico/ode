package ode.conhecimento.processo.ciu;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;
import ode.conhecimento.processo.cdp.TipoKArtefato;

public class ListagemTipoKArtefato extends ListagemSimples<TipoKArtefato>{


	/**
	 * 
	 */
	private static final long serialVersionUID = 8802123889934989750L;

	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome", "nome", "100px"));
		colunas.add(new NucleoListHeader("Descricao", "descricao", "300px"));
		return colunas;
	}

	@Override
	protected String[] recuperarDadosObjeto(TipoKArtefato objeto) {
		return new String[] { objeto.getNome(), objeto.getDescricao() };
	}

}
