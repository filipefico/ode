package ode.conhecimento.processo.Cgt;

import ode.conhecimento.processo.Cdp.KProcesso;
import ode.conhecimento.processo.Cgd.KProcessoDAO;
import ode.nucleo.crud.cgd.DAOBase;
import ode.nucleo.crud.cgt.AplBaseImpl;
import ode.nucleo.excecao.NucleoExcecao;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKProcessoImpl extends AplBaseImpl<KProcesso> 
				implements AplCadastrarKProcesso {

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
