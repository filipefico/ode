package ode.conhecimento.processo.Cgt;

import ode.conhecimento.processo.Cdp.KResultadoProcesso;
import ode.conhecimento.processo.Cgd.KResultadoProcessoDAO;
import ode.conhecimento.processo.Cgt.AplCadastrarKResultadoProcesso;
import ode.nucleo.crud.cgd.DAOBase;
import ode.nucleo.crud.cgt.AplBaseImpl;
import ode.nucleo.excecao.NucleoExcecao;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKResultadoProcessoImpl extends AplBaseImpl<KResultadoProcesso> implements AplCadastrarKResultadoProcesso{

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
