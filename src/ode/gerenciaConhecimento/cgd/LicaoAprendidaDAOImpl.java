package ode.gerenciaConhecimento.cgd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

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
			Date dataUltimoAcessoInicial,
			Date dataUltimoAcessoFinal,
			Long quantidadeAcessosMinimo,
			Long quantidadeAcessosMaximo,
			Long quantidadeValoracoesMinimo,
			Long quantidadeValoracoesMaximo,
			BigDecimal percentualValoracoesPositivasMinima,
			BigDecimal percentualValoracoesPositivasMaxima,
			BigDecimal percentualValoracoesNegativasMinima,
			BigDecimal percentualValoracoesNegativasMaxima,
			String tipoItemConhecimento,
			Collection<Projeto> projetos,
			Collection<KAtividade> atividades,
			Collection<Tema> temas)
			{		

		StringBuilder sb = new StringBuilder();

		// Prepara campo expressao
		sb.append("%");
		sb.append(expressao);
		sb.append("%");
		expressao = sb.toString();

		List<LicaoAprendida> licoes = getEntityManager().
				createQuery("from LicaoAprendida where " +
						"((titulo like :expressao or :expressao = NULL) or " +
						"(resumo like :expressao or :expressao = NULL) or " +
						"(aplicabilidade like :expressao or :expressao = NULL)) and " +
						"(dataCriacao BETWEEN :dataCriacaoInicial AND :dataCriacaoFinal) and " + 
						"((dataUltimoAcesso BETWEEN :dataUltimoAcessoInicial AND :dataUltimoAcessoFinal) or dataUltimoAcesso = NULL) and " +
						"(quantidadeAcessos >= :quantidadeAcessosMinimo or :quantidadeAcessosMinimo = NULL) and " +
						"(quantidadeAcessos <= :quantidadeAcessosMaximo or :quantidadeAcessosMaximo = NULL)" +
						" ")
						.setParameter("expressao", expressao)
						.setParameter("dataCriacaoInicial", dataCriacaoInicial, TemporalType.DATE)
						.setParameter("dataCriacaoFinal", dataCriacaoFinal, TemporalType.DATE)
						.setParameter("dataUltimoAcessoInicial", dataUltimoAcessoInicial, TemporalType.DATE)
						.setParameter("dataUltimoAcessoFinal", dataUltimoAcessoFinal, TemporalType.DATE)
						.setParameter("quantidadeAcessosMinimo", quantidadeAcessosMinimo)
						.setParameter("quantidadeAcessosMaximo", quantidadeAcessosMaximo)
						.getResultList();

		// Filtros
		List<LicaoAprendida> licoesRemovidas = new ArrayList<LicaoAprendida>();
		if (!licoesRemovidas.isEmpty()){
			for (LicaoAprendida licaoAprendida : licoes){

				//quantidade de valoracoes minima
				if (quantidadeValoracoesMinimo != null) {
					if (licaoAprendida.getValoracoes().size()<quantidadeValoracoesMinimo)
						licoesRemovidas.add(licaoAprendida);
				}

				//quantidade de valoracoes maxima
				if (quantidadeValoracoesMaximo != null) {
					if (licaoAprendida.getValoracoes().size()>quantidadeValoracoesMaximo)
						licoesRemovidas.add(licaoAprendida);
				}

				// percentual valoracoes positivas
				try {
					float percentualPositivas = licaoAprendida.quantidadeValoracoes(1) / licaoAprendida.getValoracoes().size();

					if (percentualValoracoesPositivasMinima != null) {
						if (percentualPositivas < percentualValoracoesPositivasMinima.floatValue())
							licoesRemovidas.add(licaoAprendida);
					}


					if (percentualValoracoesPositivasMaxima != null) {
						if (percentualPositivas > percentualValoracoesPositivasMaxima.floatValue())
							licoesRemovidas.add(licaoAprendida);
					}

				} catch (Exception e) {
					System.out.println("Divisão por zero.");

					float percentualPositivas = 0;

					if (percentualValoracoesPositivasMinima != null) {
						if (percentualPositivas < percentualValoracoesPositivasMinima.floatValue())
							licoesRemovidas.add(licaoAprendida);
					}


					if (percentualValoracoesPositivasMaxima != null) {
						if (percentualPositivas > percentualValoracoesPositivasMaxima.floatValue())
							licoesRemovidas.add(licaoAprendida);
					}
				}

				try {
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
				} catch (Exception e) {
					System.out.println("Divisão por zero.");

					float percentualNegativas = 0;

					if (percentualValoracoesNegativasMinima != null) {
						if (percentualNegativas < percentualValoracoesNegativasMinima.floatValue())
							licoesRemovidas.add(licaoAprendida);
					}

					if (percentualValoracoesNegativasMaxima != null) {
						if (percentualNegativas > percentualValoracoesNegativasMaxima.floatValue())
							licoesRemovidas.add(licaoAprendida);
					}

				}

				// projetos
				if (!projetos.isEmpty()) {
					Set<Projeto> projetosRetidos = new HashSet<Projeto>();
					for (Projeto projeto : licaoAprendida.getProjetos()){
						projetosRetidos.add(projeto);
					}
					projetosRetidos.retainAll(projetos);
					if (projetosRetidos.size() == 0) {
						licoesRemovidas.add(licaoAprendida);
					}
				}

				// atividades
				if (!atividades.isEmpty()) {
					Set<KAtividade> atividadesRetidas = new HashSet<KAtividade>();
					for (KAtividade atividade : licaoAprendida.getkAtividades()){
						atividadesRetidas.add(atividade);
					}
					atividadesRetidas.retainAll(atividades);
					if (atividadesRetidas.size() == 0) {
						licoesRemovidas.add(licaoAprendida);
					}
				}

				// temas
				if (!temas.isEmpty()) {
					Set<Tema> temasRetidos = new HashSet<Tema>();
					for (Tema tema : licaoAprendida.getTemas()){
						temasRetidos.add(tema);
					}
					temasRetidos.retainAll(temas);
					if (temasRetidos.size() == 0) {
						licoesRemovidas.add(licaoAprendida);
					}
				}
			}	
		}

		// Remove licoes nao pertecentes
		licoes.removeAll(licoesRemovidas);

		return licoes; 

			}

	@SuppressWarnings("unchecked")
	public List<LicaoAprendida> recuperarOrdenadoPorQuantidadeAcesso(){
		return getEntityManager().
				createQuery("from LicaoAprendida order by quantidadeAcessos").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<LicaoAprendida> recuperarOrdenadoPorDataCriacaoMaisRecente(){
		return getEntityManager().
				createQuery("from LicaoAprendida order by dataCriacao desc").getResultList();
	}

	public int recuperarQuantidadeTotal(){
		return getEntityManager().createQuery("from LicaoAprendida").getResultList().size();
	}

}
