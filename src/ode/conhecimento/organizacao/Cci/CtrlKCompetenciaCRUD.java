package ode.conhecimento.organizacao.Cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode.conhecimento.organizacao.Cdp.KCompetencia;
import ode.conhecimento.organizacao.Cgt.AplCadastrarKCompetencia;
import ode.conhecimento.organizacao.Cih.FormDadosKCompetencia;
import ode.conhecimento.organizacao.Cih.PainelCRUDKCompetencia;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cgt.AplBase;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.PainelCRUD;

@Controller
public class CtrlKCompetenciaCRUD extends CtrlCRUD<KCompetencia> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AplCadastrarKCompetencia aplCadastrarKCompetencia;

	@Override
	public String definirTituloJanelaDados() {
		return "Competência";
	}
	
	@Override
	public String definirTituloJanelaPrincipal() {
		return "Competências";
	}

	@Override
	public AplBase<KCompetencia> definirNucleoAplCadastroBase() {
		return this.aplCadastrarKCompetencia;
	}

	@Override
	public PainelCRUD<KCompetencia> definirPainelCRUD() {
		return new PainelCRUDKCompetencia();
	}

	@Override
	public FormularioDadosCRUD<KCompetencia> definirFormularioCadastro() {
		return new FormDadosKCompetencia();
	}

	@Override
	public KCompetencia factoryObjetoDados() {
		return new KCompetencia();
	}

}
