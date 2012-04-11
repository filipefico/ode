package ode.gerenciaConhecimento.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.gerenciaConhecimento.cdp.Tema;

public class PainelCRUDTema extends PainelCRUD<Tema> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ListagemSimples<Tema> definirListagem() {
		// TODO Auto-generated method stub
		return new ListagemTema();
	}

}
