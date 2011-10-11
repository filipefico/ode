package ode.conhecimento.processo.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KCategoriaProcesso;

public class PainelCrudKCategoriaProcesso extends PainelCRUD<KCategoriaProcesso>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L; 

	@Override
	public ListagemSimples<KCategoriaProcesso> definirListagem() {
		ListagemKCategoriaProcesso listagem = new ListagemKCategoriaProcesso();
		return listagem;
	}
}
