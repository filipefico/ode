package ode._controleFerramenta.cih;

import ode._controleFerramenta.cdp.FerramentaSoftware;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.PainelCRUD;

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
