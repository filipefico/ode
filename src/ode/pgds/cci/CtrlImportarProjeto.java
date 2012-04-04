package ode.pgds.cci;

import org.springframework.stereotype.Controller;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode.pgds.cih.JanelaImportarProjeto;

@SuppressWarnings("serial")
@Controller
public class CtrlImportarProjeto extends CtrlBase{

	@Override
	public void iniciar() {
		new JanelaImportarProjeto().doOverlapped();
	}

}
