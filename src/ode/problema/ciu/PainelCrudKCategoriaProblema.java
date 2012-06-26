package ode.problema.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.problema.cdp.KCategoriaProblema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class PainelCrudKCategoriaProblema extends PainelCRUD<KCategoriaProblema> {
	
	private static final long serialVersionUID = 5605634542456342413L;
	@Autowired 
	CtrlKCategoriaProblemaCRUD ctrlcrudkcategoriaproblema;
	
	@Override
	public ListagemSimples<KCategoriaProblema> definirListagem() {
		// TODO Auto-generated method stub
		return new ListagemKCategoriaProblema();
	}

}
