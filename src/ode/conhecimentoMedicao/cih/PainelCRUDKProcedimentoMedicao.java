package ode.conhecimentoMedicao.cih;

import ode.conhecimentoMedicao.cdp.KProcedimentoMedicao;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

public class PainelCRUDKProcedimentoMedicao extends PainelCRUD<KProcedimentoMedicao> {

	@Override
	public ListagemSimples<KProcedimentoMedicao> definirListagem() {
		return new ListagemKProcedimentoMedicao();
	}

}
