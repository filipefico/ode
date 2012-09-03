package ode.controleCaracteristica.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.controleCaracteristica.cdp.Caracteristica;

public class PainelCRUDCaracteristica extends PainelCRUD<Caracteristica>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L; 

	@Override
	public ListagemSimples<Caracteristica> definirListagem() {
		ListagemCaracteristica listagem = new ListagemCaracteristica();
		return listagem;
	}
}