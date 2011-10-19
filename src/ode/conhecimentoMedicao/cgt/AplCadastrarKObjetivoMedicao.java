package ode.conhecimentoMedicao.cgt;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode.conhecimentoMedicao.cdp.KNecessidadeInformacao;
import ode.conhecimentoMedicao.cdp.KObjetivoEstrategico;
import ode.conhecimentoMedicao.cdp.KObjetivoMedicao;
import ode.conhecimentoMedicao.cdp.KObjetivoSoftware;
import ode.conhecimentoMedicao.cgd.KObjetivoMedicaoDAO;
import ode.conhecimentoMedicao.cgd.KObjetivoSoftwareDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;

@Service
public class AplCadastrarKObjetivoMedicao extends AplCRUD<KObjetivoMedicao> {

	@Autowired
	KObjetivoMedicaoDAO objetivoMedicaoDAO;

	@Override
	public DAOBase<KObjetivoMedicao> getNucleoDaoBase() {
		return objetivoMedicaoDAO;
	}

	@Override
	protected void antesExcluir(KObjetivoMedicao objeto)
			throws NucleoRegraNegocioExcecao {
		if (RelacionamentoNecessidadeInformacao(objeto)) {
			throw new NucleoRegraNegocioExcecao(
					NucleoMensagens
							.getMensagem(NucleoMensagens.MSG_OBJETIVO_EXCLUSAO_ERRO),
					null);
		}
	}

	public boolean RelacionamentoNecessidadeInformacao(KObjetivoMedicao objeto) {
		Collection<KNecessidadeInformacao> necessidadeInformacao = objetivoMedicaoDAO
				.getObjetivosSoftware(objeto);
		return !necessidadeInformacao.isEmpty();
	}

	public void antesSalvar(KObjetivoMedicao objeto)
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
