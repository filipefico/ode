package ode.controleCaracteristica.cgd;

import java.util.Collection;

import ode.controleCaracteristica.cdp.Caracteristica;
import ode.controleCaracteristica.cdp.EnumTipoItemSoftware;
import ode._infraestruturaBase.cgd.DAOBase;

public interface CaracteristicaDAO extends DAOBase<Caracteristica>{

	Collection<Caracteristica> recuperarCaracteristicaPorTipoSoftware(
			EnumTipoItemSoftware tipo);
	
	public boolean estaNumaPespectivaAnalise(long id);

}