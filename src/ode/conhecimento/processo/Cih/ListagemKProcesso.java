package ode.conhecimento.processo.Cih;

import java.util.ArrayList;
import java.util.List;

import nucleo.comuns.visao.paginacao.ListagemPaginada;
import nucleo.comuns.visao.paginacao.NucleoListHeader;
import ode.conhecimento.processo.Cdp.KProcesso;

public class ListagemKProcesso extends ListagemPaginada<KProcesso> {

	@Override
	protected String[] recuperarDadosObjeto(KProcesso objeto) {
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
