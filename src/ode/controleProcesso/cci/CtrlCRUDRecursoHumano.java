package ode.controleProcesso.cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode.controleProcesso.cdp.RecursoHumano;
import ode.controleProcesso.cgt.AplCadastrarRecursoHumano;
import ode.controleProcesso.cih.FormDadosRecursoHumano;
import ode.controleProcesso.cih.PainelCRUDRecursoHumano;
import ode.nucleo.cgt.NucleoAplCadastroBase;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.PainelCRUD;

@Controller(CtrlCRUDRecursoHumano.NOME)
public class CtrlCRUDRecursoHumano extends CtrlCRUD<RecursoHumano> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String NOME = "CtrlCRUDRecursoHumano";
	
	@Autowired
	private AplCadastrarRecursoHumano aplCadastrarRecursoHumano;

	@Override
	public String definirTituloJanelaDados() {
		return "Recurso Humano";
	}

	@Override
	public NucleoAplCadastroBase<RecursoHumano> definirNucleoAplCadastroBase() {
		return this.aplCadastrarRecursoHumano;
	}

	@Override
	public PainelCRUD<RecursoHumano> definirPainelCRUD() {
		return new PainelCRUDRecursoHumano();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Recursos Humanos";
	}

	@Override
	public FormularioDadosCRUD<RecursoHumano> definirFormularioCadastro() {
		return new FormDadosRecursoHumano();
	}

	@Override
	public RecursoHumano factoryObjetoDados() {
		return new RecursoHumano();
	}

}
