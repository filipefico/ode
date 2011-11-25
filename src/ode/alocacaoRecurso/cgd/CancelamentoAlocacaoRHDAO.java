package ode.alocacaoRecurso.cgd;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.alocacaoRecurso.cdp.CancelamentoAlocacaoRH;

public interface CancelamentoAlocacaoRHDAO extends DAOBase<CancelamentoAlocacaoRH> {

	CancelamentoAlocacaoRH recuperarPorAlocacaoRH(Long id);

}
