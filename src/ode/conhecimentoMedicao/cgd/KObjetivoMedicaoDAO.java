package ode.conhecimentoMedicao.cgd;

import java.util.Collection;

import ode.conhecimentoMedicao.cdp.KNecessidadeInformacao;
import ode.conhecimentoMedicao.cdp.KObjetivoMedicao;
import ode.conhecimentoMedicao.cdp.KObjetivoSoftware;
import ode._infraestruturaBase.cgd.DAOBase;

public interface KObjetivoMedicaoDAO extends DAOBase<KObjetivoMedicao>{

	Collection<KNecessidadeInformacao> getObjetivosSoftware(
			KObjetivoMedicao objeto);
	
}
