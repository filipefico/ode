package ode.conhecimento.processo.Cih;

import nucleo.comuns.crud.visao.PainelCRUD;
import nucleo.comuns.visao.paginacao.ListagemPaginada;
import ode.conhecimento.processo.Cdp.KProcesso;

public class PainelCrudKProcesso extends PainelCRUD<KProcesso>{

	@Override
	public ListagemPaginada<KProcesso> definirListagem() {
		ListagemKProcesso listagem = new ListagemKProcesso();
		return listagem;
	}
}
