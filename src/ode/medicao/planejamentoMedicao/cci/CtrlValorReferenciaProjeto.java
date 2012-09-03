package ode.medicao.planejamentoMedicao.cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoOrganizacao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoProjeto;
import ode.medicao.planejamentoMedicao.cgd.PlanoMedicaoProjetoDAO;
@Controller("CtrlValorReferenciaProjeto")
public class CtrlValorReferenciaProjeto extends CtrlValorReferencia {

	
	@Autowired
	PlanoMedicaoProjetoDAO aplProj;
	
	@Override
	public NucleoCombobox<PlanoMedicaoProjeto> popularCBPlano() {
		NucleoCombobox<PlanoMedicaoProjeto> combox = new NucleoCombobox<PlanoMedicaoProjeto>();
		combox.setObjetos(aplProj.recuperarTodos());
		return combox;
	}

	@Override
	protected String getTitulo() {
		return "Valor Referência para Projeto";
	}
	
}
