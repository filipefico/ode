package ode.conhecimentoMedicao.cgt;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ode.conhecimentoMedicao.cdp.KObjetivoEstrategico;
import ode.conhecimentoMedicao.cdp.KObjetivoMedicao;
import ode.conhecimentoMedicao.cdp.KObjetivoSoftware;
import ode.conhecimentoMedicao.cgd.KObjetivoEstrategicoDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;

@Service
public class AplCadastrarKObjetivoEstrategico extends
		AplCRUD<KObjetivoEstrategico> {

	@Autowired
	KObjetivoEstrategicoDAO kObjetivoEstrategicoDAO;

	@Override
	public DAOBase<KObjetivoEstrategico> getNucleoDaoBase() {
		return kObjetivoEstrategicoDAO;
	}

	@Override
	protected void antesExcluir(KObjetivoEstrategico objeto)
			throws NucleoRegraNegocioExcecao {
		// Verifica se o KObjetivoEstrategico nao tem nenhum objetivo de
		// software nem de medicao ligado a ele
		if (RelacionamentoSoftwareMedicao(objeto)) {
			throw new NucleoRegraNegocioExcecao(
					NucleoMensagens
							.getMensagem(NucleoMensagens.MSG_OBJETIVO_EXCLUSAO_ERRO),
					null);
		}
	}

	public boolean RelacionamentoSoftwareMedicao(KObjetivoEstrategico objeto){
		Collection<KObjetivoSoftware> objSoftware = kObjetivoEstrategicoDAO.getObjetivosSoftware(objeto);
		Collection<KObjetivoMedicao> objMedicao = kObjetivoEstrategicoDAO.getObjetivosMedicao(objeto);
		return !objSoftware.isEmpty() || !objMedicao.isEmpty();
	}
	
	

}
