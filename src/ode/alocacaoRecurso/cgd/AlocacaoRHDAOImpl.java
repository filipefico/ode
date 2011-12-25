package ode.alocacaoRecurso.cgd;

import java.util.List;

import ode._controleProcesso.cdp.ProcessoProjetoEspecifico;
import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.alocacaoRecurso.cdp.AlocacaoRH;
import ode.alocacaoRecurso.cdp.EstadoAlocacaoRH;
import ode.controleProjeto.cdp.Projeto;

import org.springframework.stereotype.Repository;

@Repository
public class AlocacaoRHDAOImpl extends DAOBaseImpl<AlocacaoRH> implements AlocacaoRHDAO {

	@Override
	public List<AlocacaoRH> recuperarPorAtividadeKRecursoHumano(Long idAtividade, Long idKRecursoHumano) {
		return entityManager.createQuery("select a from AlocacaoRH a where a.atividade.id = :idAtividade and a.kRecursoHumano.id = :idKRecursoHumano", AlocacaoRH.class).setParameter("idAtividade", idAtividade).setParameter("idKRecursoHumano", idKRecursoHumano).getResultList();
	}

	@Override
	public AlocacaoRH recuperarPorRecursoHumanoAtividadeKRecursoHumano(Long idRecursoHumano, Long idAtividade, Long idKRecursoHumano) {
		return recuperarSinglePorQuery(entityManager.createQuery("select a from AlocacaoRH a where a.atividade.id = :idAtividade and a.kRecursoHumano.id = :idKRecursoHumano and a.recursoHumano.id = :idRecursoHumano", AlocacaoRH.class).setParameter("idAtividade", idAtividade).setParameter("idKRecursoHumano", idKRecursoHumano).setParameter("idRecursoHumano", idRecursoHumano));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AlocacaoRH> obterPossiveisAlocacoesAutomaticas(Long idProjeto) {
		return entityManager.createQuery(
			"select new AlocacaoRH(ativ, pe.recursoHumano, krh)" +
			" from DemandaRH d join d.kRecursoHumano krh join d.definicaoAtividade da join da.atividade ativ, ParticipacaoEquipe pe" +
			//pega todas as atividades do projeto			
			" where ativ.processoProjetoEspecifico.processoProjetoGeral.projeto.id = :idProjeto" +
			//desconsidera as que ja possuem alocacao para aquela demanda
			" and not exists (from AlocacaoRH arh where arh.atividade = ativ and arh.kRecursoHumano = krh)" +
			//garante que somente uma pessoa na equipe pode atender aquela demanda, incluindo as competencias
			" and (select count(*) from ParticipacaoEquipe pe2 where " + subqueryFiltroAlocacaoAutomatica("pe2") + ") = 1" +
			//filtra a instancia da pessoa que atende ao filtro
			" and " + subqueryFiltroAlocacaoAutomatica("pe")
			).setParameter("idProjeto", idProjeto).getResultList();
	}
	
	//precisa do identificador do campo ParticipacaoEquipe usado na query principal
	private String subqueryFiltroAlocacaoAutomatica(String participacaoEquipe) {
		return participacaoEquipe + ".equipe.projeto.id = :idProjeto" +
				"  and krh member of "+participacaoEquipe+".papeis" +
				"  and not exists (from DemandaCompetencia dc" +
				"   where dc.demandaRH = d" +
				"   and not exists (" +
				"    from CompetenciaRH crh" +
				"    where crh.atuacaoRH.recursoHumano = "+participacaoEquipe+".recursoHumano" +
				"    and crh.kCompetencia = dc.kCompetencia" +
				"    and crh.nivel >= dc.nivel" +
				"   )" +
				"  )";
	}

	@Override
	public List<Projeto> recuperarProjetosPorRH(Long idRecursoHumano) {
		return entityManager.createQuery("from Projeto p" +
				" where exists (from AlocacaoRH a where a.recursoHumano.id = :idRecursoHumano and a.atividade.processoProjetoEspecifico.processoProjetoGeral.projeto = p)", Projeto.class).setParameter("idRecursoHumano", idRecursoHumano).getResultList();
	}

	@Override
	public List<ProcessoProjetoEspecifico> recuperarProcessosComAlocacaoPorRecursoHumanoProjeto(Long idRecursoHumano, Long idProjeto) {
		return entityManager.createQuery("from ProcessoProjetoEspecifico p" +
				" where p.processoProjetoGeral.projeto.id = :idProjeto" +
				" and exists (from AlocacaoRH a where a.recursoHumano.id = :idRecursoHumano and a.atividade.processoProjetoEspecifico = p)", ProcessoProjetoEspecifico.class)
				.setParameter("idRecursoHumano",idRecursoHumano)
				.setParameter("idProjeto",idProjeto)
				.getResultList();
	}
	
	@Override
	public List<AlocacaoRH> recuperarPorRecursoHumanoProcesso(Long idRecursoHumano, Long idProcesso) {
		return entityManager.createQuery("select arh from AlocacaoRH arh join arh.atividade a join arh.kRecursoHumano krh" +
				" where arh.recursoHumano.id = :idRecursoHumano" +
				" and a.processoProjetoEspecifico.id = :idProcesso" +
				" order by a.nome, krh.nome", AlocacaoRH.class)
				.setParameter("idRecursoHumano",idRecursoHumano)
				.setParameter("idProcesso",idProcesso)
				.getResultList();
	}

	@Override
	public List<AlocacaoRH> recuperarPorRecursoHumanoPapel(Long idRecursoHumano, Long idKRecursoHumano, Long idProjeto) {
		return entityManager.createQuery("select arh from AlocacaoRH arh where arh.atividade.processoProjetoEspecifico.processoProjetoGeral.projeto.id = :idProjeto" +
				" and arh.recursoHumano.id = :idRecursoHumano" +
				" and arh.kRecursoHumano.id = :idKRecursoHumano" +
				" and (" +
				"arh.estado != :estadoCancelada" +
				" or arh.estado != :estadoEncerrada" +
				")", AlocacaoRH.class)
				.setParameter("idRecursoHumano",idRecursoHumano)
				.setParameter("idKRecursoHumano",idKRecursoHumano)
				.setParameter("idProjeto",idProjeto)
				.setParameter("estadoCancelada",EstadoAlocacaoRH.Cancelada)
				.setParameter("estadoEncerrada",EstadoAlocacaoRH.Encerrada)
				.getResultList();
	}
}
