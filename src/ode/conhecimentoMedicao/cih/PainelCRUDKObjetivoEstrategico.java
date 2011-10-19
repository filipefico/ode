package ode.conhecimentoMedicao.cih;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ode.conhecimentoMedicao.cci.CtrlKObjetivoEstrategicoCRUD;
import ode.conhecimentoMedicao.cdp.KObjetivoEstrategico;
import ode.conhecimentoMedicao.cgt.AplCadastrarKObjetivoEstrategico;
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
