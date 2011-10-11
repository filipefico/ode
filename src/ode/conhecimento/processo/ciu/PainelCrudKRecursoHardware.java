package ode.conhecimento.processo.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KRecursoHardware;


public class PainelCrudKRecursoHardware extends PainelCRUD<KRecursoHardware> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L;

	@Override
	public ListagemSimples<KRecursoHardware> definirListagem() {
		ListagemKRecursoHardware listagem = new ListagemKRecursoHardware();
		return listagem;
	}

	

}

