package ode.medicao.planejamentoMedicao.cgd;

import java.util.Collection;
import ode._infraestruturaBase.cgd.DAOBase;
import ode.medicao.planejamentoMedicao.cdp.NecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;

public interface ObjetivoMedicaoDAO extends DAOBase<ObjetivoMedicao>{

	Collection<NecessidadeInformacao> getObjetivosSoftware(
			ObjetivoMedicao objeto);
	
}
