package ode.conhecimentoMedicao.cih;

import ode.conhecimentoMedicao.cdp.KMetodoAnalitico;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

public class PainelCRUDKMetodoAnalitico extends PainelCRUD<KMetodoAnalitico> {

	@Override
	public ListagemSimples<KMetodoAnalitico> definirListagem() {
		return new ListagemKMetodoAnalitico();
	}

}
