package ode.controleProjeto.cih;

import nucleo.comuns.crud.visao.PainelCRUD;
import nucleo.comuns.visao.listagem.ListagemSimples;
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
