package ode.conhecimento.processo.Cih;

import ode.conhecimento.processo.Cdp.KProcesso;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.PainelCRUD;

public class PainelCrudKProcesso extends PainelCRUD<KProcesso>{

	@Override
	public ListagemSimples<KProcesso> definirListagem() {
		ListagemKProcesso listagem = new ListagemKProcesso();
		return listagem;
	}
}
