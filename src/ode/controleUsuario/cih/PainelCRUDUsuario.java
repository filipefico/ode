package ode.controleUsuario.cih;

import ode.controleUsuario.cdp.Usuario;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.PainelCRUD;

public class PainelCRUDUsuario extends PainelCRUD<Usuario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ListagemSimples<Usuario> definirListagem() {
		ListagemUsuario listagem = new ListagemUsuario();
		return listagem;
	}

}
