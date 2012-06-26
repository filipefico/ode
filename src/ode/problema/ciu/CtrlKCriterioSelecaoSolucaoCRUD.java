package ode.problema.ciu;



import java.util.Collection;

import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.problema.cdp.KCriterioSelecaoSolucao;

import ode.problema.cgt.AplCadastrarKCriterioSelecaoSolucao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;



@Controller (CtrlKCriterioSelecaoSolucaoCRUD.Nome)
public class CtrlKCriterioSelecaoSolucaoCRUD extends CtrlCRUD<KCriterioSelecaoSolucao> {

private static final long serialVersionUID = 7145413931399863360L;
public static final String Nome = "CtrlCRUDKCriterioSelecaoSolucao";

//@Autowired
//private AplCadastrarKValorCriterioSelecaoSolucao aplCadastrarKValorCriterioSelecaoSolucao;
@Autowired
public AplCadastrarKCriterioSelecaoSolucao aplCadastrarKCriterioSelecaoSolucao;

private String titulo = "CadastroCriterioSelecaoSolucao"; 


	@Override
	public String definirTituloJanelaDados() {
		// TODO Auto-generated method stub
		return titulo;
	}

	@Override
	public AplCRUD<KCriterioSelecaoSolucao> definirAplCRUD() {
		// TODO Auto-generated method stub
		return aplCadastrarKCriterioSelecaoSolucao ;
	}

	@Override
	public PainelCRUD<KCriterioSelecaoSolucao> definirPainelCRUD() {
		// TODO Auto-generated method stub
		return new PainelCrudKCriterioSelecaoSolucao();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		// TODO Auto-generated method stub
		return titulo;
	}

	@Override
	public FormularioDadosCRUD<KCriterioSelecaoSolucao> definirFormularioCadastro() {
		// TODO Auto-generated method stub
		return new FormDadosKCriterioSelecaoSolucao();
	}

	@Override
	public KCriterioSelecaoSolucao factoryObjetoDados() {
		// TODO Auto-generated method stub
		return new KCriterioSelecaoSolucao();
	}
	
//	public AplCadastrarKValorCriterioSelecaoSolucao getAplCadastrarKValorCategoriaProblema() {
//		return aplCadastrarKValorCriterioSelecaoSolucao;
///	}

//	public void setAplCadastrarKValorCriterioSelecaoSolucao(
//			AplCadastrarKValorCriterioSelecaoSolucao aplCadastrarKValorCriterioSelecaoSolucao) {
//		this.aplCadastrarKValorCriterioSelecaoSolucao = aplCadastrarKValorCriterioSelecaoSolucao;
//	}

//	public Collection<KValorCriterioSelecaoSolucao> listarKValorCriterioSelecaoSolucao() {
//		return getAplCadastrarKValorCategoriaProblema().recuperarTodos();
//	}

	
	

}
