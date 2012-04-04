package ode.uml.cih;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.uml.cdp.Classe;

public class PainelCrudClasse extends PainelCRUD<Classe>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L; 

	@Override
	public ListagemSimples<Classe> definirListagem() {
		ListagemClasse listagem = new ListagemClasse();
		return listagem;
	}
}
