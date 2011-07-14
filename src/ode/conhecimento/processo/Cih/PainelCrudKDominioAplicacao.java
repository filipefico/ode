package ode.conhecimento.processo.Cih;

import ode.conhecimento.processo.Cdp.KDominioAplicacao;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.PainelCRUD;

public class PainelCrudKDominioAplicacao extends PainelCRUD<KDominioAplicacao> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L;

	@Override
	public ListagemSimples<KDominioAplicacao> definirListagem() {
		ListagemKDominioAplicacao listagem = new ListagemKDominioAplicacao();
		return listagem;
	}

	

}

