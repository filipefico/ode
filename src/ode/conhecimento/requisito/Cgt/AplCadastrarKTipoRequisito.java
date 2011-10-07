package ode.conhecimento.requisito.cgt;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.conhecimento.requisito.cdp.KTipoRequisito;
import ode.conhecimento.requisito.cgd.KTipoRequisitoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKTipoRequisito extends AplCRUD<KTipoRequisito> {

	@Autowired
	private KTipoRequisitoDAO kTipoRequisitoDAO;

	public KTipoRequisitoDAO getKTipoRequisitoDAO() {
		return kTipoRequisitoDAO;
	}

	public void setKTipoRequisitoDAO(KTipoRequisitoDAO kTipoRequisitoDAO) {
		this.kTipoRequisitoDAO = kTipoRequisitoDAO;
	}

	protected KTipoRequisito alterarDados(KTipoRequisito objeto) throws NucleoRegraNegocioExcecao {
		getNucleoDaoBase().atualizar(objeto);
		//Retorna objetoPersistido;
		return objeto;
	}

	@Override
	public DAOBase<KTipoRequisito> getNucleoDaoBase() {
		// TODO Auto-generated method stub
		return kTipoRequisitoDAO;
	}
}
