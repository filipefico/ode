package ode.medicao.planejamentoMedicao.cih;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ode.medicao.planejamentoMedicao.cci.CtrlKObjetivoSoftwareCRUD;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoSoftware;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;


@Component
public class PainelCRUDKObjetivoSoftware extends PainelCRUD<KObjetivoSoftware>{

	@Autowired
	CtrlKObjetivoSoftwareCRUD ctrl;
	
	@Override
	public ListagemSimples<KObjetivoSoftware> definirListagem() {
		return new ListagemKObjetivoSoftware();
	}

}
