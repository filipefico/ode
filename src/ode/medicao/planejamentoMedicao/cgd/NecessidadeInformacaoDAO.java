package ode.medicao.planejamentoMedicao.cgd;


import java.util.Collection;

import ode.conhecimentoMedicao.cdp.KMedida;
import ode.medicao.planejamentoMedicao.cdp.NecessidadeInformacao;
import ode._infraestruturaBase.cgd.DAOBase;

public interface NecessidadeInformacaoDAO extends DAOBase<NecessidadeInformacao> {

	public Collection<KMedida> getKMedidasRelacionadas(NecessidadeInformacao objeto);
}
