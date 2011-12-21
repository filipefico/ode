package ode.medicao.planejamentoMedicao.cgd;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoSoftware;

public interface ObjetivoEstrategicoDAO extends DAOBase<ObjetivoEstrategico> {
	
	public Collection<ObjetivoMedicao> getObjetivosMedicao(ObjetivoEstrategico obj);
}
