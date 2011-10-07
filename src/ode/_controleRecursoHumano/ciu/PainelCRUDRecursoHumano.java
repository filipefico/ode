package ode._controleRecursoHumano.ciu;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

public class PainelCRUDRecursoHumano extends PainelCRUD<RecursoHumano> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ListagemSimples<RecursoHumano> definirListagem() {
		ListagemRecursoHumano listagem = new ListagemRecursoHumano();
		return listagem;
	}

}
