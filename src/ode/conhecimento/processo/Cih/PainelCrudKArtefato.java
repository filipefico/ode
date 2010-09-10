package ode.conhecimento.processo.Cih;

import ode.conhecimento.processo.Cdp.KArtefato;
import nucleo.comuns.crud.visao.PainelCRUD;
import nucleo.comuns.visao.paginacao.ListagemPaginada;

public class PainelCrudKArtefato extends PainelCRUD<KArtefato>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4310883300114786337L;

	@Override
	public ListagemPaginada<KArtefato> definirListagem() {
		ListagemKArtefato listagem = new ListagemKArtefato();
		return listagem;
	}

}
