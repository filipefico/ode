package ode._controleRecursoHumano.cgd;

import java.util.List;

import ode._controleRecursoHumano.cdp.ParticipacaoEquipe;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cgd.DAOBase;

public interface ParticipacaoEquipeDAO extends DAOBase<ParticipacaoEquipe> {

	public List<ParticipacaoEquipe> obterPorEquipe(Long id);

	public List<RecursoHumano> obterMembrosPorProjeto(Long id);

	public List<RecursoHumano> recuperarRecursosHumanosComParticipacaoPapel(Long idKRH, Long idProjeto);

}
