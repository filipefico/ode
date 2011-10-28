package ode.conhecimento.processo.cgt;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.conhecimento.processo.cdp.KCategoriaProcesso;
import ode.conhecimento.processo.cgd.KCategoriaProcessoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AplCadastrarKCategoriaProcesso")
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKCategoriaProcesso extends AplCRUD<KCategoriaProcesso> {

	@Autowired
	private KCategoriaProcessoDAO kCategoriaProcessoDAO;

	public KCategoriaProcessoDAO getKCategoriaProcessoDAO() {
		return kCategoriaProcessoDAO;
	}

	public void setKCategoriaProcessoDAO(KCategoriaProcessoDAO kCategoriaProcessoDAO) {
		this.kCategoriaProcessoDAO = kCategoriaProcessoDAO;
	}

	protected KCategoriaProcesso alterarDados(KCategoriaProcesso objeto) throws NucleoRegraNegocioExcecao {
		getNucleoDaoBase().atualizar(objeto);
		//Retorna objetoPersistido;
		return objeto;
	}

	@Override
	public DAOBase<KCategoriaProcesso> getNucleoDaoBase() {
		return kCategoriaProcessoDAO;
	}
}
