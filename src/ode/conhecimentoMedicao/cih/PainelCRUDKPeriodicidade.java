package ode.conhecimentoMedicao.cih;

import ode.conhecimentoMedicao.cdp.KPeriodicidade;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

public class PainelCRUDKPeriodicidade extends PainelCRUD<KPeriodicidade> {

	@Override
	public ListagemSimples<KPeriodicidade> definirListagem() {
		return new ListagemKPeriodicidade();
	}

}
