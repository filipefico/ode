package ode.conhecimento.processo.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KProcedimento;

public class PainelCrudKProcedimento extends PainelCRUD<KProcedimento> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L;

	@Override
	public ListagemSimples<KProcedimento> definirListagem() {
		ListagemKProcedimento listagem = new ListagemKProcedimento();
		return listagem;
	}

	

}

