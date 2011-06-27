package ode.conhecimento.requisito.Cgt;

import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;
import nucleo.comuns.excecao.NucleoExcecao;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.persistencia.NucleoDAOBase;
import ode.conhecimento.requisito.Cdp.KTipoRequisito;
import ode.conhecimento.requisito.Cgd.KTipoRequisitoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
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

	@Override
	public NucleoDAOBase<KTipoRequisito> getNucleoDaoBase() {
		// TODO Auto-generated method stub
		return kTipoRequisitoDAO;
	}
}
