package ode.conhecimento.requisito.Cih;

import ode.conhecimento.requisito.Cdp.KTipoRequisito;
import ode.conhecimento.requisito.Cih.ListagemKTipoRequisito;
import ode.nucleo.crud.cih.ListagemSimples;
import ode.nucleo.crud.cih.PainelCRUD;

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
