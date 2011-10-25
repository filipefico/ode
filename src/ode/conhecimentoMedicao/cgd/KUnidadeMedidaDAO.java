package ode.conhecimentoMedicao.cgd;

import java.util.Collection;

import ode.conhecimentoMedicao.cdp.KUnidadeMedida;
import ode._infraestruturaBase.cgd.DAOBase;

public interface KUnidadeMedidaDAO extends DAOBase<KUnidadeMedida>{

	public Collection<KUnidadeMedida> getKMedidasRelacionadas(KUnidadeMedida objeto);

}
