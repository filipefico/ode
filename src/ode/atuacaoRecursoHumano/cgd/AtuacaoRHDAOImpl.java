package ode.atuacaoRecursoHumano.cgd;

import java.util.Collection;
import java.util.List;

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
	public Collection<CompetenciaRH> recuperarCompetenciasPorRH(Long idRH) {
		return entityManager.createQuery("from CompetenciaRH c where c.atuacaoRH.recursoHumano.id = :idRH", CompetenciaRH.class).setParameter("idRH", idRH).getResultList();
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
	
	@Override
	public List<RecursoHumano> recuperarComParticipacaoPapel(Long idKRH, Long idProjeto) {
		return entityManager.createQuery("select pe.recursoHumano from ParticipacaoEquipe pe" +
				" where pe.equipe.projeto.id = :idProjeto" +
				" and pe.dtFim is null" +
				" and :idKRH in (select p.id from pe.papeis p)", RecursoHumano.class).setParameter("idProjeto", idProjeto).setParameter("idKRH", idKRH).getResultList();
	}
	
}