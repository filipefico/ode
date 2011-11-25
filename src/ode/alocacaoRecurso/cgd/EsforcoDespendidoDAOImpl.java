package ode.alocacaoRecurso.cgd;

import java.util.List;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.alocacaoRecurso.cdp.EsforcoDespendido;

import org.springframework.stereotype.Repository;

@Repository
public class EsforcoDespendidoDAOImpl extends DAOBaseImpl<EsforcoDespendido> implements EsforcoDespendidoDAO {

	@Override
	public List<EsforcoDespendido> recuperarPorAlocacaoRH(Long idAlocacao) {
		return entityManager.createQuery("from EsforcoDespendido e where e.alocacaoRH.id = :idAlocacao order by e.data",EsforcoDespendido.class).setParameter("idAlocacao",idAlocacao).getResultList();
	}

}
