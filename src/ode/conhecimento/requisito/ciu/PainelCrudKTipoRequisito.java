package ode.conhecimento.requisito.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.requisito.cdp.KTipoRequisito;

public class PainelCrudKTipoRequisito extends PainelCRUD<KTipoRequisito>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L; 

	@Override
	public ListagemSimples<KTipoRequisito> definirListagem() {
		ListagemKTipoRequisito listagem = new ListagemKTipoRequisito();
		return listagem;
	}
}
