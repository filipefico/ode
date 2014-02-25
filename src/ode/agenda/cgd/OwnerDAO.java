package ode.agenda.cgd;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cgd.DAOBase;
import ode.agenda.cdp.Owner;

public interface OwnerDAO extends DAOBase<Owner> {
	
	public Owner recuperarOwnerDoRHAtual();	

	public Owner recuperarOwnerDoRH(RecursoHumano rh);

}
