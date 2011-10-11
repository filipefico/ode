package ode.conhecimento.processo.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KFerramentaSoftware;


public class PainelCrudKFerramentaSoftware extends PainelCRUD<KFerramentaSoftware>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L; 

	@Override
	public ListagemSimples<KFerramentaSoftware> definirListagem() {
		ListagemKFerramentaSoftware listagem = new ListagemKFerramentaSoftware();
		return listagem;
	}
}
