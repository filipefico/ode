package ode.middlewareIssueTracker.cgd;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.agenda.cdp.Owner;
import ode.middlewareIssueTracker.cdp.UsuarioMantis;

public interface UsuarioMantisDAO extends DAOBase<UsuarioMantis> {

	public UsuarioMantis recuperarUsuarioMantisDoUsuarioAtual();
	
}
