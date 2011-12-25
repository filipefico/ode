package ode.planejamentoCompetencia.cgd;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.planejamentoCompetencia.cdp.DemandaCompetencia;

public interface DemandaCompetenciaDAO extends DAOBase<DemandaCompetencia> {

	Collection<DemandaCompetencia> recuperarPorDemandaRH(Long idDemandaRH);
	
	Collection<DemandaCompetencia> recuperarNaoPossuidasPorRecursoHumanoDemanda(Long idRecursoHumano, Long idDemandaRH);
	
}
