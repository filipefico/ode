package ode.uml.cih;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.uml.cdp.CasoUso;

public class PainelCrudCasoUso extends PainelCRUD<CasoUso> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ListagemSimples<CasoUso> definirListagem() {
		ListagemCasoUso listagem = new ListagemCasoUso();
		return listagem;
	}

}
