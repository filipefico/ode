package ode.controleProjeto.cih;

import ode.controleProjeto.cdp.Projeto;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.PainelCRUD;

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
