package ode.controleCaracteristica.cgd;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.controleCaracteristica.cdp.Importancia;

public interface ImportanciaDAO extends DAOBase<Importancia>{
	
	public Collection<Importancia> estaNumaImportancia(long id);
}