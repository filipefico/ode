package ode.medicao.planejamentoMedicao.cgt;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode.medicao.planejamentoMedicao.cdp.NecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoSoftware;
import ode.medicao.planejamentoMedicao.cgd.ObjetivoMedicaoDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;

@Service
public class AplCadastrarObjetivoMedicao extends AplCRUD<ObjetivoMedicao> {

	@Autowired
	ObjetivoMedicaoDAO objetivoMedicaoDAO;

	@Override
	public DAOBase<ObjetivoMedicao> getNucleoDaoBase() {
		return objetivoMedicaoDAO;
	}

	@Override
	protected void antesExcluir(ObjetivoMedicao objeto)
			throws NucleoRegraNegocioExcecao {
		if (RelacionamentoNecessidadeInformacao(objeto)) {
			throw new NucleoRegraNegocioExcecao(
					NucleoMensagens
							.getMensagem(NucleoMensagens.MSG_OBJETIVO_EXCLUSAO_ERRO),
					null);
		}
	}

	public boolean RelacionamentoNecessidadeInformacao(ObjetivoMedicao objeto) {
		Collection<NecessidadeInformacao> necessidadeInformacao = objetivoMedicaoDAO
				.getObjetivosSoftware(objeto);
		return !necessidadeInformacao.isEmpty();
	}

	public void antesSalvar(ObjetivoMedicao objeto)
			throws NucleoRegraNegocioExcecao {
		if (objeto.getObjetivosEstrategicos().isEmpty()) {
			throw new NucleoRegraNegocioExcecao(
					NucleoMensagens
							.getMensagem(NucleoMensagens.MSG_OBJETIVO_ESTRATEGICO_EMPTY_ERRO),
					null);
		}
		if (objeto.getObjetivosSoftware().isEmpty()) {
			throw new NucleoRegraNegocioExcecao(
					NucleoMensagens
							.getMensagem(NucleoMensagens.MSG_OBJETIVO_SOFTWARE_EMPTY_ERRO),
					null);
		}
	}

}
