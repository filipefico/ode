package ode.controleCaracteristica.cgt;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.controleCaracteristica.cdp.PossivelValorNaoOrdenado;
import ode.controleCaracteristica.cdp.Similaridade;
import ode.controleCaracteristica.cgd.PossivelValorNaoOrdenadoDAO;
import ode.controleCaracteristica.cgd.SimilaridadeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.zkplus.spring.SpringUtil;

@Service("AplCadastrarPossivelValorNaoOrdenado")
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarPossivelValorNaoOrdenado  extends AplCRUD<PossivelValorNaoOrdenado>{

	@Autowired
	private PossivelValorNaoOrdenadoDAO possivelValorNaoOrdenadoDAO;
	
	@Override
	public DAOBase<PossivelValorNaoOrdenado> getNucleoDaoBase() {
		// TODO Auto-generated method stub
		return possivelValorNaoOrdenadoDAO;
	}
	
	public PossivelValorNaoOrdenadoDAO getPossivelValorNaoOrdenadoDAO() {
		return possivelValorNaoOrdenadoDAO;
	}

	public void setPossivelValorNaoOrdenadoDAO(PossivelValorNaoOrdenadoDAO possivelValorNaoOrdenadoDAO) {
		this.possivelValorNaoOrdenadoDAO = possivelValorNaoOrdenadoDAO;
	}

	public void salvarSimilaridade(Similaridade similaridade){
				
		SimilaridadeDAO similaridadeDAO = (SimilaridadeDAO) SpringUtil.getApplicationContext().getBean(SimilaridadeDAO.class);
		similaridadeDAO.salvar(similaridade);
		
		
	}

}
