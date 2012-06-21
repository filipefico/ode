package ode.gerenciaConhecimento.cgt;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import ode.gerenciaConhecimento.cdp.LicaoAprendida;
import ode.gerenciaConhecimento.cdp.Tema;
import ode.gerenciaConhecimento.cgd.LicaoAprendidaDAO;

@Service
@Transactional(rollbackFor = NucleoRegraNegocioExcecao.class)
public class AplCadastrarLicaoAprendida extends AplCRUD<LicaoAprendida> {

	@Autowired
	private LicaoAprendidaDAO licaoAprendidaDAO;

	@Override
	public DAOBase<LicaoAprendida> getNucleoDaoBase() {
		return licaoAprendidaDAO;
	}

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
			Collection<Tema> temas){

		return licaoAprendidaDAO.buscar(expressao, dataCriacaoInicial, dataCriacaoFinal, dataUltimoAcessoInicial, dataUltimoAcessoFinal, quantidadeAcessosMinimo, quantidadeAcessosMaximo, quantidadeValoracoesMinimo, quantidadeValoracoesMaximo, percentualValoracoesPositivasMinima, percentualValoracoesPositivasMaxima, percentualValoracoesNegativasMinima, percentualValoracoesNegativasMaxima, tipoItemConhecimento, projetos, atividades, temas);
	}

	public List<LicaoAprendida> recuperarOrdenadoPorQuantidadeAcesso(){

		List<LicaoAprendida> licoes = this.licaoAprendidaDAO.recuperarOrdenadoPorQuantidadeAcesso(); 

		// Recupera os tres primeiros
		List<LicaoAprendida> novasLicoes = new ArrayList<LicaoAprendida>();
		if (licoes.size() >= 1)
			novasLicoes.add(licoes.get(0));
		if (licoes.size() >= 2)
			novasLicoes.add(licoes.get(1));
		if (licoes.size() >= 3)
			novasLicoes.add(licoes.get(2));

		return novasLicoes;
	}

	public List<LicaoAprendida> recuperarOrdenadoPorDataCriacaoMaisRecente(){

		List<LicaoAprendida> licoes = this.licaoAprendidaDAO.recuperarOrdenadoPorDataCriacaoMaisRecente(); 

		// Recupera os tres primeiros
		List<LicaoAprendida> novasLicoes = new ArrayList<LicaoAprendida>();
		if (licoes.size() >= 1)
			novasLicoes.add(licoes.get(0));
		if (licoes.size() >= 2)
			novasLicoes.add(licoes.get(1));
		if (licoes.size() >= 3)
			novasLicoes.add(licoes.get(2));

		return novasLicoes;
	}

	public int recuperarQuantidadeTotal(){
		return this.licaoAprendidaDAO.recuperarQuantidadeTotal();
	}

}
