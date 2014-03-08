package ode.middlewareIssueTracker.cgd;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.middlewareIssueTracker.cdp.ConfiguracaoMantis;

public interface ConfiguracaoMantisDAO extends DAOBase<ConfiguracaoMantis> {

	public ConfiguracaoMantis recuperarConfiguracaoMantisPadrao();
	
}
