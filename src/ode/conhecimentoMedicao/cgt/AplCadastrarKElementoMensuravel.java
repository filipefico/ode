package ode.conhecimentoMedicao.cgt;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode.conhecimentoMedicao.cdp.KElementoMensuravel;
import ode.conhecimentoMedicao.cdp.TipoEntidadeMensuravel;
import ode.conhecimentoMedicao.cgd.KElementoMensuravelDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.cgt.AplCRUD;

@Service
public class AplCadastrarKElementoMensuravel extends AplCRUD<KElementoMensuravel>{

	@Autowired
	KElementoMensuravelDAO dao;
	
	@Override
	public DAOBase<KElementoMensuravel> getNucleoDaoBase() {
		return dao;
	}
	
	public boolean relacionamentoComMedida(KElementoMensuravel objeto){
		return !dao.getRelacionamentoMedida(objeto).isEmpty();
	}

	@Override
	public void antesExcluir(KElementoMensuravel objeto) throws NucleoRegraNegocioExcecao{
		if(relacionamentoComMedida(objeto)){
			throw new NucleoRegraNegocioExcecao(NucleoMensagens.getMensagem(NucleoMensagens.MSG_MEDIDAS_RELACIONADAS_EXCLUSAO_ERRO),null);
		}
	}
	
	@Override
	public void antesSalvar(KElementoMensuravel objeto)throws NucleoRegraNegocioExcecao{
		if(objeto.getTipoEntidadeMensuravel().isEmpty()){
			throw new NucleoRegraNegocioExcecao(NucleoMensagens.getMensagem(NucleoMensagens.MSG_TIPOENTIDADE_EMPTY_ERRO),null);
		}
	}
	

}
