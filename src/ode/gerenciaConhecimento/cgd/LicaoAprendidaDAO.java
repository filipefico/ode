package ode.gerenciaConhecimento.cgd;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.controleProjeto.cdp.Projeto;
import ode.gerenciaConhecimento.cdp.LicaoAprendida;
import ode.gerenciaConhecimento.cdp.Tema;

public interface LicaoAprendidaDAO extends DAOBase<LicaoAprendida> {

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
			List<Projeto> projetos,
			List<KAtividade> atividades,
			List<Tema> temas);
}
