package ode.conhecimentoMedicao.cih;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ode.conhecimentoMedicao.cci.CtrlKObjetivoEstrategicoCRUD;
import ode.conhecimentoMedicao.cci.CtrlKObjetivoMedicaoCRUD;
import ode.conhecimentoMedicao.cdp.KObjetivoEstrategico;
import ode.conhecimentoMedicao.cdp.KObjetivoMedicao;
import ode.conhecimentoMedicao.cgt.AplCadastrarKObjetivoEstrategico;
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
