package ode.gerenciaConhecimento.cgd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.controleProjeto.cdp.Projeto;
import ode.gerenciaConhecimento.cdp.LicaoAprendida;
import ode.gerenciaConhecimento.cdp.Tema;

import org.springframework.stereotype.Repository;

@Repository
public class LicaoAprendidaDAOImpl extends DAOBaseImpl<LicaoAprendida>
implements LicaoAprendidaDAO {

	@SuppressWarnings("unchecked")
	public List<LicaoAprendida> buscar(String expressao,
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
			Collection<Projeto> projetos,
			Collection<KAtividade> atividades,
			Collection<Tema> temas)
			{		
		List<LicaoAprendida> licoes = getEntityManager().
				createQuery("from LicaoAprendida where " +
						"((:expressao is null or titulo like '%:expressao%') or " +
						" (:expressao is null or resumo like '%:expressao%') or " +
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
		List<LicaoAprendida> licoesRemovidas = new ArrayList<LicaoAprendida>();
		for (LicaoAprendida licaoAprendida : licoes){

			// percentual valoracoes positivas
			float percentualPositivas = licaoAprendida.quantidadeValoracoes(1) / licaoAprendida.getValoracoes().size();

			if (percentualValoracoesPositivasMinima != null) {
				if (percentualPositivas < percentualValoracoesPositivasMinima.floatValue())
					licoesRemovidas.add(licaoAprendida);
			}

			if (percentualValoracoesPositivasMaxima != null) {
				if (percentualPositivas > percentualValoracoesPositivasMaxima.floatValue())
					licoesRemovidas.add(licaoAprendida);
			}

			// percentual valoracoes negativas
			float percentualNegativas = licaoAprendida.quantidadeValoracoes(-1) / licaoAprendida.getValoracoes().size();

			if (percentualValoracoesNegativasMinima != null) {
				if (percentualNegativas < percentualValoracoesNegativasMinima.floatValue())
					licoesRemovidas.add(licaoAprendida);
			}

			if (percentualValoracoesNegativasMaxima != null) {
				if (percentualNegativas > percentualValoracoesNegativasMaxima.floatValue())
					licoesRemovidas.add(licaoAprendida);
			}

			// projetos
			licaoAprendida.getProjetos().retainAll(projetos);
			if (licaoAprendida.getProjetos().size() == 0) {
				licoesRemovidas.add(licaoAprendida);
			}

			// atividades
			licaoAprendida.getkAtividades().retainAll(atividades);
			if (licaoAprendida.getkAtividades().size() == 0) {
				licoesRemovidas.add(licaoAprendida);
			}

			// temas
			licaoAprendida.getTemas().retainAll(temas);
			if (licaoAprendida.getTemas().size() == 0) {
				licoesRemovidas.add(licaoAprendida);
			}
		}	

		// Remove licoes nao pertecentes
		licoes.removeAll(licoesRemovidas);

		return licoes; 

	}

}
