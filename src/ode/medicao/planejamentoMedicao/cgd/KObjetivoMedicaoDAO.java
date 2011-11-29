package ode.medicao.planejamentoMedicao.cgd;

import java.util.Collection;
import ode._infraestruturaBase.cgd.DAOBase;
import ode.medicao.planejamentoMedicao.cdp.KNecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoMedicao;

public interface KObjetivoMedicaoDAO extends DAOBase<KObjetivoMedicao>{

	Collection<KNecessidadeInformacao> getObjetivosSoftware(
			KObjetivoMedicao objeto);
	
}
