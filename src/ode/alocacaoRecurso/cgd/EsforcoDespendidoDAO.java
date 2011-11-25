package ode.alocacaoRecurso.cgd;

import java.util.List;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.alocacaoRecurso.cdp.EsforcoDespendido;

public interface EsforcoDespendidoDAO extends DAOBase<EsforcoDespendido> {

	List<EsforcoDespendido> recuperarPorAlocacaoRH(Long idAlocacao);

}
