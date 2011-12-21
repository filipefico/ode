package ode.medicao.planejamentoMedicao.cgt;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoSoftware;
import ode.medicao.planejamentoMedicao.cgd.ObjetivoEstrategicoDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;

@Service
public class AplCadastrarObjetivoEstrategico extends
		AplCRUD<ObjetivoEstrategico> {

	@Autowired
	ObjetivoEstrategicoDAO kObjetivoEstrategicoDAO;

	@Override
	public DAOBase<ObjetivoEstrategico> getNucleoDaoBase() {
		return kObjetivoEstrategicoDAO;
	}

	@Override
	protected void antesExcluir(ObjetivoEstrategico objeto)
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

	public boolean RelacionamentoSoftwareMedicao(ObjetivoEstrategico objeto){
		Collection<ObjetivoMedicao> objMedicao = kObjetivoEstrategicoDAO.getObjetivosMedicao(objeto);
		return !objeto.getObjetivoSoftware().isEmpty() || !objMedicao.isEmpty();
	}
	
	

}
