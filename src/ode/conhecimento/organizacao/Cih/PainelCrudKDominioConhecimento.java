package ode.conhecimento.organizacao.Cih;

import ode.conhecimento.organizacao.Cdp.KDominioConhecimento;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.PainelCRUD;



public class PainelCrudKDominioConhecimento extends PainelCRUD<KDominioConhecimento>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L; 

	@Override
	public ListagemSimples<KDominioConhecimento> definirListagem() {
		ListagemKDominioConhecimento listagem = new ListagemKDominioConhecimento();
		return listagem;
	}
}
