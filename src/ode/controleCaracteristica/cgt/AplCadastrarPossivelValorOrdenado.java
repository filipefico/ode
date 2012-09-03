package ode.controleCaracteristica.cgt;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.controleCaracteristica.cdp.PossivelValorOrdenado;
import ode.controleCaracteristica.cgd.PossivelValorOrdenadoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AplCadastrarPossivelValorOrdenado")
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarPossivelValorOrdenado extends AplCRUD<PossivelValorOrdenado>{

	@Autowired
	private PossivelValorOrdenadoDAO possivelValorOrdenadoDAO;
	
	@Override
	public DAOBase<PossivelValorOrdenado> getNucleoDaoBase() {
		// TODO Auto-generated method stub
		return  possivelValorOrdenadoDAO;
	}
	
	public PossivelValorOrdenadoDAO getPossivelValorOrdenadoDAO() {
		return  possivelValorOrdenadoDAO;
	}

	public void setPossivelValorOrdenadoDAO(PossivelValorOrdenadoDAO possivelValorOrdenadoDAO) {
		this.possivelValorOrdenadoDAO = possivelValorOrdenadoDAO;
	}

}

