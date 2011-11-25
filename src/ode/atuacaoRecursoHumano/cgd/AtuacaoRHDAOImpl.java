package ode.atuacaoRecursoHumano.cgd;

import java.util.Collection;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.atuacaoRecursoHumano.cdp.AtuacaoRH;
import ode.atuacaoRecursoHumano.cdp.CompetenciaRH;

import org.springframework.stereotype.Repository;

@Repository
public class AtuacaoRHDAOImpl extends
		DAOBaseImpl<AtuacaoRH> implements AtuacaoRHDAO {

	@Override
	public AtuacaoRH recuperarAtuacaoRHPorRH(Long idRH) {
		return recuperarSinglePorQuery(entityManager.createQuery("from AtuacaoRH a where a.recursoHumano.id = :idRH", AtuacaoRH.class).setParameter("idRH", idRH));
	}

	@Override
	public Collection<CompetenciaRH> recuperarCompetenciasPorAtuacaoRH(Long idAtuacaoRH) {
		return entityManager.createQuery("from CompetenciaRH c where c.atuacaoRH.id = :idAtuacaoRH", CompetenciaRH.class).setParameter("idAtuacaoRH", idAtuacaoRH).getResultList();
	}

	@Override
	public Collection<RecursoHumano> recuperarAptosPorPapel(Long idKRecursoHumano) {
		return entityManager.createQuery("select rh from RecursoHumano rh" +
				" where rh.cargo.id = :idKRecursoHumano" +
				" or exists (from AtuacaoRH a where a.recursoHumano = rh and :idKRecursoHumano in (select p.id from a.papeis p))",
				RecursoHumano.class)
				.setParameter("idKRecursoHumano", idKRecursoHumano)
				.getResultList();
	}

	
}