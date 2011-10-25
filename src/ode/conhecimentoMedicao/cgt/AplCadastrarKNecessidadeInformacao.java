package ode.conhecimentoMedicao.cgt;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;

import ode.conhecimentoMedicao.cdp.KNecessidadeInformacao;
import ode.conhecimentoMedicao.cdp.KObjetivoEstrategico;
import ode.conhecimentoMedicao.cgd.KNecessidadeInformacaoDAO;
import ode.conhecimentoMedicao.cgd.KObjetivoEstrategicoDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;

@Service
public class AplCadastrarKNecessidadeInformacao extends
		AplCRUD<KNecessidadeInformacao> {

	@Autowired
	KNecessidadeInformacaoDAO dao;

	@Override
	public void antesExcluir(KNecessidadeInformacao objeto)
			throws NucleoRegraNegocioExcecao {
		if (estaRelacionadoComMedida(objeto)) {
			throw new NucleoRegraNegocioExcecao(
					NucleoMensagens
							.getMensagem(NucleoMensagens.MSG_MEDIDAS_RELACIONADAS_EXCLUSAO_ERRO),
					null);
		}
	}

	public boolean estaRelacionadoComMedida(KNecessidadeInformacao objeto) {
		return !dao.getKMedidasRelacionadas(objeto).isEmpty();
	}

	public void antesSalvar(KNecessidadeInformacao objeto)
			throws NucleoRegraNegocioExcecao {
		if (objeto.getObjetivosMedicao().isEmpty()) {
			throw new NucleoRegraNegocioExcecao(
					NucleoMensagens
							.getMensagem(NucleoMensagens.MSG_EMPTY_MEDICAO_INFORMACAO),
					null);
		}
		if(objeto.getProcessos().isEmpty()){
			throw new NucleoRegraNegocioExcecao(
					NucleoMensagens
							.getMensagem(NucleoMensagens.MSG_EMPTY_PROCESSO_INFORMACAO));
		}
	}

	@Override
	public DAOBase<KNecessidadeInformacao> getNucleoDaoBase() {
		return dao;
	}

}
