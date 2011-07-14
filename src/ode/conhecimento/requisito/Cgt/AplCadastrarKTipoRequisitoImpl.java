package ode.conhecimento.requisito.Cgt;

import ode.conhecimento.requisito.Cdp.KTipoRequisito;
import ode.conhecimento.requisito.Cgd.KTipoRequisitoDAO;
import ode.nucleo.cgd.NucleoDAOBase;
import ode.nucleo.cgt.NucleoAplCadastroBaseImpl;
import ode.nucleo.excecao.NucleoExcecao;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;

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
