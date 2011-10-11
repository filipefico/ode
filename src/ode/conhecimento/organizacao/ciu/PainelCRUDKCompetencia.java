package ode.conhecimento.organizacao.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.organizacao.cdp.KCompetencia;

public class PainelCRUDKCompetencia extends PainelCRUD<KCompetencia> {

	private static final long serialVersionUID = 1L;

	@Override
	public ListagemSimples<KCompetencia> definirListagem() {
		ListagemKCompetencia listagem = new ListagemKCompetencia();
		return listagem;
	}

}
