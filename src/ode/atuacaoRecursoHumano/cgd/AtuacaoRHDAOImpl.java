package ode.atuacaoRecursoHumano.cgd;

import java.util.Collection;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import ode.atuacaoRecursoHumano.cdp.AtuacaoRH;
import ode.atuacaoRecursoHumano.cdp.CompetenciaRH;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cgd.DAOBaseImpl;

@Repository
public class AtuacaoRHDAOImpl extends
		DAOBaseImpl<AtuacaoRH> implements AtuacaoRHDAO {

	@Override
	public AtuacaoRH recuperarAtuacaoRHPorRH(Long idRH) {
		AtuacaoRH atuacaoRH;
		try {
			atuacaoRH = entityManager.createQuery("from AtuacaoRH a where a.recursoHumano.id = :idRH", AtuacaoRH.class).setParameter("idRH", idRH).getSingleResult();
		}
		catch (NoResultException e) {
			atuacaoRH = null;
		}
		return atuacaoRH;
	}

	@Override
	public Collection<CompetenciaRH> recuperarCompetenciasPorAtuacaoRH(Long idAtuacaoRH) {
		return entityManager.createQuery("from CompetenciaRH c where c.atuacaoRH.id = :idAtuacaoRH", CompetenciaRH.class).setParameter("idAtuacaoRH", idAtuacaoRH).getResultList();
	}

	@Override
	public Collection<RecursoHumano> recuperarAptosPorPapel(Long idKRecursoHumano) {
		return entityManager.createQuery("select rh from AtuacaoRH a" +
				" right join a.recursoHumano rh" +
				" where rh.cargo.id = :idKRecursoHumano" +
				" or a!=null and :idKRecursoHumano in (select p.id from a.papeis p)",
				RecursoHumano.class)
				.setParameter("idKRecursoHumano", idKRecursoHumano)
				.getResultList();
	}

	
}