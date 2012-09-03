package ode.medicao.planejamentoMedicao.cci;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoOrganizacao;
import ode.medicao.planejamentoMedicao.cgt.AplElaborarPlanoMedicaoOrganizacao;

@Controller("CtrlValorReferenciaOrganizacao")
public class CtrlValorReferenciaOrganizacao extends CtrlValorReferencia {

	@Autowired
	AplElaborarPlanoMedicaoOrganizacao aplOrg;
	
	@Override
	public NucleoCombobox<PlanoMedicaoOrganizacao> popularCBPlano() {
		NucleoCombobox<PlanoMedicaoOrganizacao> combox = new NucleoCombobox<PlanoMedicaoOrganizacao>();
		combox.setObjetos(aplOrg.recuperarTodos());
		return combox;
	}

	@Override
	protected String getTitulo() {
		return "Valor Referência para Organização";
	}

}
