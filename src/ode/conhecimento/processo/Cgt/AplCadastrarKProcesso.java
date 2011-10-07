package ode.conhecimento.processo.cgt;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.conhecimento.processo.cgd.KProcessoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKProcesso extends AplCRUD<KProcesso> {

	@Autowired
	private KProcessoDAO kProcessoDAO;

	public KProcessoDAO getKProcessoDAO() {
		return kProcessoDAO;
	}

	public void setKProcessoDAO(KProcessoDAO kProcessoDAO) {
		this.kProcessoDAO = kProcessoDAO;
	}

	protected KProcesso alterarDados(KProcesso objeto) throws NucleoRegraNegocioExcecao {
		getNucleoDaoBase().atualizar(objeto);
		//Retorna objetoPersistido;
		return objeto;
	}
	
	@Override
	public DAOBase<KProcesso> getNucleoDaoBase() {
		return kProcessoDAO;
	}
}
