package ode.conhecimento.processo.Cih;

import nucleo.comuns.crud.visao.PainelCRUD;
import nucleo.comuns.visao.listagem.ListagemSimples;
import ode.conhecimento.processo.Cdp.KCategoriaProcesso;
import ode.conhecimento.processo.Cih.ListagemCategoriaProcesso;

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
