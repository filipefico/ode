package ode.conhecimento.processo.Cih;

import ode.conhecimento.processo.Cdp.KArtefato;
import ode.nucleo.crud.cih.PainelCRUD;
import nucleo.comuns.visao.listagem.ListagemSimples;

public class PainelCrudKArtefato extends PainelCRUD<KArtefato>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4310883300114786337L;

	@Override
	public ListagemSimples<KArtefato> definirListagem() {
		ListagemKArtefato listagem = new ListagemKArtefato();
		return listagem;
	}

}
