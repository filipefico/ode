package ode.atuacaoRecursoHumano.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import ode.atuacaoRecursoHumano.cdp.CompetenciaRH;
import ode._infraestruturaBase.cgd.DAOBaseImpl;

@Repository
public class CompetenciaRHDAOImpl extends
		DAOBaseImpl<CompetenciaRH> implements CompetenciaRHDAO {

	@Override
	public Collection<CompetenciaRH> obterPorRH(Long idRH) {
		return entityManager.createQuery("from CompetenciaRH c where c.atuacaoRH.recursoHumano.id = :idRH", CompetenciaRH.class).setParameter("idRH", idRH).getResultList();
	}
}