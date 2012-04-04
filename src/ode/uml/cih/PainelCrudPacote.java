package ode.uml.cih;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.uml.cdp.Pacote;

public class PainelCrudPacote extends PainelCRUD<Pacote>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L; 

	@Override
	public ListagemSimples<Pacote> definirListagem() {
		ListagemPacote listagem = new ListagemPacote();
		return listagem;
	}
}
