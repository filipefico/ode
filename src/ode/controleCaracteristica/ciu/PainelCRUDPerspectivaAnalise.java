package ode.controleCaracteristica.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.controleCaracteristica.cdp.PerspectivaAnalise;

public class PainelCRUDPerspectivaAnalise extends PainelCRUD<PerspectivaAnalise>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L; 

	@Override
	public ListagemSimples<PerspectivaAnalise> definirListagem() {
		ListagemPerspectivaAnalise listagem = new ListagemPerspectivaAnalise();
		return listagem;
	}
}
