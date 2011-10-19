package ode.conhecimentoMedicao.cih;

import ode.conhecimentoMedicao.cdp.KUnidadeMedida;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

public class PainelCRUDKUnidadeMedida extends PainelCRUD<KUnidadeMedida> {

	@Override
	public ListagemSimples<KUnidadeMedida> definirListagem() {
		return new ListagemKUnidadeMedida();
	}

}
