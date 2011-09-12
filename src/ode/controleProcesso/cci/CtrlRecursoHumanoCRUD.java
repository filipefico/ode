package ode.controleProcesso.cci;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode.conhecimento.processo.Cdp.KRecursoHumano;
import ode.conhecimento.processo.Cgt.AplCadastrarKRecursoHumano;
import ode.controleProcesso.cdp.RecursoHumano;
import ode.controleProcesso.cgt.AplCadastrarRecursoHumano;
import ode.controleProcesso.cih.FormDadosRecursoHumano;
import ode.controleProcesso.cih.PainelCRUDRecursoHumano;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cgt.AplBase;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.PainelCRUD;

@Controller(CtrlRecursoHumanoCRUD.NOME)
public class CtrlRecursoHumanoCRUD extends CtrlCRUD<RecursoHumano> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String NOME = "CtrlCRUDRecursoHumano";
	
	@Autowired
	private AplCadastrarRecursoHumano aplCadastrarRecursoHumano;
	
	@Autowired
	private AplCadastrarKRecursoHumano aplCadastrarKRecursoHumano;

	@Override
	public String definirTituloJanelaDados() {
		return "Recurso Humano";
	}

	@Override
	public AplBase<RecursoHumano> definirNucleoAplCadastroBase() {
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

	public AplCadastrarKRecursoHumano getAplCadastrarKRecursoHumano() {
		return aplCadastrarKRecursoHumano;
	}

	public void setAplCadastrarKRecursoHumano(
			AplCadastrarKRecursoHumano aplCadastrarKRecursoHumano) {
		this.aplCadastrarKRecursoHumano = aplCadastrarKRecursoHumano;
	}
	
	public Collection<KRecursoHumano> listarKRecursosHumanos() {
		return getAplCadastrarKRecursoHumano().recuperarTodos();
	}

}
