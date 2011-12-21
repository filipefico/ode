package ode.medicao.planejamentoMedicao.cih;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ode.medicao.planejamentoMedicao.cci.CtrlObjetivoMedicaoCRUD;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

@Component
public class PainelCRUDObjetivoMedicao extends PainelCRUD<ObjetivoMedicao>{
	
	
	@Autowired
	CtrlObjetivoMedicaoCRUD controlador;
	
	@Override
	public ListagemSimples<ObjetivoMedicao> definirListagem() {
		return new ListagemObjetivoMedicao();
	}

}
