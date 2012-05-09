package ode.gerenciaConhecimento.cgd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.controleProjeto.cdp.Projeto;
import ode.gerenciaConhecimento.cdp.ConhecimentoRelativoDiscussao;
import ode.gerenciaConhecimento.cdp.Tema;

import org.springframework.stereotype.Repository;

@Repository
public class ConhecimentoRelativoDiscussaoDAOImpl extends
		DAOBaseImpl<ConhecimentoRelativoDiscussao> implements
		ConhecimentoRelativoDiscussaoDAO {
	
	@SuppressWarnings("unchecked")
	public List<ConhecimentoRelativoDiscussao> buscar(String expressao,
			Date dataCriacaoInicial,
			Date dataCriacaoFinal,
			Date dataUltimaAtualizacaoInicial,
			Date dataUltimaAtualizacaoFinal,
			Date dataUltimoAcessoInicial,
			Date dataUltimoAcessoFinal,
			int quantidadeAcessosMinimo,
			int quantidadeAcessosMaximo,
			int quantidadeValoracoesMinimo,
			int quantidadeValoracoesMaximo,
			BigDecimal percentualValoracoesPositivasMinima,
			BigDecimal percentualValoracoesPositivasMaxima,
			BigDecimal percentualValoracoesNegativasMinima,
			BigDecimal percentualValoracoesNegativasMaxima,
			String tipoItemConhecimento,
			List<Projeto> projetos,
			List<KAtividade> atividades,
			List<Tema> temas)
			{		
		List<ConhecimentoRelativoDiscussao> itens = getEntityManager().
				createQuery("from ConhecimentoRelativoDiscussao where " +
						"((:expressao is null or titulo like '%:expressao%') or " +
						" (:expressao is null or resumo like '%:expressao%') or " + 
						" (:expressao is null or resumo like '%:expressao%')) and " +
						"(:tipoItemConhecimento is null or resumo like :tipoItemConhecimento) and " +
						"(:dataCriacaoInicial is null or dataCriacao >= :dataCriacaoInicial) and " +
						"(:dataCriacaoFinal is null or dataCriacao <= :dataCriacaoFinal) and " +
						"(:dataUltimaAtualizacaoInicial is null or dataUltimaAtualizacao >= :dataUltimaAtualizacaoInicial) and " +
						"(:dataUltimaAtualizacaoFinal is null or dataUltimaAtualizacao <= :dataUltimaAtualizacaoFinal) and " +
						"(:dataUltimoAcessoInicial is null or dataUltimoAcesso >= :dataUltimoAcessoInicial) and " +
						"(:dataUltimoAcessoFinal is null or dataUltimoAcesso <= :dataUltimoAcessoFinal) and " +
						"(:quantidadeAcessosMinimo is null or quantidadeAcessos >= :quantidadeAcessosMinimo) and " +
						"(:quantidadeAcessosMaximo is null or quantidadeAcessos <= :quantidadeAcessosMaximo) and " +
						"(:quantidadeValoracoesMinimo is null or quantidadeValoracoes >= :quantidadeValoracoesMinimo) and " +
						"(:quantidadeValoracoesMaximo is null or quantidadeValoracoes <= :quantidadeValoracoesMaximo) and " +
						" ")
						.setParameter("expressao", expressao)
						.setParameter("dataCriacaoInicial", dataCriacaoInicial)
						.setParameter("dataCriacaoFinal", dataCriacaoFinal)
						.setParameter("dataUltimaAtualizacaoInicial", dataUltimaAtualizacaoInicial)
						.setParameter("dataUltimaAtualizacaoFinal", dataUltimaAtualizacaoFinal)
						.setParameter("dataUltimoAcessoInicial", dataUltimoAcessoInicial)
						.setParameter("dataUltimoAcessoFinal", dataUltimoAcessoFinal)
						.setParameter("quantidadeAcessosMinimo", quantidadeAcessosMinimo)
						.setParameter("quantidadeAcessosMaximo", quantidadeAcessosMaximo)
						.setParameter("quantidadeValoracoesMinimo", quantidadeValoracoesMinimo)
						.setParameter("quantidadeValoracoesMaximo", quantidadeValoracoesMaximo)
						.setParameter("tipoItemConhecimento", tipoItemConhecimento)
						.getResultList();

		// Filtros
		List<ConhecimentoRelativoDiscussao> itensRemovidos = new ArrayList<ConhecimentoRelativoDiscussao>();
		for (ConhecimentoRelativoDiscussao item : itens){

			// percentual valoracoes positivas
			float percentualPositivas = item.quantidadeValoracoes(1) / item.getValoracoes().size();

			if (percentualValoracoesPositivasMinima != null) {
				if (percentualPositivas < percentualValoracoesPositivasMinima.floatValue())
					itensRemovidos.add(item);
			}

			if (percentualValoracoesPositivasMaxima != null) {
				if (percentualPositivas > percentualValoracoesPositivasMaxima.floatValue())
					itensRemovidos.add(item);
			}

			// percentual valoracoes negativas
			float percentualNegativas = item.quantidadeValoracoes(-1) / item.getValoracoes().size();

			if (percentualValoracoesNegativasMinima != null) {
				if (percentualNegativas < percentualValoracoesNegativasMinima.floatValue())
					itensRemovidos.add(item);
			}

			if (percentualValoracoesNegativasMaxima != null) {
				if (percentualNegativas > percentualValoracoesNegativasMaxima.floatValue())
					itensRemovidos.add(item);
			}

			// projetos
			item.getProjetos().retainAll(projetos);
			if (item.getProjetos().size() == 0) {
				itensRemovidos.add(item);
			}

			// atividades
			item.getkAtividades().retainAll(atividades);
			if (item.getkAtividades().size() == 0) {
				itensRemovidos.add(item);
			}

			// temas
			item.getTemas().retainAll(temas);
			if (item.getTemas().size() == 0) {
				itensRemovidos.add(item);
			}
		}	

		// Remove itens nao pertecentes
		itens.removeAll(itensRemovidos);

		return itens; 

	}

}
