package ode.medicao.planejamentoMedicao.cih;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ode.medicao.planejamentoMedicao.cci.CtrlObjetivoSoftwareCRUD;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoSoftware;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;


@Component
public class PainelCRUDObjetivoSoftware extends PainelCRUD<ObjetivoSoftware>{

	@Autowired
	CtrlObjetivoSoftwareCRUD ctrl;
	
	@Override
	public ListagemSimples<ObjetivoSoftware> definirListagem() {
		return new ListagemObjetivoSoftware();
	}

}
