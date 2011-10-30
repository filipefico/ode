package ode.atuacaoRecursoHumano.cgd;

import java.util.Collection;

import ode.atuacaoRecursoHumano.cdp.AtuacaoRH;
import ode.atuacaoRecursoHumano.cdp.CompetenciaRH;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cgd.DAOBase;

public interface AtuacaoRHDAO extends DAOBase<AtuacaoRH> {

	AtuacaoRH recuperarAtuacaoRHPorRH(Long id);

	Collection<CompetenciaRH> recuperarCompetenciasPorAtuacaoRH(Long idAtuacaoRH);

	Collection<RecursoHumano> recuperarAptosPorPapel(Long idKRecursoHumano);
	
}
