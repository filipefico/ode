package ode.conhecimento.processo.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KArtefato;

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
