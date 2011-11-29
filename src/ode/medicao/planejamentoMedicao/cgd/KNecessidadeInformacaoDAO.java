package ode.medicao.planejamentoMedicao.cgd;


import java.util.Collection;

import ode.conhecimentoMedicao.cdp.KMedida;
import ode.medicao.planejamentoMedicao.cdp.KNecessidadeInformacao;
import ode._infraestruturaBase.cgd.DAOBase;

public interface KNecessidadeInformacaoDAO extends DAOBase<KNecessidadeInformacao> {

	public Collection<KMedida> getKMedidasRelacionadas(KNecessidadeInformacao objeto);
}
