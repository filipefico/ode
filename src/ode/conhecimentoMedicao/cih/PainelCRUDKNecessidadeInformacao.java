package ode.conhecimentoMedicao.cih;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ode.conhecimentoMedicao.cci.CtrlKNecessidadeInformacaoCRUD;
import ode.conhecimentoMedicao.cdp.KNecessidadeInformacao;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

@Component
public class PainelCRUDKNecessidadeInformacao extends PainelCRUD<KNecessidadeInformacao>{

	@Autowired
	CtrlKNecessidadeInformacaoCRUD controlador;
	
	@Override
	public ListagemSimples<KNecessidadeInformacao> definirListagem() {
		return new ListagemKNecessidadeInformacao();
	}

}
