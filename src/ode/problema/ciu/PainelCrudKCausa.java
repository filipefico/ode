package ode.problema.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.problema.cdp.KCausa;

import org.springframework.stereotype.Component;





@Component
public class PainelCrudKCausa extends PainelCRUD<KCausa> {

	
	private static final long serialVersionUID = 1L;

	@Override
	public ListagemSimples<KCausa> definirListagem() {
		// TODO Auto-generated method stub
		return new ListagemKCausa();
	}

}
