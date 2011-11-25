package ode._controleRecursoHumano.cgd;

import org.springframework.stereotype.Repository;

import ode._controleRecursoHumano.cdp.Equipe;
import ode._infraestruturaBase.cgd.DAOBaseImpl;

@Repository
public class EquipeDAOImpl extends
		DAOBaseImpl<Equipe> implements EquipeDAO {

	@Override
	public Equipe obterEquipePorProjeto(Long idProjeto) {
		return recuperarSinglePorQuery(entityManager.createQuery("from Equipe e where e.projeto.id = :idProjeto", Equipe.class).setParameter("idProjeto", idProjeto));
	}
}
