package ode.conhecimento.processo.Cih;

import nucleo.comuns.crud.visao.PainelCRUD;
import nucleo.comuns.visao.paginacao.ListagemPaginada;
import ode.conhecimento.processo.Cdp.KTipoSoftware;

public class PainelCrudKTipoSoftware extends PainelCRUD<KTipoSoftware> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L;

	@Override
	public ListagemPaginada<KTipoSoftware> definirListagem() {
		ListagemKTipoSoftware listagem = new ListagemKTipoSoftware();
		return listagem;
	}

	

}
