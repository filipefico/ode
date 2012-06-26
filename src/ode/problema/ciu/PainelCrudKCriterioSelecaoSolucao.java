package ode.problema.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.problema.cdp.KCriterioSelecaoSolucao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




@Component
public class PainelCrudKCriterioSelecaoSolucao extends PainelCRUD<KCriterioSelecaoSolucao> {
	
private static final long serialVersionUID = 7727244187095004800L;
@Autowired 
CtrlKCriterioSelecaoSolucaoCRUD ctrlcrudkcriterioselecaosolucao;

@Override
public ListagemSimples<KCriterioSelecaoSolucao> definirListagem() {
	return new ListagemKCriterioSelecaoSolucao();
}

}
