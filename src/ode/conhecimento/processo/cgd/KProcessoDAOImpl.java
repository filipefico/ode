package ode.conhecimento.processo.cgd;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.conhecimento.processo.cdp.KProcesso;

import org.springframework.stereotype.Repository;

@Repository
public class KProcessoDAOImpl extends DAOBaseImpl<KProcesso> implements
		KProcessoDAO {

	@Override
	public Collection recuperarTodosNaoEngenharia() {
		return entityManager.createQuery(
				"select a from KProcesso a where a.ehEngenharia = false")
				.getResultList();
	}

	@Override
	public Collection recuperarTodosEngenharia() {
		return entityManager.createQuery(
				"select a from KProcesso a where a.ehEngenharia = true")
				.getResultList();
	}
}
