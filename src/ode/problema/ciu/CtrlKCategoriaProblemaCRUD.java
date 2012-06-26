package ode.problema.ciu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.problema.cdp.KCategoriaProblema;
import ode.problema.cgt.AplCadastrarKCategoriaProblema;
import ode.problema.cgt.AplCadastrarKProblema;




@Controller (CtrlKCategoriaProblemaCRUD.Nome)
public class CtrlKCategoriaProblemaCRUD extends CtrlCRUD<KCategoriaProblema> {

	
	private static final long serialVersionUID = -3709726254669505985L;
	public static final String Nome = "CtrlCRUDKCategoriaProblema";	
	@Autowired
	AplCadastrarKCategoriaProblema aplCadastrarKCategoriaProblema;

	private String titulo = "CadastroCategoriaProblema";
	
	
	
	@Override
	public String definirTituloJanelaDados() {
		// TODO Auto-generated method stub
		return titulo;
	}

	@Override
	public AplCRUD<KCategoriaProblema> definirAplCRUD() {
		// TODO Auto-generated method stub
		return aplCadastrarKCategoriaProblema;
	}

	@Override
	public PainelCRUD<KCategoriaProblema> definirPainelCRUD() {
		// TODO Auto-generated method stub
		return new PainelCrudKCategoriaProblema();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		// TODO Auto-generated method stub
		return titulo;
	}

	@Override
	public FormularioDadosCRUD<KCategoriaProblema> definirFormularioCadastro() {
		// TODO Auto-generated method stub
		return new FormDadosKCategoriaProblema();
	}

	@Override
	public KCategoriaProblema factoryObjetoDados() {
		// TODO Auto-generated method stub
		return new KCategoriaProblema();
	}
	
	public AplCadastrarKCategoriaProblema getAplCadastrarKCategoriaProblema() {
		return aplCadastrarKCategoriaProblema;
	}

	public void setAplCadastrarKCategoriaProblema(
			AplCadastrarKProblema aplCadastrarKProblema) {
		this.aplCadastrarKCategoriaProblema = aplCadastrarKCategoriaProblema;
	}

	
/*	public Collection<KCategoriaProblema> listarKCategoriaProblema() {
		return getAplCadastrarKCategoriaProblema().recuperarTodos();
	}*/



}
