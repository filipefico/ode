package ode.conhecimento.processo.Cih;

import java.util.ArrayList;
import java.util.List;

import ode.conhecimento.processo.Cdp.KArtefato;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.NucleoListHeader;

public class ListagemKArtefato extends ListagemSimples<KArtefato> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -14597618633910372L;

	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome", "nome", "100px"));
		colunas.add(new NucleoListHeader("Descricao", "descricao", "300px"));
		return colunas;
	}

	@Override
	protected String[] recuperarDadosObjeto(KArtefato objeto) {
		return new String[] { objeto.getNome(), objeto.getDescricao() };
	}

}
