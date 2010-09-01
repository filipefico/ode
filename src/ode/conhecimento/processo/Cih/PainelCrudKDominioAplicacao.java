package ode.conhecimento.processo.Cih;

import nucleo.comuns.crud.visao.PainelCRUD;
import nucleo.comuns.visao.paginacao.ListagemPaginada;
import ode.conhecimento.processo.Cdp.KDominioAplicacao;

public class PainelCrudKDominioAplicacao extends PainelCRUD<KDominioAplicacao> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L;

	@Override
	public ListagemPaginada<KDominioAplicacao> definirListagem() {
		ListagemKDominioAplicacao listagem = new ListagemKDominioAplicacao();
		return listagem;
	}

	

}

