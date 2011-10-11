package ode.conhecimento.requisito.ciu;

import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.requisito.cdp.KTipoRequisito;
import ode.conhecimento.requisito.cgt.AplCadastrarKTipoRequisito;

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
	public AplCRUD<KTipoRequisito> definirAplCRUD() {
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
