package ode.controleUsuario.ciu;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.controleUsuario.cgt.AplCadastrarUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlAlterarSenha extends CtrlBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AplCadastrarUsuario aplCadastrarUsuario;
	
	private JanelaSimples jan;
	
	private PainelAlterarSenha PainelAlterarSenha;

	@Override
	public void iniciar() {

		jan = factoryJanelaSimples();
		jan.setTitle("Alterar Senha");
		
		PainelAlterarSenha = new PainelAlterarSenha(this);
		PainelAlterarSenha.setParent(jan);
		
		jan.doOverlapped();
		
	}

	public void finalizar() {
		jan.detach();
		PainelAlterarSenha.detach();
		this.detach();
	}

	public AplCadastrarUsuario getAplCadastrarUsuario() {
		return aplCadastrarUsuario;
	}

	public void setAplCadastrarUsuario(AplCadastrarUsuario aplCadastrarUsuario) {
		this.aplCadastrarUsuario = aplCadastrarUsuario;
	}
	
}
