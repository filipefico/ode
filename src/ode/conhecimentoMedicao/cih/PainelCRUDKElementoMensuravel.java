package ode.conhecimentoMedicao.cih;

import org.springframework.stereotype.Component;

import ode.conhecimentoMedicao.cdp.KElementoMensuravel;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

@Component
public class PainelCRUDKElementoMensuravel extends PainelCRUD<KElementoMensuravel>{

	@Override
	public ListagemSimples<KElementoMensuravel> definirListagem() {
		return new ListagemKElementoMensuravel();
	}

}
