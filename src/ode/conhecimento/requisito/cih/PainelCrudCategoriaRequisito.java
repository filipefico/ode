package ode.conhecimento.requisito.cih;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.requisito.cdp.CategoriaRequisito;
import ode.conhecimento.requisito.cih.ListagemCategoriaRequisito;

public class PainelCrudCategoriaRequisito extends PainelCRUD<CategoriaRequisito>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L; 

	@Override
	public ListagemSimples<CategoriaRequisito> definirListagem() {
		ListagemCategoriaRequisito listagem = new ListagemCategoriaRequisito();
		return listagem;
	}
}
