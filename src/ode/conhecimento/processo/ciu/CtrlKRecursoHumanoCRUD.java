package ode.conhecimento.processo.ciu;

import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.conhecimento.processo.cgt.AplCadastrarKRecursoHumano;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlKRecursoHumanoCRUD extends CtrlCRUD<KRecursoHumano> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void iniciar() {
		super.iniciar();
	}
	
	@Autowired
	private AplCadastrarKRecursoHumano aplCadastrarKRecursoHumano;

	//lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public AplCRUD<KRecursoHumano> definirAplCRUD() {
		return aplCadastrarKRecursoHumano;
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudKRecursoHumano();
		
	}


	@Override
	public KRecursoHumano factoryObjetoDados() {
		return new KRecursoHumano();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosKRecursoHumano();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Recurso Humano";
	}
	
	@Override
	public String definirTituloJanelaPrincipal() {
		return "Recursos Humanos";
	}
	



}
