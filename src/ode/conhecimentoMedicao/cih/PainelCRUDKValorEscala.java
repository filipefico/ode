package ode.conhecimentoMedicao.cih;

import org.springframework.stereotype.Component;

import ode.conhecimentoMedicao.cdp.KValorEscala;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

@Component
public class PainelCRUDKValorEscala extends PainelCRUD<KValorEscala> {

	@Override
	public ListagemSimples<KValorEscala> definirListagem() {
		return new ListagemKValorEscala();
	}

}
