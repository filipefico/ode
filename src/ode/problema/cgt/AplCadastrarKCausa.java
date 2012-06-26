package ode.problema.cgt;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.problema.cdp.KCausa;
import ode.problema.cgd.KCausaDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional (rollbackFor = NucleoExcecao.class)
public class AplCadastrarKCausa extends AplCRUD<KCausa>  {
@Autowired
KCausaDAO kcausa;
	
//protected void antesExcluir(T objeto) throws NucleoRegraNegocioExcecao {
	//lembrar que causas que estejam registradas em projetos não podem ser excluidas; 
//}
	
	
	
	@Override
	public DAOBase<KCausa> getNucleoDaoBase() {
		// TODO Auto-generated method stub
		return kcausa;
	}

}
