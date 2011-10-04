package ode.conhecimento.organizacao.Cih;

import ode.conhecimento.organizacao.Cdp.KCompetencia;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.PainelCRUD;

public class PainelCRUDKCompetencia extends PainelCRUD<KCompetencia> {

	private static final long serialVersionUID = 1L;

	@Override
	public ListagemSimples<KCompetencia> definirListagem() {
		ListagemKCompetencia listagem = new ListagemKCompetencia();
		return listagem;
	}

}
