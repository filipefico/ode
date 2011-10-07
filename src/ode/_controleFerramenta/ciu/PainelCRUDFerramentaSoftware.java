package ode._controleFerramenta.ciu;

import ode._controleFerramenta.cdp.FerramentaSoftware;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

public class PainelCRUDFerramentaSoftware extends PainelCRUD<FerramentaSoftware> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ListagemSimples<FerramentaSoftware> definirListagem() {
		ListagemFerramentaSoftware listagem = new ListagemFerramentaSoftware();
		return listagem;
	}

}
