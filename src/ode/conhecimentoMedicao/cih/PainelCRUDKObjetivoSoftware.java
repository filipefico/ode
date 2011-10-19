package ode.conhecimentoMedicao.cih;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ode.conhecimentoMedicao.cci.CtrlKObjetivoSoftwareCRUD;
import ode.conhecimentoMedicao.cdp.KObjetivoSoftware;
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
