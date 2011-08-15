package ode.controleProjeto.cci;

import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgt.AplCadastrarProjeto;
import ode.controleProjeto.cih.FormDadosProjeto;
import ode.controleProjeto.cih.PainelCRUDProjeto;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cgt.AplBase;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.PainelCRUD;

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
	public AplBase<Projeto> definirNucleoAplCadastroBase() {
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
