package ode.atuacaoRecursoHumano.cgd;

import java.util.Collection;
import java.util.List;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cgd.DAOBase;
import ode.atuacaoRecursoHumano.cdp.AtuacaoRH;
import ode.atuacaoRecursoHumano.cdp.CompetenciaRH;

public interface AtuacaoRHDAO extends DAOBase<AtuacaoRH> {

	AtuacaoRH recuperarAtuacaoRHPorRH(Long id);

	Collection<CompetenciaRH> recuperarCompetenciasPorRH(Long idRH);

	Collection<RecursoHumano> recuperarAptosPorPapel(Long idKRecursoHumano);
	
	public List<RecursoHumano> recuperarComParticipacaoPapel(Long idKRH, Long idProjeto);
	
}
