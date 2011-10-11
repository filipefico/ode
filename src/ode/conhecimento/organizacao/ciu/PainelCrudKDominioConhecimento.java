package ode.conhecimento.organizacao.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.organizacao.cdp.KDominioConhecimento;



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
