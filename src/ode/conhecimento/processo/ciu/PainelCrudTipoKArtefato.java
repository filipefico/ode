package ode.conhecimento.processo.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.TipoKArtefato;

public class PainelCrudTipoKArtefato extends PainelCRUD<TipoKArtefato> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6782246290526691057L;

	@Override
	public ListagemSimples<TipoKArtefato> definirListagem() {
		ListagemTipoKArtefato listagem = new ListagemTipoKArtefato();
		return listagem;
	}

}
