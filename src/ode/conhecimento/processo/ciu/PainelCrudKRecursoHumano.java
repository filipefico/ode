package ode.conhecimento.processo.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KRecursoHumano;

public class PainelCrudKRecursoHumano extends PainelCRUD<KRecursoHumano> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L;

	@Override
	public ListagemSimples<KRecursoHumano> definirListagem() {
		ListagemKRecursoHumano listagem = new ListagemKRecursoHumano();
		return listagem;
	}

	

}

