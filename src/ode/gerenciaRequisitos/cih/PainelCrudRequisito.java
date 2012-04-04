package ode.gerenciaRequisitos.cih;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.gerenciaRequisitos.cdp.Requisito;

public class PainelCrudRequisito extends PainelCRUD<Requisito> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ListagemSimples<Requisito> definirListagem() {
		ListagemRequisito listagem = new ListagemRequisito();
		return listagem;
	}

}
