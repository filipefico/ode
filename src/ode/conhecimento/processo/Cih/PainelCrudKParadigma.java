package ode.conhecimento.processo.Cih;

import ode.conhecimento.processo.Cdp.KParadigma;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.PainelCRUD;

public class PainelCrudKParadigma extends PainelCRUD<KParadigma>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4310883300114786337L;

	@Override
	public ListagemSimples<KParadigma> definirListagem() {
		ListagemKParadigma listagem = new ListagemKParadigma();
		return listagem;
	}

}
