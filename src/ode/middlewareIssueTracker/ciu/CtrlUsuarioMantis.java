package ode.middlewareIssueTracker.ciu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode.middlewareIssueTracker.cdp.UsuarioMantis;
import ode.middlewareIssueTracker.cgt.AplUsuarioMantis;

@Controller
public class CtrlUsuarioMantis extends CtrlBase {

	private static final long serialVersionUID = 1L;
	
	private JanUsuarioMantis jum;
	
	@Autowired
	AplUsuarioMantis aplUsuarioMantis;

	@Override
	public void iniciar() {
		// TODO Auto-generated method stub
		jum = new JanUsuarioMantis(this);
		jum.mostrar();
	}
	
	public UsuarioMantis recuperarUsuarioMantisAtual(){
		UsuarioMantis usuarioMantis = aplUsuarioMantis.recuperarUsuarioMantisAtual();
		return usuarioMantis;
	}
	
	public void salvarUsuarioMantisAtual(UsuarioMantis usuarioMantis){
		
		try {
			aplUsuarioMantis.salvar(usuarioMantis);
		} catch (NucleoRegraNegocioExcecao e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
