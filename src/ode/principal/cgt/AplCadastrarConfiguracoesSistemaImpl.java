package ode.principal.cgt;

import ode.nucleo.cgd.NucleoDAOBase;
import ode.nucleo.cgt.NucleoAplCadastroBaseImpl;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;
import ode.principal.cdp.ConfiguracoesSistema;
import ode.principal.cgd.ConfiguracoesSistemaDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoRegraNegocioExcecao.class)	
public class AplCadastrarConfiguracoesSistemaImpl extends
		NucleoAplCadastroBaseImpl<ConfiguracoesSistema> implements
		AplCadastrarConfiguracoesSistema {

	@Autowired
	private ConfiguracoesSistemaDAO configuracoesSistemaDAO;
	
	@Override
	public NucleoDAOBase<ConfiguracoesSistema> getNucleoDaoBase() {
		return configuracoesSistemaDAO;
	}
	
}
