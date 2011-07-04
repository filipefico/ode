package ode.conhecimento.processo.Cih;

import java.util.ArrayList;
import java.util.List;

import nucleo.comuns.visao.listagem.ListagemSimples;
import nucleo.comuns.visao.paginacao.NucleoListHeader;
import ode.conhecimento.processo.Cdp.KAtividade;
import ode.conhecimento.processo.Cdp.KDominioAplicacao;


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
