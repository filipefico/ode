package ode.conhecimento.processo.Cgt;

import nucleo.comuns.excecao.NucleoExcecao;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import ode.conhecimento.processo.Cdp.KProcesso;
import ode.conhecimento.processo.Cgd.KProcessoDAO;
import ode.nucleo.cgd.NucleoDAOBase;
import ode.nucleo.cgt.NucleoAplCadastroBaseImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKProcessoImpl extends NucleoAplCadastroBaseImpl<KProcesso> 
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
		getNucleoDaoBase().merge(objeto);
		//Retorna objetoPersistido;
		return objeto;
	}
	
	@Override
	public NucleoDAOBase<KProcesso> getNucleoDaoBase() {
		return kProcessoDAO;
	}
}
