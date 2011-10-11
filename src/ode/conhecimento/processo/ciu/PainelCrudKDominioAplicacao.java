package ode.conhecimento.processo.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KDominioAplicacao;

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

