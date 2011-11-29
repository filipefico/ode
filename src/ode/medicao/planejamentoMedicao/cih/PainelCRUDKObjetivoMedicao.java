package ode.medicao.planejamentoMedicao.cih;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ode.medicao.planejamentoMedicao.cci.CtrlKObjetivoMedicaoCRUD;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoMedicao;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

@Component
public class PainelCRUDKObjetivoMedicao extends PainelCRUD<KObjetivoMedicao>{
	
	
	@Autowired
	CtrlKObjetivoMedicaoCRUD controlador;
	
	@Override
	public ListagemSimples<KObjetivoMedicao> definirListagem() {
		return new ListagemKObjetivoMedicao();
	}

}
