package ode.atuacaoRecursoHumano.cgd;

import java.util.Collection;

import ode.atuacaoRecursoHumano.cdp.CompetenciaRH;
import ode._infraestruturaBase.cgd.DAOBase;

public interface CompetenciaRHDAO extends DAOBase<CompetenciaRH> {

	Collection<CompetenciaRH> obterPorRH(Long idRH);
}
