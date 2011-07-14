package ode.conhecimento.processo.Cih;

import ode.conhecimento.processo.Cdp.KRecursoHardware;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.PainelCRUD;


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

