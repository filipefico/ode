package ode.conhecimento.processo.Cci;

import ode.conhecimento.processo.Cdp.KRecursoHumano;
import ode.conhecimento.processo.Cgt.AplCadastrarKRecursoHumano;
import ode.conhecimento.processo.Cih.FormDadosKRecursoHumano;
import ode.conhecimento.processo.Cih.PainelCrudKRecursoHumano;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cgt.AplBase;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.PainelCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zkplus.spring.SpringUtil;

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
	public AplBase definirNucleoAplCadastroBase() {
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
