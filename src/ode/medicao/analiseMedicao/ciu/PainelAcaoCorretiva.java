package ode.medicao.analiseMedicao.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.medicao.analiseMedicao.cdp.AcaoCorretiva;

public class PainelAcaoCorretiva extends PainelCRUD<AcaoCorretiva>{

	@Override
	public ListagemSimples<AcaoCorretiva> definirListagem() {
		return new ListagemAcaoCorretiva();
	}

}
