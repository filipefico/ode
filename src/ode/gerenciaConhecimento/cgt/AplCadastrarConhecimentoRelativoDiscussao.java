package ode.gerenciaConhecimento.cgt;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.controleProjeto.cdp.Projeto;
import ode.gerenciaConhecimento.cdp.ConhecimentoRelativoDiscussao;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;
import ode.gerenciaConhecimento.cdp.Tema;
import ode.gerenciaConhecimento.cgd.ConhecimentoRelativoDiscussaoDAO;

@Service
@Transactional(rollbackFor = NucleoRegraNegocioExcecao.class)
public class AplCadastrarConhecimentoRelativoDiscussao extends
		AplCRUD<ConhecimentoRelativoDiscussao> {

	@Autowired
	private ConhecimentoRelativoDiscussaoDAO conhecimentoRelativoDiscussaoDAO;
	
	@Override
	public DAOBase<ConhecimentoRelativoDiscussao> getNucleoDaoBase() {
		return conhecimentoRelativoDiscussaoDAO;
	}
	
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
			Collection<Projeto> projetos,
			Collection<KAtividade> atividades,
			Collection<Tema> temas){
		return conhecimentoRelativoDiscussaoDAO.buscar(expressao, dataCriacaoInicial, dataCriacaoFinal, dataUltimaAtualizacaoInicial, dataUltimaAtualizacaoFinal, dataUltimoAcessoInicial, dataUltimoAcessoFinal, quantidadeAcessosMinimo, quantidadeAcessosMaximo, quantidadeValoracoesMinimo, quantidadeValoracoesMaximo, percentualValoracoesPositivasMinima, percentualValoracoesPositivasMaxima, percentualValoracoesNegativasMinima, percentualValoracoesNegativasMaxima, tipoItemConhecimento, projetos, atividades, temas);
	}
}
