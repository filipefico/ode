package ode.conhecimento.processo.Cih;

import ode.conhecimento.processo.Cdp.KAtividade;
import ode.conhecimento.processo.Cdp.KDominioAplicacao;
import ode.conhecimento.processo.Cdp.KRecursoHumano;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.PainelCRUD;

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

