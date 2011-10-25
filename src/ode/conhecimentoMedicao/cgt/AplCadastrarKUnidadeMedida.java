package ode.conhecimentoMedicao.cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode.conhecimentoMedicao.cdp.KNecessidadeInformacao;
import ode.conhecimentoMedicao.cdp.KUnidadeMedida;
import ode.conhecimentoMedicao.cgd.KUnidadeMedidaDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.cgt.AplCRUD;

@Service
public class AplCadastrarKUnidadeMedida extends AplCRUD<KUnidadeMedida>{

	@Autowired
	KUnidadeMedidaDAO dao;
	
	@Override
	public DAOBase<KUnidadeMedida> getNucleoDaoBase() {
		return dao;
	}
	
	public boolean estaRelacionadoComMedida(KUnidadeMedida objeto) {
		return !dao.getKMedidasRelacionadas(objeto).isEmpty();
	}
	
	@Override
	protected void antesExcluir(KUnidadeMedida objeto) throws NucleoRegraNegocioExcecao{
		if(estaRelacionadoComMedida(objeto)){
			throw new NucleoRegraNegocioExcecao(NucleoMensagens.getMensagem(NucleoMensagens.MSG_MEDIDAS_RELACIONADAS_EXCLUSAO_ERRO));
		}
	}

}
