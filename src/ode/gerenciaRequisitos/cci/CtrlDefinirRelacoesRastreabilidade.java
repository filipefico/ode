package ode.gerenciaRequisitos.cci;

import org.springframework.stereotype.Controller;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode.gerenciaRequisitos.cih.JanelaDefinirRelacoesRastreabilidade;

@Controller
public class CtrlDefinirRelacoesRastreabilidade extends CtrlBase{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JanelaDefinirRelacoesRastreabilidade janelaRastreab;

	@Override
	public void iniciar() {
		janelaRastreab = new JanelaDefinirRelacoesRastreabilidade();
		janelaRastreab.mostrar();
	}

}
