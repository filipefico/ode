package ode.conhecimento.processo.Cih;

import ode.conhecimento.processo.Cdp.KParadigma;
import nucleo.comuns.crud.visao.PainelCRUD;
import nucleo.comuns.visao.listagem.ListagemSimples;

public class PainelCrudKParadigma extends PainelCRUD<KParadigma>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4310883300114786337L;

	@Override
	public ListagemSimples<KParadigma> definirListagem() {
		ListagemKParadigma listagem = new ListagemKParadigma();
		return listagem;
	}

}