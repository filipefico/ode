package ode.conhecimento.organizacao.ciu;

import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.organizacao.cdp.KCompetencia;
import ode.conhecimento.organizacao.cgt.AplCadastrarKCompetencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
	public AplCRUD<KCompetencia> definirAplCRUD() {
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
