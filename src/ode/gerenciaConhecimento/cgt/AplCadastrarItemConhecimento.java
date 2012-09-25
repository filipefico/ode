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
import ode.gerenciaConhecimento.cdp.Avaliacao;
import ode.gerenciaConhecimento.cdp.ConhecimentoRelativoDiscussao;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;
import ode.gerenciaConhecimento.cdp.LicaoAprendida;
import ode.gerenciaConhecimento.cdp.Tema;
import ode.gerenciaConhecimento.cdp.Valoracao;
import ode.gerenciaConhecimento.cgd.ItemConhecimentoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.icu.util.GregorianCalendar;

@Service
@Transactional(rollbackFor = NucleoRegraNegocioExcecao.class)
public class AplCadastrarItemConhecimento extends AplCRUD<ItemConhecimento> {

	@Autowired
	ItemConhecimentoDAO itemConhecimentoDAO;
	
	@Autowired
	AplCadastrarValoracao aplCadastrarValoracao;

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
			Date dataUltimoAcessoInicial,
			Date dataUltimoAcessoFinal,
			Integer quantidadeAcessosMinimo,
			Integer quantidadeAcessosMaximo,
			Integer quantidadeValoracoesMinimo,
			Integer quantidadeValoracoesMaximo,
			BigDecimal percentualValoracoesPositivasMinima,
			BigDecimal percentualValoracoesPositivasMaxima,
			BigDecimal percentualValoracoesNegativasMinima,
			BigDecimal percentualValoracoesNegativasMaxima,
			String tipoItemConhecimento,
			Collection<Projeto> projetos,
			Collection<KAtividade> atividades,
			Collection<Tema> temas){
		
		//////////////////////////////////////
		// Tratamento dos dados
		/////////////////////////////////////
		if (dataCriacaoInicial == null)
			dataCriacaoInicial = menorData();
		if (dataCriacaoFinal == null)
			dataCriacaoFinal = maiorData();
		if (dataUltimoAcessoInicial == null)
			dataUltimoAcessoInicial = menorData();
		if (dataUltimoAcessoFinal == null)
			dataUltimoAcessoFinal = maiorData();
		

		List<ItemConhecimento> itens = new ArrayList<ItemConhecimento>();

		if (tipoItemConhecimento.compareToIgnoreCase("Conhecimento Relativo a Discussão") == 0 || tipoItemConhecimento.compareToIgnoreCase("Todos") == 0) {

			List<ConhecimentoRelativoDiscussao> itensCRD = aplCadastrarConhecimentoRelativoDiscussao.buscar(expressao, dataCriacaoInicial, dataCriacaoFinal, dataUltimoAcessoInicial, dataUltimoAcessoFinal, recuperarLong(quantidadeAcessosMinimo), recuperarLong(quantidadeAcessosMaximo), recuperarLong(quantidadeValoracoesMinimo), recuperarLong(quantidadeValoracoesMaximo), percentualValoracoesPositivasMinima, percentualValoracoesPositivasMaxima, percentualValoracoesNegativasMinima, percentualValoracoesNegativasMaxima, tipoItemConhecimento, projetos, atividades, temas);
			for (ConhecimentoRelativoDiscussao itemCRD : itensCRD)
				itens.add(itemCRD);

		}

		if (tipoItemConhecimento.compareToIgnoreCase("Lição Aprendida") == 0 || tipoItemConhecimento.compareToIgnoreCase("Todos") == 0) {

			List<LicaoAprendida> itensLA = aplCadastrarLicaoAprendida.buscar(expressao, dataCriacaoInicial, dataCriacaoFinal, dataUltimoAcessoInicial, dataUltimoAcessoFinal, recuperarLong(quantidadeAcessosMinimo), recuperarLong(quantidadeAcessosMaximo), recuperarLong(quantidadeValoracoesMinimo), recuperarLong(quantidadeValoracoesMaximo), percentualValoracoesPositivasMinima, percentualValoracoesPositivasMaxima, percentualValoracoesNegativasMinima, percentualValoracoesNegativasMaxima, tipoItemConhecimento, projetos, atividades, temas);
			for (LicaoAprendida itemLA : itensLA)
				itens.add(itemLA);

		}

		return itens;
	}
	
	public Date menorData(){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.set(GregorianCalendar.YEAR,1);
		calendar.set(GregorianCalendar.MONTH,1);
		calendar.set(GregorianCalendar.DAY_OF_MONTH,1);
		return calendar.getTime();
	}
	
	public Date maiorData(){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.set(GregorianCalendar.YEAR,3000);
		calendar.set(GregorianCalendar.MONTH,1);
		calendar.set(GregorianCalendar.DAY_OF_MONTH,1);
		return calendar.getTime();
	}
	
	public Long recuperarLong(Integer i){
		return i == null ? null : new Long(i);
	}

	public void adicionarValoracao(Valoracao valoracao, ItemConhecimento itemConhecimento){
		try {
		itemConhecimento = this.recuperarPorId(itemConhecimento.getId());
		
		valoracao.setItemConhecimentoAvaliado(itemConhecimento);
		
		aplCadastrarValoracao.salvar(valoracao);
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void adicionarAvaliacao(Avaliacao avaliacao, ItemConhecimento conhecimento){
		conhecimento = itemConhecimentoDAO.recuperarPorId(conhecimento.getId());
		avaliacao.setItemConhecimentoAvaliado(conhecimento);
		conhecimento.getAvaliacoes().add(avaliacao);
	}
	
	public Collection<ItemConhecimento> recuperarItensConhecimentoPendentesPorUsuarioAtual(){
		return this.itemConhecimentoDAO.recuperarItensConhecimentoPendentesPorUsuarioAtual();
	}
	
	public Collection<ItemConhecimento> recuperarItensCriados(){ 
		return this.itemConhecimentoDAO.recuperarItensCriados();
	}
	
	public Collection<ItemConhecimento> recuperarItensConhecimentoValorados(){
		return this.itemConhecimentoDAO.recuperarItensConhecimentoValorados();
	}
		
}
