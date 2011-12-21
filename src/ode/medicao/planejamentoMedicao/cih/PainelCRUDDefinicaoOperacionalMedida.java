package ode.medicao.planejamentoMedicao.cih;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.medicao.planejamentoMedicao.cdp.DefinicaoOperacionalMedida;

public class PainelCRUDDefinicaoOperacionalMedida extends PainelCRUD<DefinicaoOperacionalMedida>{

	@Override
	public ListagemSimples<DefinicaoOperacionalMedida> definirListagem() {
		return new ListagemDefinicaoOperacionalMedida();
	}

}
