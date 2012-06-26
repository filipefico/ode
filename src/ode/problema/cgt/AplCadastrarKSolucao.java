package ode.problema.cgt;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.problema.cdp.KSolucao;
import ode.problema.cgd.KSolucaoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional (rollbackFor = NucleoExcecao.class)
public class AplCadastrarKSolucao extends AplCRUD<KSolucao>  {
@Autowired
KSolucaoDAO ksolucao;

//protected void antesExcluir(T objeto) throws NucleoRegraNegocioExcecao {
	//Soluções  que tenham sido utilizadas  em projetos  não podem ser excluídas.
//}
	
	@Override
	public DAOBase<KSolucao> getNucleoDaoBase() {
		// TODO Auto-generated method stub
		return ksolucao;
	}

}
