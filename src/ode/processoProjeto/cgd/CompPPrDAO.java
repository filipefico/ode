package ode.processoProjeto.cgd;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.processoProjeto.cdp.CompPPr;

public interface CompPPrDAO extends DAOBase<CompPPr>{

	Collection recuperarTodosFinalizados(Class compPPr);
	
}
