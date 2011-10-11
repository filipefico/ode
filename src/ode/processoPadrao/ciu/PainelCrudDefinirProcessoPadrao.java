package ode.processoPadrao.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.processoPadrao.cdp.CompPP;

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
