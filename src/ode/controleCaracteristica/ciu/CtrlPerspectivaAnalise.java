package ode.controleCaracteristica.ciu;

import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.controleCaracteristica.cdp.PerspectivaAnalise;
import ode.controleCaracteristica.cgt.AplCadastrarPerspectivaAnalise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlPerspectivaAnalise extends CtrlCRUD<PerspectivaAnalise> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@Autowired
	private AplCadastrarPerspectivaAnalise aplCadastrarPerspectivaAnalise;

	@Override
	public void iniciar() {
		super.iniciar();

	}
	

	// lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public AplCRUD<PerspectivaAnalise> definirAplCRUD() {
		return aplCadastrarPerspectivaAnalise;
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCRUDPerspectivaAnalise();

	}

	@Override
	public PerspectivaAnalise factoryObjetoDados() {
		return new PerspectivaAnalise();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosPerspectivaAnalise();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Perspectiva de Analise";
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Cadastro de Perspectivas de Analises";
	}
}
