package ode.conhecimento.processo.cgt;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.conhecimento.processo.cdp.KResultadoProcesso;
import ode.conhecimento.processo.cgd.KResultadoProcessoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKResultadoProcesso extends AplCRUD<KResultadoProcesso> {

	@Autowired
	private KResultadoProcessoDAO kResultadoProcessoDAO;

	public KResultadoProcessoDAO getKResultadoProcessoDAO() {
		return kResultadoProcessoDAO;
	}
	
	public void setKResultadoProcessoDAO(KResultadoProcessoDAO kResultadoProcessoDAO) {
		this.kResultadoProcessoDAO = kResultadoProcessoDAO;
	}
	
	protected KResultadoProcesso alterarDados(KResultadoProcesso objeto) throws NucleoRegraNegocioExcecao {
		getNucleoDaoBase().atualizar(objeto);
		return objeto;
	}

	@Override
	public DAOBase<KResultadoProcesso> getNucleoDaoBase() {
		return kResultadoProcessoDAO;
	}
}
