package ode.processoPadrao.ciu;

import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cgt.AplDefinirProcessoPadrao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlDefinirProcessoPadraoCRUD {
	private static final long serialVersionUID = 5163665131082019928L;

	public static String TIPO_COMPP_PROCESSO_COMPLEXO = "CompPP Processo Complexo";
	public static String TIPO_COMPP_PROCESSO_SIMPLES = "CompPP Processo Simples";
	public static String TIPO_COMPP_MACROATIVIDADE = "CompPP Macroatividade";

	/** Janela Principal do ambiente de definição de processo padrão. */
	

	@Autowired
	AplDefinirProcessoPadrao aplDefinirProcessoPadrao;

	public AplDefinirProcessoPadrao getAplDefinirProcessoPadrao() {
		return aplDefinirProcessoPadrao;
	}

	public void setAplDefinirProcessoPadrao(
			AplDefinirProcessoPadrao aplDefinirProcessoPadrao) {
		this.aplDefinirProcessoPadrao = aplDefinirProcessoPadrao;
	}

}
