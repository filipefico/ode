package ode.conhecimentoMedicao.cih;

import ode.conhecimentoMedicao.cdp.KMedida;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

public class PainelCRUDKMedida extends PainelCRUD<KMedida>{

	@Override
	public ListagemSimples<KMedida> definirListagem() {
		return new ListagemKMedida();
	}

}
