package ode.controleUsuario.cih;

import ode.controleUsuario.cdp.NucleoUserDetails;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.PainelCRUD;

public class UsuarioPainelCRUD extends PainelCRUD<NucleoUserDetails> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ListagemSimples<NucleoUserDetails> definirListagem() {
		UsuarioListagemSimples listagem = new UsuarioListagemSimples();
		return listagem;
	}

}
