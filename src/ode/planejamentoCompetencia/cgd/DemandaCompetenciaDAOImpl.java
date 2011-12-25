package ode.planejamentoCompetencia.cgd;

import java.util.List;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.planejamentoCompetencia.cdp.DemandaCompetencia;

import org.springframework.stereotype.Repository;

@Repository
public class DemandaCompetenciaDAOImpl extends
		DAOBaseImpl<DemandaCompetencia> implements DemandaCompetenciaDAO {

	@Override
	public List<DemandaCompetencia> recuperarPorDemandaRH(Long idDemandaRH) {
		return entityManager.createQuery("select dc from DemandaCompetencia dc where dc.demandaRH.id = :idDemandaRH", DemandaCompetencia.class)
				.setParameter("idDemandaRH",idDemandaRH)
				.getResultList();
	}

	@Override
	public List<DemandaCompetencia> recuperarNaoPossuidasPorRecursoHumanoDemanda(Long idRecursoHumano, Long idDemandaRH) {
		return entityManager.createQuery("select dc from DemandaCompetencia dc" +
				" where dc.demandaRH.id = :idDemandaRH" +
				" and not exists (" +
				"  from CompetenciaRH crh" +
				"  where crh.atuacaoRH.recursoHumano.id = :idRecursoHumano" +
				"  and crh.kCompetencia = dc.kCompetencia" +
				"  and crh.nivel >= dc.nivel" +
				" )",DemandaCompetencia.class)
				.setParameter("idDemandaRH",idDemandaRH)
				.setParameter("idRecursoHumano",idRecursoHumano)
				.getResultList();
	}
}