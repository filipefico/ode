package ode.conhecimento.requisito.Cih;

import nucleo.comuns.crud.visao.PainelCRUD;
import nucleo.comuns.visao.listagem.ListagemSimples;
import ode.conhecimento.requisito.Cdp.KTipoRequisito;
import ode.conhecimento.requisito.Cih.ListagemKTipoRequisito;

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
