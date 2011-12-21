package ode.medicao.planejamentoMedicao.cci;

import org.springframework.stereotype.Controller;

import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicao;
@Controller("CtrlValorReferenciaProjeto")
public class CtrlValorReferenciaProjeto extends CtrlValorReferencia {

	@Override
	protected NucleoCombobox<PlanoMedicao> popularCBPlano() {
		return new NucleoCombobox<PlanoMedicao>();
	}

}
