package ode._controleRecursoHumano.cgd;

import java.util.List;

import ode._controleRecursoHumano.cdp.ParticipacaoEquipe;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cgd.DAOBase;

public interface ParticipacaoEquipeDAO extends DAOBase<ParticipacaoEquipe> {

	public List<ParticipacaoEquipe> obterHistoricoPorProjeto(Long id);

	public List<RecursoHumano> obterMembrosPorProjeto(Long id);

	public List<ParticipacaoEquipe> obterPorEquipeAtual(Long id);

}
