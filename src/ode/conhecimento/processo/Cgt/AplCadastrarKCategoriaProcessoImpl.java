package ode.conhecimento.processo.Cgt;

import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;
import nucleo.comuns.excecao.NucleoExcecao;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.persistencia.NucleoDAOBase;
import ode.conhecimento.processo.Cdp.KCategoriaProcesso;
import ode.conhecimento.processo.Cgd.KCategoriaProcessoDAO;
import ode.conhecimento.processo.Cgt.AplCadastrarKCategoriaProcesso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKCategoriaProcessoImpl extends NucleoAplCadastroBaseImpl<KCategoriaProcesso> 
				implements AplCadastrarKCategoriaProcesso {

	@Autowired
	private KCategoriaProcessoDAO kCategoriaProcessoDAO;

	public KCategoriaProcessoDAO getKCategoriaProcessoDAO() {
		return kCategoriaProcessoDAO;
	}

	public void setKCategoriaProcessoDAO(KCategoriaProcessoDAO kCategoriaProcessoDAO) {
		this.kCategoriaProcessoDAO = kCategoriaProcessoDAO;
	}

	@Override
	protected void copiarValor(KCategoriaProcesso objetoFonte, KCategoriaProcesso objetoDestino) {
		objetoDestino.setNome(objetoFonte.getNome());
		objetoDestino.setDescricao(objetoFonte.getDescricao());
	}

	protected KCategoriaProcesso alterarDados(KCategoriaProcesso objeto) throws NucleoRegraNegocioExcecao {
		getNucleoDaoBase().merge(objeto);
		//Retorna objetoPersistido;
		return objeto;
	}

	@Override
	public NucleoDAOBase<KCategoriaProcesso> getNucleoDaoBase() {
		return kCategoriaProcessoDAO;
	}
}
