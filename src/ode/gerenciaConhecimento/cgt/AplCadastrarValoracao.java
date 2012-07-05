package ode.gerenciaConhecimento.cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.gerenciaConhecimento.cdp.Valoracao;
import ode.gerenciaConhecimento.cgd.ValoracaoDAO;

@Service
@Transactional(rollbackFor = NucleoRegraNegocioExcecao.class)
public class AplCadastrarValoracao extends AplCRUD<Valoracao> {

	@Autowired
	ValoracaoDAO valoracaoDAO;
	
	@Override
	public DAOBase<Valoracao> getNucleoDaoBase() {
		return valoracaoDAO;
	}

}
