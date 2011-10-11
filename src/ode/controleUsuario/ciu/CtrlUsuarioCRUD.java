package ode.controleUsuario.ciu;

import java.util.Collection;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgt.AplCadastrarRecursoHumano;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.controleUsuario.cdp.Usuario;
import ode.controleUsuario.cgt.AplCadastrarUsuario;

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
	public AplCRUD<Usuario> definirAplCRUD() {
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
