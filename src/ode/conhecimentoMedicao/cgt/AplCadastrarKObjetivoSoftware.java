package ode.conhecimentoMedicao.cgt;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode.conhecimentoMedicao.cdp.KObjetivoEstrategico;
import ode.conhecimentoMedicao.cdp.KObjetivoMedicao;
import ode.conhecimentoMedicao.cdp.KObjetivoSoftware;
import ode.conhecimentoMedicao.cgd.KObjetivoSoftwareDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;

@Service
public class AplCadastrarKObjetivoSoftware extends AplCRUD<KObjetivoSoftware> {

	@Autowired
	KObjetivoSoftwareDAO objetivoSoftwareDAO;

	@Override
	public DAOBase<KObjetivoSoftware> getNucleoDaoBase() {
		return objetivoSoftwareDAO;
	}

	@Override
	protected void antesExcluir(KObjetivoSoftware objeto)
			throws NucleoRegraNegocioExcecao {
		if (RelacionamentoMedicao(objeto)) {
			throw new NucleoRegraNegocioExcecao(
					NucleoMensagens
							.getMensagem(NucleoMensagens.MSG_OBJETIVO_EXCLUSAO_ERRO),
					null);
		}
	}

	public boolean RelacionamentoMedicao(KObjetivoSoftware objetoCadastro) {
		Collection<KObjetivoMedicao> objMedicao = objetivoSoftwareDAO
				.getObjetivosMedicao(objetoCadastro);
		return !objMedicao.isEmpty();
	}

	public void antesSalvar(KObjetivoSoftware objeto)
			throws NucleoRegraNegocioExcecao {
		if (objeto.getObjetivosEstrategicos().isEmpty()) {
			throw new NucleoRegraNegocioExcecao(
					NucleoMensagens
							.getMensagem(NucleoMensagens.MSG_OBJETIVO_ESTRATEGICO_EMPTY_ERRO),
					null);
		}
	}

}
