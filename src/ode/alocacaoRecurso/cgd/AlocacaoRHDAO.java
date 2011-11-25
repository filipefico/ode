package ode.alocacaoRecurso.cgd;

import java.util.List;

import ode._controleProcesso.cdp.ProcessoProjetoEspecifico;
import ode._infraestruturaBase.cgd.DAOBase;
import ode.alocacaoRecurso.cdp.AlocacaoRH;
import ode.controleProjeto.cdp.Projeto;

public interface AlocacaoRHDAO extends DAOBase<AlocacaoRH> {

	public List<AlocacaoRH> recuperarPorAtividadeKRecursoHumano(Long idAtividade, Long idKRecursoHumano);

	public AlocacaoRH recuperarPorRecursoHumanoAtividadeKRecursoHumano(Long idRecursoHumano, Long idAtividade, Long idKRecursoHumano);

	public List<AlocacaoRH> obterPossiveisAlocacoesAutomaticas(Long idProjeto);

	public List<Projeto> recuperarProjetosPorRH(Long idRecursoHumano);

	public List<ProcessoProjetoEspecifico> recuperarProcessosComAlocacaoPorRecursoHumanoProjeto(Long idRecursoHumano, Long idProjeto);

	List<AlocacaoRH> recuperarPorRecursoHumanoProcesso(Long idRecursoHumano, Long idProcesso);

}
