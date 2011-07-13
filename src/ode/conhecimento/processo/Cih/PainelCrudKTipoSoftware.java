package ode.conhecimento.processo.Cih;

import nucleo.comuns.visao.listagem.ListagemSimples;
import ode.conhecimento.processo.Cdp.KTipoSoftware;
import ode.nucleo.crud.cih.PainelCRUD;

public class PainelCrudKTipoSoftware extends PainelCRUD<KTipoSoftware> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L;

	@Override
	public ListagemSimples<KTipoSoftware> definirListagem() {
		ListagemKTipoSoftware listagem = new ListagemKTipoSoftware();
		return listagem;
	}

	

}
