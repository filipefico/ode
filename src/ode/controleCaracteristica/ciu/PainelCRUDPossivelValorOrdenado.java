package ode.controleCaracteristica.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.controleCaracteristica.cdp.PossivelValorOrdenado;

public class PainelCRUDPossivelValorOrdenado extends PainelCRUD<PossivelValorOrdenado>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L; 

	@Override
	public ListagemSimples<PossivelValorOrdenado> definirListagem() {
		ListagemPossivelValorOrdenado listagem = new ListagemPossivelValorOrdenado();
		return listagem;
	}
}