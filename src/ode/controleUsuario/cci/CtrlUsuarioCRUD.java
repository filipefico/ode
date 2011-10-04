package ode.controleUsuario.cci;

import java.util.Collection;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgt.AplCadastrarRecursoHumano;
import ode.controleUsuario.cdp.Usuario;
import ode.controleUsuario.cgt.AplCadastrarUsuario;
import ode.controleUsuario.cih.FormDadosUsuario;
import ode.controleUsuario.cih.PainelCRUDUsuario;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cgt.AplBase;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.PainelCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlUsuarioCRUD extends CtrlCRUD<Usuario> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AplCadastrarUsuario aplCadastrarUsuario;

	@Autowired
	private AplCadastrarRecursoHumano aplCadastrarRecursoHumano;
	
	@Override
	public String definirTituloJanelaDados() {
		return "Usuário";
	}

	@Override
	public AplBase<Usuario> definirNucleoAplCadastroBase() {
		return this.aplCadastrarUsuario;
	}

	@Override
	public PainelCRUD<Usuario> definirPainelCRUD() {
		return new PainelCRUDUsuario();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Usuários";
	}

	@Override
	public FormularioDadosCRUD<Usuario> definirFormularioCadastro() {
		return new FormDadosUsuario();
	}

	@Override
	public Usuario factoryObjetoDados() {
		return new Usuario();
	}

	public AplCadastrarRecursoHumano getAplCadastrarRecursoHumano() {
		return aplCadastrarRecursoHumano;
	}

	public void setAplCadastrarRecursoHumano(
			AplCadastrarRecursoHumano aplCadastrarRecursoHumano) {
		this.aplCadastrarRecursoHumano = aplCadastrarRecursoHumano;
	}
	
	public Collection<RecursoHumano> listarRecursosHumanos() {
		return getAplCadastrarRecursoHumano().recuperarTodos();
	}

}
