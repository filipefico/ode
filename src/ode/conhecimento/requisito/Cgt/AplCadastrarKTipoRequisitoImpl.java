package ode.conhecimento.requisito.Cgt;

import org.springframework.beans.factory.annotation.Autowired;

import nucleo.comuns.aplicacao.NucleoAplCadastroBase;
import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import ode.conhecimento.requisito.Cgt.AplCadastrarKTipoRequisito;
import ode.conhecimento.requisito.Cgd.KTipoRequisitoDAO;
import ode.conhecimento.requisito.Cdp.KTipoRequisito;

public class AplCadastrarKTipoRequisitoImpl extends NucleoAplCadastroBaseImpl<KTipoRequisito> 
				implements AplCadastrarKTipoRequisito {

	@Autowired
	private KTipoRequisitoDAO kTipoRequisitoDAO;

	public KTipoRequisitoDAO getKTipoRequisitoDAO() {
		return kTipoRequisitoDAO;
	}

	public void setKTipoRequisitoDAO(KTipoRequisitoDAO kTipoRequisitoDAO) {
		this.kTipoRequisitoDAO = kTipoRequisitoDAO;
	}

	@Override
	protected void copiarValor(KTipoRequisito objetoFonte, KTipoRequisito objetoDestino) {
		objetoDestino.setNome(objetoFonte.getNome());
		objetoDestino.setDescricao(objetoFonte.getDescricao());
		objetoDestino.setEhFuncional(objetoFonte.getEhFuncional());
		objetoDestino.setSuperKTipoRequisito(objetoFonte.getSuperKTipoRequisito());
	}

	protected KTipoRequisito alterarDados(KTipoRequisito objeto) throws NucleoRegraNegocioExcecao {
		getNucleoDaoBase().merge(objeto);
		//Retorna objetoPersistido;
		return objeto;
	}
}
