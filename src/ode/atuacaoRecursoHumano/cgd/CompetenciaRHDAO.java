package ode.atuacaoRecursoHumano.cgd;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.atuacaoRecursoHumano.cdp.CompetenciaRH;

public interface CompetenciaRHDAO extends DAOBase<CompetenciaRH> {

	Collection<CompetenciaRH> obterPorRH(Long idRH);

}
