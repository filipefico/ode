package ode.conhecimento.processo.Cih;

import ode.conhecimento.processo.Cdp.KFerramentaSoftware;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.PainelCRUD;


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
