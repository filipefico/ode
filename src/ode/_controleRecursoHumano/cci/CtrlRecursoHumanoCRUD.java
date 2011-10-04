package ode._controleRecursoHumano.cci;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode.conhecimento.processo.Cdp.KRecursoHumano;
import ode.conhecimento.processo.Cgt.AplCadastrarKRecursoHumano;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgt.AplCadastrarRecursoHumano;
import ode._controleRecursoHumano.cih.FormDadosRecursoHumano;
import ode._controleRecursoHumano.cih.PainelCRUDRecursoHumano;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cgt.AplBase;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.PainelCRUD;

@Controller
public class CtrlRecursoHumanoCRUD extends CtrlCRUD<RecursoHumano> {

	private static final long serialVersionUID = 1L;
	
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
