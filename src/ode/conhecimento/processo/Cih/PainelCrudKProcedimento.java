package ode.conhecimento.processo.Cih;

import ode.conhecimento.processo.Cdp.KAtividade;
import ode.conhecimento.processo.Cdp.KDominioAplicacao;
import ode.conhecimento.processo.Cdp.KProcedimento;
import ode.conhecimento.processo.Cdp.KRecursoHardware;
import ode.conhecimento.processo.Cdp.KRecursoHumano;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.PainelCRUD;

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

