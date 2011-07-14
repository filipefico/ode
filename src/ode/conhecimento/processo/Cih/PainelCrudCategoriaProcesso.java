package ode.conhecimento.processo.Cih;

import ode.conhecimento.processo.Cdp.KCategoriaProcesso;
import ode.conhecimento.processo.Cih.ListagemCategoriaProcesso;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.PainelCRUD;

public class PainelCrudCategoriaProcesso extends PainelCRUD<KCategoriaProcesso>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L; 

	@Override
	public ListagemSimples<KCategoriaProcesso> definirListagem() {
		ListagemCategoriaProcesso listagem = new ListagemCategoriaProcesso();
		return listagem;
	}
}
