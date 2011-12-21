package ode.medicao.planejamentoMedicao.cgt;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode.medicao.planejamentoMedicao.cdp.ObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoSoftware;
import ode.medicao.planejamentoMedicao.cgd.ObjetivoSoftwareDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;

@Service
public class AplCadastrarObjetivoSoftware extends AplCRUD<ObjetivoSoftware> {

	@Autowired
	ObjetivoSoftwareDAO objetivoSoftwareDAO;

	@Override
	public DAOBase<ObjetivoSoftware> getNucleoDaoBase() {
		return objetivoSoftwareDAO;
	}

	@Override
	protected void antesExcluir(ObjetivoSoftware objeto)
			throws NucleoRegraNegocioExcecao {
		if (RelacionamentoMedicao(objeto)) {
			throw new NucleoRegraNegocioExcecao(
					NucleoMensagens
							.getMensagem(NucleoMensagens.MSG_OBJETIVO_EXCLUSAO_ERRO),
					null);
		}
	}

	public boolean RelacionamentoMedicao(ObjetivoSoftware objetoCadastro) {
		Collection<ObjetivoMedicao> objMedicao = objetivoSoftwareDAO
				.getObjetivosMedicao(objetoCadastro);
		return !objMedicao.isEmpty();
	}

	public void antesSalvar(ObjetivoSoftware objeto)
			throws NucleoRegraNegocioExcecao {
		if (objeto.getObjetivoEstrategico().isEmpty()) {
			throw new NucleoRegraNegocioExcecao(
					NucleoMensagens
							.getMensagem(NucleoMensagens.MSG_OBJETIVO_ESTRATEGICO_EMPTY_ERRO),
					null);
		}
	}

}
