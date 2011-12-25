package ode._controleRecursoHumano.cgd;

import java.util.List;

import ode._controleRecursoHumano.cdp.ParticipacaoEquipe;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cgd.DAOBaseImpl;

import org.springframework.stereotype.Repository;

@Repository
public class ParticipacaoEquipeDAOImpl extends
		DAOBaseImpl<ParticipacaoEquipe> implements ParticipacaoEquipeDAO {

	@Override
	public List<ParticipacaoEquipe> obterPorEquipeAtual(Long idEquipe) {
		return entityManager.createQuery("from ParticipacaoEquipe pe where pe.equipe.id = :idEquipe and pe.dtFim is null", ParticipacaoEquipe.class).setParameter("idEquipe", idEquipe).getResultList();
	}

	@Override
	public List<RecursoHumano> obterMembrosPorProjeto(Long idProjeto) {
		return entityManager.createQuery("select pe.recursoHumano from ParticipacaoEquipe pe where pe.equipe.projeto.id = :idProjeto and pe.dtFim is null", RecursoHumano.class).setParameter("idProjeto", idProjeto).getResultList();
	}

	@Override
	public List<ParticipacaoEquipe> obterHistoricoPorProjeto(Long idProjeto) {
		return entityManager.createQuery("from ParticipacaoEquipe pe where pe.equipe.projeto.id = :idProjeto and pe.dtFim is not null order by pe.dtFim desc, pe.dtInicio desc", ParticipacaoEquipe.class).setParameter("idProjeto", idProjeto).getResultList();
	}
	
}
