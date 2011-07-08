package ode.exemplo2.pessoa.Cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("ControladorPrincipal")
public class ControladorPrincipal {

	@Autowired
	CtrlPessoaCRUD ctrlPessoaCRUD;

	public CtrlPessoaCRUD getCtrlPessoaCRUD() {
		return ctrlPessoaCRUD;
	}

	public void setCtrlPessoaCRUD(CtrlPessoaCRUD ctrlPessoaCRUD) {
		this.ctrlPessoaCRUD = ctrlPessoaCRUD;
	}

	public ControladorPrincipal(){
		
	}
	
	public void exibirControlador(){
		System.out.println("CTRL: " + ctrlPessoaCRUD);
	}
	
}

