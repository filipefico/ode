package ode.problema.ciu;

import java.util.Collection;

import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.problema.cdp.KCategoriaProblema;
import ode.problema.cdp.KProblema;
import ode.problema.cgt.AplCadastrarKCategoriaProblema;
import ode.problema.cgt.AplCadastrarKProblema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;



@Controller (CtrlKProblemaCRUD.Nome)
public class CtrlKProblemaCRUD extends CtrlCRUD<KProblema>  {

	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AplCadastrarKProblema aplCadastrarKProblema;
	
	@Autowired
	private AplCadastrarKCategoriaProblema aplCadastrarKCategoriaProblema;
	
	
	public static final String Nome = "CtrlCRUDKProblema";	


	private String titulo = "CadastroProblema";

	@Override
	public String definirTituloJanelaDados() {
		// TODO Auto-generated method stub
		return titulo;
	}

	@Override
	public AplCRUD<KProblema> definirAplCRUD() {
		// TODO Auto-generated method stub
		return aplCadastrarKProblema;
	}

	@Override
	public PainelCRUD<KProblema> definirPainelCRUD() {
		// TODO Auto-generated method stub
		return new PainelCrudKProblema();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		// TODO Auto-generated method stub
		return titulo;
	}

	@Override
	public FormularioDadosCRUD<KProblema> definirFormularioCadastro() {
		// TODO Auto-generated method stub
		return new FormDadosKProblema();
	}

	@Override
	public KProblema factoryObjetoDados() {
		// TODO Auto-generated method stub
		return new KProblema();
	}
	
	public AplCadastrarKCategoriaProblema getAplCadastrarKCategoriaProblema() {
		return aplCadastrarKCategoriaProblema;
	}

	public void setAplCadastrarKCategoriaProblema(
			AplCadastrarKCategoriaProblema aplCadastrarKCategoriaProblema) {
		this.aplCadastrarKCategoriaProblema= aplCadastrarKCategoriaProblema;
	}
	
	public Collection<KCategoriaProblema> listarKCategoriaProblema() {
		return getAplCadastrarKCategoriaProblema().recuperarTodos();
	}


	

}
