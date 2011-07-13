package ode.conhecimento.processo.Cih;

import nucleo.comuns.visao.listagem.ListagemSimples;
import ode.conhecimento.processo.Cdp.KAtividade;
import ode.conhecimento.processo.Cdp.KDominioAplicacao;
import ode.conhecimento.processo.Cdp.KRecursoHardware;
import ode.conhecimento.processo.Cdp.KRecursoHumano;
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

