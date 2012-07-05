package ode._controleRecursoHumano.cgd;

import java.util.Collection;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cgd.DAOBase;

public interface RecursoHumanoDAO extends DAOBase<RecursoHumano> {

	public Collection<RecursoHumano> recuperarRecursosHumanosAtivos();
	
}
