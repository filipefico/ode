package ode.controleCaracteristica.cgt;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.controleCaracteristica.cdp.PerspectivaAnalise;
import ode.controleCaracteristica.cgd.PerspectivaAnaliseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarPerspectivaAnalise extends AplCRUD<PerspectivaAnalise> {

	@Autowired
	private PerspectivaAnaliseDAO perspectivaAnaliseDAO;

	public PerspectivaAnaliseDAO getPerspectivaAnaliseDAO() {
		return perspectivaAnaliseDAO;
	}

	public void setPerspectivaAnaliseDAO(PerspectivaAnaliseDAO perspectivaAnaliseDAO) {
		this.perspectivaAnaliseDAO = perspectivaAnaliseDAO;
	}

	protected PerspectivaAnalise alterarDados(PerspectivaAnalise objeto) throws NucleoRegraNegocioExcecao {
		getNucleoDaoBase().atualizar(objeto);
		//Retorna objetoPersistido;
		return objeto;
	}

	@Override
	public DAOBase<PerspectivaAnalise> getNucleoDaoBase() {
		// TODO Auto-generated method stub
		return perspectivaAnaliseDAO;
	}


}
