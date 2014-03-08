package ode.middlewareIssueTracker.cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.middlewareIssueTracker.cdp.ConfiguracaoMantis;
import ode.middlewareIssueTracker.cgd.ConfiguracaoMantisDAO;


@Service
public class AplConfiguracaoMantis extends AplCRUD<ConfiguracaoMantis> {

	@Autowired
	ConfiguracaoMantisDAO configuracaoMantisDAO;
	
	@Override
	public DAOBase<ConfiguracaoMantis> getNucleoDaoBase() {
		// TODO Auto-generated method stub
		return configuracaoMantisDAO;
	}
	
	
	public ConfiguracaoMantis recuperarConfiguracaoMantisPadrao(){
		 ConfiguracaoMantis configuracaoMantis = configuracaoMantisDAO.recuperarConfiguracaoMantisPadrao();
		 return configuracaoMantis;
	}

}
