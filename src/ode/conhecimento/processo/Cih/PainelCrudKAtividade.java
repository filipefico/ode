package ode.conhecimento.processo.Cih;

import nucleo.comuns.crud.visao.PainelCRUD;
import nucleo.comuns.visao.listagem.ListagemSimples;
import ode.conhecimento.processo.Cdp.KAtividade;
import ode.conhecimento.processo.Cdp.KDominioAplicacao;

public class PainelCrudKAtividade extends PainelCRUD<KAtividade> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L;

	@Override
	public ListagemSimples<KAtividade> definirListagem() {
		ListagemKAtividade listagem = new ListagemKAtividade();
		return listagem;
	}

	

}

