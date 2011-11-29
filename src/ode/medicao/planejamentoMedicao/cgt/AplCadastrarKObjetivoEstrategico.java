package ode.medicao.planejamentoMedicao.cgt;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoSoftware;
import ode.medicao.planejamentoMedicao.cgd.KObjetivoEstrategicoDAO;
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
		Collection<KObjetivoMedicao> objMedicao = kObjetivoEstrategicoDAO.getObjetivosMedicao(objeto);
		return !objeto.getObjetivoSoftware().isEmpty() || !objMedicao.isEmpty();
	}
	
	

}
