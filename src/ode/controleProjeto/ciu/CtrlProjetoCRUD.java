package ode.controleProjeto.ciu;

import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgt.AplCadastrarProjeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlProjetoCRUD extends CtrlCRUD<Projeto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AplCadastrarProjeto aplCadastrarProjeto;

	@Override
	public String definirTituloJanelaDados() {
		return "Projeto";
	}

	@Override
	public AplCRUD<Projeto> definirAplCRUD() {
		return aplCadastrarProjeto;
	}

	@Override
	public PainelCRUD<Projeto> definirPainelCRUD() {
		return new PainelCRUDProjeto();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Projetos";
	}

	@Override
	public FormularioDadosCRUD<Projeto> definirFormularioCadastro() {
		return new FormDadosProjeto();
	}

	@Override
	public Projeto factoryObjetoDados() {
		return new Projeto();
	}

}
