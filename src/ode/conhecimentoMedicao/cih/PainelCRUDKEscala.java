package ode.conhecimentoMedicao.cih;

import org.springframework.stereotype.Component;

import ode.conhecimentoMedicao.cdp.KEscala;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

@Component
public class PainelCRUDKEscala extends PainelCRUD<KEscala>{

	@Override
	public ListagemSimples<KEscala> definirListagem() {
		return new ListagemKEscala();
	}

}
