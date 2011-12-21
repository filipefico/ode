package ode.medicao.planejamentoMedicao.cih;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ode.medicao.planejamentoMedicao.cci.CtrlObjetivoEstrategicoCRUD;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoEstrategico;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

@Component
public class PainelCRUDObjetivoEstrategico extends PainelCRUD<ObjetivoEstrategico>{
	
	
	@Autowired
	CtrlObjetivoEstrategicoCRUD controlador;
	
	@Override
	public ListagemSimples<ObjetivoEstrategico> definirListagem() {
		return new ListagemObjetivoEstrategico();
	}

}
