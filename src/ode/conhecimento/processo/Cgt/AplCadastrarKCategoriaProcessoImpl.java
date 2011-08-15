package ode.conhecimento.processo.Cgt;

import ode.conhecimento.processo.Cdp.KCategoriaProcesso;
import ode.conhecimento.processo.Cgd.KCategoriaProcessoDAO;
import ode.conhecimento.processo.Cgt.AplCadastrarKCategoriaProcesso;
import ode.nucleo.crud.cgd.DAOBase;
import ode.nucleo.crud.cgt.AplBaseImpl;
import ode.nucleo.excecao.NucleoExcecao;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKCategoriaProcessoImpl extends AplBaseImpl<KCategoriaProcesso> 
				implements AplCadastrarKCategoriaProcesso {

	@Autowired
	private KCategoriaProcessoDAO kCategoriaProcessoDAO;

	public KCategoriaProcessoDAO getKCategoriaProcessoDAO() {
		return kCategoriaProcessoDAO;
	}

	public void setKCategoriaProcessoDAO(KCategoriaProcessoDAO kCategoriaProcessoDAO) {
		this.kCategoriaProcessoDAO = kCategoriaProcessoDAO;
	}

	protected KCategoriaProcesso alterarDados(KCategoriaProcesso objeto) throws NucleoRegraNegocioExcecao {
		getNucleoDaoBase().merge(objeto);
		//Retorna objetoPersistido;
		return objeto;
	}

	@Override
	public DAOBase<KCategoriaProcesso> getNucleoDaoBase() {
		return kCategoriaProcessoDAO;
	}
}
