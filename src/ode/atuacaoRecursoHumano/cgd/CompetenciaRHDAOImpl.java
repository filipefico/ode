package ode.atuacaoRecursoHumano.cgd;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.atuacaoRecursoHumano.cdp.CompetenciaRH;

import org.springframework.stereotype.Repository;

@Repository
public class CompetenciaRHDAOImpl extends
		DAOBaseImpl<CompetenciaRH> implements CompetenciaRHDAO {

	@Override
	public Collection<CompetenciaRH> obterPorRH(Long idRH) {
		return entityManager.createQuery("from CompetenciaRH c where c.atuacaoRH.recursoHumano.id = :idRH", CompetenciaRH.class).setParameter("idRH", idRH).getResultList();
	}
	
}