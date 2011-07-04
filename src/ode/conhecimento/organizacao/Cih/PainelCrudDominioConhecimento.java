package ode.conhecimento.organizacao.Cih;

import nucleo.comuns.crud.visao.PainelCRUD;
import nucleo.comuns.visao.listagem.ListagemSimples;
import ode.conhecimento.organizacao.Cdp.KDominioConhecimento;


public class PainelCrudDominioConhecimento extends PainelCRUD<KDominioConhecimento>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L; 

	@Override
	public ListagemSimples<KDominioConhecimento> definirListagem() {
		ListagemDominioConhecimento listagem = new ListagemDominioConhecimento();
		return listagem;
	}
}
