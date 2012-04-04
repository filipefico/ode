package ode.processoPadrao.cgd;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.processoPadrao.cdp.CompPP;

public interface CompPPDAO extends DAOBase<CompPP> {

	Collection recuperarTodosFinalizados(Class compPP);

}
