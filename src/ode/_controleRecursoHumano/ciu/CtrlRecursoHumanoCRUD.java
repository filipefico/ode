package ode._controleRecursoHumano.ciu;

import java.util.Collection;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgt.AplCadastrarRecursoHumano;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.conhecimento.processo.cgt.AplCadastrarKRecursoHumano;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
	public AplCRUD<RecursoHumano> definirAplCRUD() {
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
