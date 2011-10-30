package ode._controleRecursoHumano.cgd;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import ode._controleRecursoHumano.cdp.Equipe;
import ode._infraestruturaBase.cgd.DAOBaseImpl;

@Repository
public class EquipeDAOImpl extends
		DAOBaseImpl<Equipe> implements EquipeDAO {

	@Override
	public Equipe obterEquipePorProjeto(Long idEquipe) {
		Equipe equipe;
		try {
			equipe = entityManager.createQuery("from Equipe e where e.projeto.id = :idEquipe", Equipe.class).setParameter("idEquipe", idEquipe).getSingleResult();
		}
		catch (NoResultException e) {
			equipe = null;
		}
		return equipe;
	}
}
