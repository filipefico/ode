package ode._controleRecursoHumano.cgd;

import java.util.List;

import org.springframework.stereotype.Repository;

import ode._controleRecursoHumano.cdp.ParticipacaoEquipe;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cgd.DAOBaseImpl;

@Repository
public class ParticipacaoEquipeDAOImpl extends
		DAOBaseImpl<ParticipacaoEquipe> implements ParticipacaoEquipeDAO {

	@Override
	public List<ParticipacaoEquipe> obterPorEquipe(Long idEquipe) {
		return entityManager.createQuery("from ParticipacaoEquipe pe where pe.equipe.id = :idEquipe", ParticipacaoEquipe.class).setParameter("idEquipe", idEquipe).getResultList();
	}

	@Override
	public List<RecursoHumano> obterMembrosPorProjeto(Long idProjeto) {
		return entityManager.createQuery("select pe.recursoHumano from ParticipacaoEquipe pe where pe.equipe.projeto.id = :idProjeto", RecursoHumano.class).setParameter("idProjeto", idProjeto).getResultList();
	}

}
