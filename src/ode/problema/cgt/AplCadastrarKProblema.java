package ode.problema.cgt;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.problema.cdp.KProblema;
import ode.problema.cgd.KProblemaDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional (rollbackFor = NucleoExcecao.class)
public class AplCadastrarKProblema extends AplCRUD<KProblema>{
@Autowired
KProblemaDAO kproblema;
	
//protected void antesExcluir(T objeto) throws NucleoRegraNegocioExcecao {
	// Problemas com ocorr�ncias registradas em projetos n�o podem ser exclu�dos; 
//}	
	
	@Override
	public DAOBase<KProblema> getNucleoDaoBase() {
		// TODO Auto-generated method stub
		return kproblema;
	}

}
