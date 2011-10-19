package ode.conhecimentoMedicao.cgd;


import java.util.Collection;

import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cdp.KNecessidadeInformacao;
import ode._infraestruturaBase.cgd.DAOBase;

public interface KNecessidadeInformacaoDAO extends DAOBase<KNecessidadeInformacao> {

	public Collection<KMedida> getKMedidasRelacionadas(KNecessidadeInformacao objeto);
}
