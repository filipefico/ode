package ode.conhecimento.requisito.Cci;

import ode.conhecimento.requisito.Cdp.KTipoRequisito;
import ode.conhecimento.requisito.Cgt.AplCadastrarKTipoRequisito;
import ode.conhecimento.requisito.Cih.FormDadosKTipoRequisito;
import ode.conhecimento.requisito.Cih.PainelCrudKTipoRequisito;
import ode.nucleo.cgt.NucleoAplCadastroBase;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.PainelCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlKTipoRequisitoCRUD extends CtrlCRUD<KTipoRequisito> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@Autowired
	private AplCadastrarKTipoRequisito aplCadastrarKTipoRequisito;

	@Override
	public void iniciar() {
		super.iniciar();

	}

	// lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public NucleoAplCadastroBase definirNucleoAplCadastroBase() {
		return aplCadastrarKTipoRequisito;
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudKTipoRequisito();

	}

	@Override
	public KTipoRequisito factoryObjetoDados() {
		return new KTipoRequisito();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosKTipoRequisito();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Tipo de Requisito";
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Cadastro de Tipo de Requisito";
	}
}
