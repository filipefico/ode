package ode.resolucaoProblema.cgd;

import java.util.List;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.resolucaoProblema.cdp.OcorrenciaProblema;

public interface OcorrenciaProblemaDAO extends DAOBase<OcorrenciaProblema>{
	List<OcorrenciaProblema> recuperarTodosOrdenadoImpacto ();
}
