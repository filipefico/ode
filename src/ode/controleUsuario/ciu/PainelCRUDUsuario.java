package ode.controleUsuario.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.controleUsuario.cdp.Usuario;

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
