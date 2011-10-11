package ode.conhecimento.processo.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KParadigma;

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
