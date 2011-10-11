package ode.controleProjeto.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.controleProjeto.cdp.Projeto;

public class PainelCRUDProjeto extends PainelCRUD<Projeto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ListagemSimples<Projeto> definirListagem() {
		ListagemProjeto listagem = new ListagemProjeto();
		return listagem;
	}

}
