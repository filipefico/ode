package ode.problema.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.problema.cdp.KProblema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




@Component
public class PainelCrudKProblema extends PainelCRUD<KProblema> {
	
	private static final long serialVersionUID = 6087627115143798193L;
	@Autowired 
	CtrlKProblemaCRUD ctrlcrudkproblema;
	
	@Override
	public ListagemSimples<KProblema> definirListagem() {
		// TODO Auto-generated method stub
		return new ListagemKProblema();
	}

}
