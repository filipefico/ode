package ode.gerenciaConhecimento.cgd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.TemporalType;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.controleProjeto.cdp.Projeto;
import ode.gerenciaConhecimento.cdp.ConhecimentoRelativoDiscussao;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;
import ode.gerenciaConhecimento.cdp.LicaoAprendida;
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

		List<ConhecimentoRelativoDiscussao> itens = getEntityManager().
		createQuery("from ConhecimentoRelativoDiscussao where estado = :estado and " +
				"((titulo like :expressao or :expressao = NULL) or " +
				"(resumo like :expressao or :expressao = NULL) or " +
				"(aplicabilidade like :expressao or :expressao = NULL)) and " +
				"(dataCriacao BETWEEN :dataCriacaoInicial AND :dataCriacaoFinal) and " + 
				"((dataUltimoAcesso BETWEEN :dataUltimoAcessoInicial AND :dataUltimoAcessoFinal) or dataUltimoAcesso = NULL) and " +
				"(quantidadeAcessos >= :quantidadeAcessosMinimo or :quantidadeAcessosMinimo = NULL) and " +
				"(quantidadeAcessos <= :quantidadeAcessosMaximo or :quantidadeAcessosMaximo = NULL)" +
		" ")
		.setParameter("estado", ItemConhecimento.ESTADO_DISPONIVEL)
		.setParameter("expressao", expressao)
		.setParameter("dataCriacaoInicial", dataCriacaoInicial, TemporalType.DATE)
		.setParameter("dataCriacaoFinal", dataCriacaoFinal, TemporalType.DATE)
		.setParameter("dataUltimoAcessoInicial", dataUltimoAcessoInicial, TemporalType.DATE)
		.setParameter("dataUltimoAcessoFinal", dataUltimoAcessoFinal, TemporalType.DATE)
		.setParameter("quantidadeAcessosMinimo", quantidadeAcessosMinimo)
		.setParameter("quantidadeAcessosMaximo", quantidadeAcessosMaximo)
		.getResultList();

		// Filtros
		List<ConhecimentoRelativoDiscussao> itensRemovidos = new ArrayList<ConhecimentoRelativoDiscussao>();
		if (!itens.isEmpty()){
			for (ConhecimentoRelativoDiscussao item : itens){

				//quantidade de valoracoes minima
				if (quantidadeValoracoesMinimo != null) {
					if (item.getValoracoes().size()<quantidadeValoracoesMinimo)
						itensRemovidos.add(item);
				}

				//quantidade de valoracoes maxima
				if (quantidadeValoracoesMaximo != null) {
					if (item.getValoracoes().size()>quantidadeValoracoesMaximo)
						itensRemovidos.add(item);
				}

				// percentual valoracoes positivas
				try {
					float percentualPositivas = item.quantidadeValoracoes(1) / item.getValoracoes().size();

					if (percentualValoracoesPositivasMinima != null) {
						if (percentualPositivas < percentualValoracoesPositivasMinima.floatValue())
							itensRemovidos.add(item);
					}


					if (percentualValoracoesPositivasMaxima != null) {
						if (percentualPositivas > percentualValoracoesPositivasMaxima.floatValue())
							itensRemovidos.add(item);
					}

				} catch (Exception e) {
					System.out.println("Divisão por zero.");

					float percentualPositivas = 0;

					if (percentualValoracoesPositivasMinima != null) {
						if (percentualPositivas < percentualValoracoesPositivasMinima.floatValue())
							itensRemovidos.add(item);
					}


					if (percentualValoracoesPositivasMaxima != null) {
						if (percentualPositivas > percentualValoracoesPositivasMaxima.floatValue())
							itensRemovidos.add(item);
					}
				}

				try {
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
				} catch (Exception e) {
					System.out.println("Divisão por zero.");

					float percentualNegativas = 0;

					if (percentualValoracoesNegativasMinima != null) {
						if (percentualNegativas < percentualValoracoesNegativasMinima.floatValue())
							itensRemovidos.add(item);
					}

					if (percentualValoracoesNegativasMaxima != null) {
						if (percentualNegativas > percentualValoracoesNegativasMaxima.floatValue())
							itensRemovidos.add(item);
					}

				}

				// projetos
				if (!projetos.isEmpty()) {
					Set<Projeto> projetosRetidos = new HashSet<Projeto>();
					for (Projeto projeto : item.getProjetos()){
						projetosRetidos.add(projeto);
					}
					projetosRetidos.retainAll(projetos);
					if (projetosRetidos.size() == 0) {
						itensRemovidos.add(item);
					}
				}

				// atividades
				if (!atividades.isEmpty()) {
					Set<KAtividade> atividadesRetidas = new HashSet<KAtividade>();
					for (KAtividade atividade : item.getkAtividades()){
						atividadesRetidas.add(atividade);
					}
					atividadesRetidas.retainAll(atividades);
					if (atividadesRetidas.size() == 0) {
						itensRemovidos.add(item);
					}
				}

				// temas
				if (!temas.isEmpty()) {
					Set<Tema> temasRetidos = new HashSet<Tema>();
					for (Tema tema : item.getTemas()){
						temasRetidos.add(tema);
					}
					temasRetidos.retainAll(temas);
					if (temasRetidos.size() == 0) {
						itensRemovidos.add(item);
					}
				}

			}	
		}

		// Remove itens nao pertecentes
		itens.removeAll(itensRemovidos);

		return itens; 

			}

	@SuppressWarnings("unchecked")
	public List<ConhecimentoRelativoDiscussao> recuperarOrdenadoPorQuantidadeAcesso(){

		String estado = ItemConhecimento.ESTADO_DISPONIVEL; 

		return getEntityManager().
		createQuery("from ConhecimentoRelativoDiscussao where estado = :estado order by quantidadeAcessos")
		.setParameter("estado", estado)
		.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<ConhecimentoRelativoDiscussao> recuperarOrdenadoPorDataCriacaoMaisRecente(){
		String estado = ItemConhecimento.ESTADO_DISPONIVEL; 

		return getEntityManager().
		createQuery("from ConhecimentoRelativoDiscussao where estado = :estado order by dataCriacao")
		.setParameter("estado", estado)
		.getResultList();
	}

	public int recuperarQuantidadeTotal(){
		String estado = ItemConhecimento.ESTADO_DISPONIVEL; 

		return getEntityManager().createQuery("from ConhecimentoRelativoDiscussao where estado = :estado")
		.setParameter("estado", estado)
		.getResultList()
		.size();
	}
	
	public List<ItemConhecimento> recuperarConhecimentoRelativoDiscussaoDisponiveis(){
		
		String estado = ItemConhecimento.ESTADO_DISPONIVEL; 

		return getEntityManager().createQuery("from ConhecimentoRelativoDiscussao where estado = :estado")
		.setParameter("estado", estado)
		.getResultList();
	}

}
