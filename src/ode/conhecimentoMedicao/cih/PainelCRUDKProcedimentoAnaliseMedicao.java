package ode.conhecimentoMedicao.cih;

import ode.conhecimentoMedicao.cdp.KProcedimentoAnaliseMedicao;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

public class PainelCRUDKProcedimentoAnaliseMedicao extends PainelCRUD<KProcedimentoAnaliseMedicao>{

	@Override
	public ListagemSimples<KProcedimentoAnaliseMedicao> definirListagem() {
		return new ListagemKProcedimentoAnaliseMedicao();
	}

}
