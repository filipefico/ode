package ode.conhecimento.processo.Cih;

import ode.conhecimento.processo.Cdp.KCategoriaProcesso;
import ode.conhecimento.processo.Cih.ListagemKCategoriaProcesso;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.PainelCRUD;

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
