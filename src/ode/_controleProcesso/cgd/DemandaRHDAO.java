package ode._controleProcesso.cgd;

import java.util.Collection;
import ode._controleProcesso.cdp.DemandaRH;
import ode._infraestruturaBase.cgd.DAOBase;
import ode.conhecimento.processo.cdp.KRecursoHumano;

public interface DemandaRHDAO extends DAOBase<DemandaRH> {

	public Collection<KRecursoHumano> recuperarKRecursosHumanosPorProjeto(Long idProjeto);

	public Collection<DemandaRH> recuperarPorAtividade(Long idAtividade);

}
