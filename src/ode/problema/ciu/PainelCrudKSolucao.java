package ode.problema.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.problema.cdp.KSolucao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class PainelCrudKSolucao extends PainelCRUD<KSolucao> {

	
	private static final long serialVersionUID = -5911704315279214771L;
	@Autowired 
	CtrlKSolucaoCRUD ctrlcrudksolucao;

	@Override
	public ListagemSimples<KSolucao> definirListagem() {
		return new ListagemKSolucao();
	}

}
