package ode.processoPadrao.Cih;

import nucleo.comuns.crud.visao.PainelCRUD;

import nucleo.comuns.visao.paginacao.ListagemPaginada;
import ode.processoPadrao.Cih.ListagemProcessoPadrao;
import ode.processoPadrao.Cdp.CompPP;

public class PainelCrudProcessoPadrao extends PainelCRUD<CompPP> {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2044602186469791574L;

	@Override
	public ListagemPaginada<CompPP> definirListagem() {
		ListagemProcessoPadrao listagem = new ListagemProcessoPadrao();
		return listagem;
	}

	

}
