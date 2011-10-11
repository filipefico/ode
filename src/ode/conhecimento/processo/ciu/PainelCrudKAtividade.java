package ode.conhecimento.processo.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KAtividade;

public class PainelCrudKAtividade extends PainelCRUD<KAtividade> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L;

	@Override
	public ListagemSimples<KAtividade> definirListagem() {
		ListagemKAtividade listagem = new ListagemKAtividade();
		return listagem;
	}

	

}

