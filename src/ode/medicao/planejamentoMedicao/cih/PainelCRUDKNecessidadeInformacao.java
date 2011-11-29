package ode.medicao.planejamentoMedicao.cih;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.medicao.planejamentoMedicao.cci.CtrlKNecessidadeInformacaoCRUD;
import ode.medicao.planejamentoMedicao.cdp.KNecessidadeInformacao;

@Component
public class PainelCRUDKNecessidadeInformacao extends PainelCRUD<KNecessidadeInformacao>{

	@Autowired
	CtrlKNecessidadeInformacaoCRUD controlador;
	
	@Override
	public ListagemSimples<KNecessidadeInformacao> definirListagem() {
		return new ListagemKNecessidadeInformacao();
	}

}
