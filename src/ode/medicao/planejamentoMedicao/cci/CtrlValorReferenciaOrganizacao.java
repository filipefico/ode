package ode.medicao.planejamentoMedicao.cci;

import org.springframework.stereotype.Controller;

import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoOrganizacao;

@Controller("CtrlValorReferenciaOrganizacao")
public class CtrlValorReferenciaOrganizacao extends CtrlValorReferencia {

	@Override
	protected NucleoCombobox<PlanoMedicao> popularCBPlano() {
		return new NucleoCombobox<PlanoMedicao>();
	}

}
