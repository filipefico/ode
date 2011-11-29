package ode.medicao.planejamentoMedicao.cih;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ode.medicao.planejamentoMedicao.cci.CtrlKObjetivoEstrategicoCRUD;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoEstrategico;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

@Component
public class PainelCRUDKObjetivoEstrategico extends PainelCRUD<KObjetivoEstrategico>{
	
	
	@Autowired
	CtrlKObjetivoEstrategicoCRUD controlador;
	
	@Override
	public ListagemSimples<KObjetivoEstrategico> definirListagem() {
		return new ListagemKObjetivoEstrategico();
	}

}
