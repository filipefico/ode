package ode.conhecimento.processo.Cih;

import nucleo.comuns.crud.visao.PainelCRUD;
import nucleo.comuns.visao.paginacao.ListagemPaginada;
import ode.conhecimento.processo.Cdp.KFerramentaSoftware;


public class PainelCrudFerramentaSoftware extends PainelCRUD<KFerramentaSoftware>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L; 

	@Override
	public ListagemPaginada<KFerramentaSoftware> definirListagem() {
		ListagemFerramentaSoftware listagem = new ListagemFerramentaSoftware();
		return listagem;
	}
}
