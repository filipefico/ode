package ode.conhecimentoMedicao.cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode.conhecimentoMedicao.cdp.KEscala;
import ode.conhecimentoMedicao.cdp.KValorEscala;
import ode.conhecimentoMedicao.cgd.KValorEscalaDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.cgt.AplCRUD;

@Service
public class AplCadastrarKValorEscala extends AplCRUD<KValorEscala>{

	@Autowired
	KValorEscalaDAO dao;
	
	@Override
	public DAOBase<KValorEscala> getNucleoDaoBase() {
		return dao;
	}

	public boolean temRelacaoComEscala(KValorEscala objeto) {
		return dao.getRelacaoComEscala(objeto).isEmpty();
	}
	
	@Override
	protected void antesExcluir(KValorEscala objeto)
			throws NucleoRegraNegocioExcecao {
		if(temRelacaoComEscala(objeto)){
			throw new NucleoRegraNegocioExcecao(NucleoMensagens.getMensagem(NucleoMensagens.MSG_ESCALAS_RELACIONADAS_EXCLUSAO_ERRO));
		}
	}

}
