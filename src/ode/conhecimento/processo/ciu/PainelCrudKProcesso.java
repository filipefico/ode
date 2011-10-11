package ode.conhecimento.processo.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KProcesso;

public class PainelCrudKProcesso extends PainelCRUD<KProcesso>{

	@Override
	public ListagemSimples<KProcesso> definirListagem() {
		ListagemKProcesso listagem = new ListagemKProcesso();
		return listagem;
	}
}
