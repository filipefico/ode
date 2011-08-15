package ode.principal.cgt;

import ode.nucleo.crud.cgd.DAOBase;
import ode.nucleo.crud.cgt.AplBaseImpl;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;
import ode.principal.cdp.ConfiguracoesSistema;
import ode.principal.cgd.ConfiguracoesSistemaDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoRegraNegocioExcecao.class)	
public class AplCadastrarConfiguracoesSistemaImpl extends
		AplBaseImpl<ConfiguracoesSistema> implements
		AplCadastrarConfiguracoesSistema {

	@Autowired
	private ConfiguracoesSistemaDAO configuracoesSistemaDAO;
	
	@Override
	public DAOBase<ConfiguracoesSistema> getNucleoDaoBase() {
		return configuracoesSistemaDAO;
	}
	
}
