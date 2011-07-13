package ode.processoPadrao.Cih;

import nucleo.comuns.visao.listagem.ListagemSimples;
import ode.nucleo.crud.cih.PainelCRUD;
import ode.processoPadrao.Cdp.CompPP;

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
