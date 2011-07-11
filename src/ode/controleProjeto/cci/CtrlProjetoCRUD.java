package ode.controleProjeto.cci;

import nucleo.comuns.aplicacao.NucleoAplCadastroBase;
import nucleo.comuns.crud.controlador.CtrlCRUD;
import nucleo.comuns.crud.visao.FormularioDadosCRUD;
import nucleo.comuns.crud.visao.PainelCRUD;
import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgt.AplCadastrarProjeto;
import ode.controleProjeto.cih.FormDadosProjeto;
import ode.controleProjeto.cih.PainelCRUDProjeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller(CtrlProjetoCRUD.NOME)
public class CtrlProjetoCRUD extends CtrlCRUD<Projeto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final static String NOME = "CtrlProjetoCRUD";
	
	@Autowired
	private AplCadastrarProjeto aplCadastrarProjeto;

	@Override
	public String definirTituloJanelaDados() {
		return "Projeto";
	}

	@Override
	public NucleoAplCadastroBase<Projeto> definirNucleoAplCadastroBase() {
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
