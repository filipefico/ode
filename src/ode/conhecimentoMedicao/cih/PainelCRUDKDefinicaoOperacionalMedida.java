package ode.conhecimentoMedicao.cih;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimentoMedicao.cdp.KDefinicaoOperacionalMedida;

public class PainelCRUDKDefinicaoOperacionalMedida extends PainelCRUD<KDefinicaoOperacionalMedida>{

	@Override
	public ListagemSimples<KDefinicaoOperacionalMedida> definirListagem() {
		return new ListagemKDefinicaoOperacionalMedida();
	}

}
