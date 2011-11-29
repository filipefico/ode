package ode.medicao.planejamentoMedicao.cgd;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoSoftware;

public interface KObjetivoEstrategicoDAO extends DAOBase<KObjetivoEstrategico> {
	
	public Collection<KObjetivoMedicao> getObjetivosMedicao(KObjetivoEstrategico obj);
}
