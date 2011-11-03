package ode._controleProcesso.ciu;

import org.springframework.stereotype.Controller;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.util.NucleoContexto;
import ode.controleProjeto.cdp.Projeto;

@Controller
public class CtrlDefinicaoProcesso extends CtrlBase {

	private static final long serialVersionUID = 1L;
	
	private JanDefinicaoProcesso jan;
	
	private Projeto projeto;
	
	@Override
	public void iniciar() {
		projeto = NucleoContexto.recuperarProjeto();
		
		jan = new JanDefinicaoProcesso(this);
		jan.mostrar();
	}

	public Projeto getProjeto() {
		return projeto;
	}
}
