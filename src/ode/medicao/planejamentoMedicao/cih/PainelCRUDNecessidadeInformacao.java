package ode.medicao.planejamentoMedicao.cih;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.medicao.planejamentoMedicao.cci.CtrlNecessidadeInformacaoCRUD;
import ode.medicao.planejamentoMedicao.cdp.NecessidadeInformacao;

@Component
public class PainelCRUDNecessidadeInformacao extends PainelCRUD<NecessidadeInformacao>{

	@Autowired
	CtrlNecessidadeInformacaoCRUD controlador;
	
	@Override
	public ListagemSimples<NecessidadeInformacao> definirListagem() {
		return new ListagemNecessidadeInformacao();
	}

}
