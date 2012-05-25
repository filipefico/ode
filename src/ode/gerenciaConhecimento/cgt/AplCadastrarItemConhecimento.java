package ode.gerenciaConhecimento.cgt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.controleProjeto.cdp.Projeto;
import ode.gerenciaConhecimento.cdp.ConhecimentoRelativoDiscussao;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;
import ode.gerenciaConhecimento.cdp.LicaoAprendida;
import ode.gerenciaConhecimento.cdp.Tema;
import ode.gerenciaConhecimento.cgd.ItemConhecimentoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoRegraNegocioExcecao.class)
public class AplCadastrarItemConhecimento extends AplCRUD<ItemConhecimento> {

	@Autowired
	ItemConhecimentoDAO itemConhecimentoDAO;

	@Autowired
	AplCadastrarConhecimentoRelativoDiscussao aplCadastrarConhecimentoRelativoDiscussao;

	@Autowired
	AplCadastrarLicaoAprendida aplCadastrarLicaoAprendida;

	@Override
	public DAOBase<ItemConhecimento> getNucleoDaoBase() {
		return itemConhecimentoDAO;
	}

	public List<ItemConhecimento> buscar(String expressao,
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
			Collection<Tema> temas){

		List<ItemConhecimento> itens = new ArrayList<ItemConhecimento>();

		if (tipoItemConhecimento.compareToIgnoreCase("Conhecimento Relativo a Discussão") == 0 || tipoItemConhecimento.compareToIgnoreCase("Todos") == 0) {

			List<ConhecimentoRelativoDiscussao> itensCRD = aplCadastrarConhecimentoRelativoDiscussao.buscar(expressao, dataCriacaoInicial, dataCriacaoFinal, dataUltimaAtualizacaoInicial, dataUltimaAtualizacaoFinal, dataUltimoAcessoInicial, dataUltimoAcessoFinal, quantidadeAcessosMinimo, quantidadeAcessosMaximo, quantidadeValoracoesMinimo, quantidadeValoracoesMaximo, percentualValoracoesPositivasMinima, percentualValoracoesPositivasMaxima, percentualValoracoesNegativasMinima, percentualValoracoesNegativasMaxima, tipoItemConhecimento, projetos, atividades, temas);
			for (ConhecimentoRelativoDiscussao itemCRD : itensCRD)
				itens.add(itemCRD);

		}

		if (tipoItemConhecimento.compareToIgnoreCase("Lição Aprendida") == 0 || tipoItemConhecimento.compareToIgnoreCase("Todos") == 0) {

			List<LicaoAprendida> itensLA = aplCadastrarLicaoAprendida.buscar(expressao, dataCriacaoInicial, dataCriacaoFinal, dataUltimaAtualizacaoInicial, dataUltimaAtualizacaoFinal, dataUltimoAcessoInicial, dataUltimoAcessoFinal, quantidadeAcessosMinimo, quantidadeAcessosMaximo, quantidadeValoracoesMinimo, quantidadeValoracoesMaximo, percentualValoracoesPositivasMinima, percentualValoracoesPositivasMaxima, percentualValoracoesNegativasMinima, percentualValoracoesNegativasMaxima, tipoItemConhecimento, projetos, atividades, temas);
			for (LicaoAprendida itemLA : itensLA)
				itens.add(itemLA);

		}

		return itens;
	}

}
