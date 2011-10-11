package ode.conhecimento.processo.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KTipoSoftware;

public class PainelCrudKTipoSoftware extends PainelCRUD<KTipoSoftware> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L;

	@Override
	public ListagemSimples<KTipoSoftware> definirListagem() {
		ListagemKTipoSoftware listagem = new ListagemKTipoSoftware();
		return listagem;
	}

	

}
