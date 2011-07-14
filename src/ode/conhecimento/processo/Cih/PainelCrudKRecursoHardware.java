package ode.conhecimento.processo.Cih;

import nucleo.comuns.crud.visao.PainelCRUD;
import nucleo.comuns.visao.listagem.ListagemSimples;
import ode.conhecimento.processo.Cdp.KRecursoHardware;


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

