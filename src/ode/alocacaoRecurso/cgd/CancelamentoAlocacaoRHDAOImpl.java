package ode.alocacaoRecurso.cgd;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.alocacaoRecurso.cdp.CancelamentoAlocacaoRH;

import org.springframework.stereotype.Repository;

@Repository
public class CancelamentoAlocacaoRHDAOImpl extends DAOBaseImpl<CancelamentoAlocacaoRH> implements CancelamentoAlocacaoRHDAO {

	@Override
	public CancelamentoAlocacaoRH recuperarPorAlocacaoRH(Long idAlocacaoRH) {
		return recuperarSinglePorQuery(entityManager.createQuery("from CancelamentoAlocacaoRH c where c.alocacaoRH.id = :idAlocacaoRH", CancelamentoAlocacaoRH.class).setParameter("idAlocacaoRH", idAlocacaoRH));
	}

}
