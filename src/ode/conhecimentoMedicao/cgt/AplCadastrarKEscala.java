package ode.conhecimentoMedicao.cgt;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode.conhecimentoMedicao.cdp.KEscala;
import ode.conhecimentoMedicao.cdp.TipoEscala;
import ode.conhecimentoMedicao.cgd.KEscalaDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.cgt.AplCRUD;

@Service
public class AplCadastrarKEscala extends AplCRUD<KEscala>{

	@Autowired
	KEscalaDAO dao;
	
	@Override
	public DAOBase<KEscala> getNucleoDaoBase() {
		return dao;
	}
	
	public Collection<KEscala> recuperarPorTipo(TipoEscala tipo){
		return dao.recuperarPorTipo(tipo);
	}

	public boolean existeMedidaRelacionada(KEscala objeto) {
		return dao.getMedidasRelacionadas(objeto).isEmpty();
	}
	
	@Override
	protected void antesExcluir(KEscala objeto) throws NucleoRegraNegocioExcecao{
		if(existeMedidaRelacionada(objeto)){
			throw new NucleoRegraNegocioExcecao(NucleoMensagens.getMensagem(NucleoMensagens.MSG_MEDIDAS_RELACIONADAS_EXCLUSAO_ERRO));
		}
	}

}
