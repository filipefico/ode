package ode.gerenciaConhecimento.cgd;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.gerenciaConhecimento.cdp.Tema;

@Repository
public class TemaDAOImpl extends DAOBaseImpl<Tema> implements TemaDAO {

	@PersistenceContext
	EntityManager entityManager;
}
