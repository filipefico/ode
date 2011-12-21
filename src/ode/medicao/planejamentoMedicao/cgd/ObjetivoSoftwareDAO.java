package ode.medicao.planejamentoMedicao.cgd;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoSoftware;

public interface ObjetivoSoftwareDAO extends DAOBase<ObjetivoSoftware>{

	Collection<ObjetivoMedicao> getObjetivosMedicao(
			ObjetivoSoftware objetoCadastro);
	
}
