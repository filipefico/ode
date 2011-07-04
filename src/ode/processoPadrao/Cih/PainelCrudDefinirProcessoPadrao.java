package ode.processoPadrao.Cih;

import nucleo.comuns.crud.visao.PainelCRUD;

import nucleo.comuns.visao.listagem.ListagemSimples;
import ode.processoPadrao.Cih.ListagemDefinirProcessoPadrao;
import ode.processoPadrao.Cdp.CompPP;
import ode.processoPadrao.Cdp.InterfaceCompPP;

public class PainelCrudDefinirProcessoPadrao extends PainelCRUD<CompPP> {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2044602186469791574L;

	@Override
	public ListagemSimples<CompPP> definirListagem() {
		ListagemDefinirProcessoPadrao listagem = new ListagemDefinirProcessoPadrao();
		return listagem;
	}

	

}
