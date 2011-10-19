package ode.conhecimentoMedicao.cih;

import org.springframework.stereotype.Component;

import ode.conhecimentoMedicao.cdp.KTipoEntidadeMensuravel;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

@Component
public class PainelCRUDKTipoEntidadeMensuravel extends
		PainelCRUD<KTipoEntidadeMensuravel> {

	@Override
	public ListagemSimples<KTipoEntidadeMensuravel> definirListagem() {
		return new ListagemKTipoEntidadeMensuravel();
	}

}
