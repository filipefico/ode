package ode._controleRecursoHumano.cgd;

import java.util.Collection;
import java.util.List;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cgd.DAOBase;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;
import ode.gerenciaConhecimento.cdp.Tema;

public interface RecursoHumanoDAO extends DAOBase<RecursoHumano> {

	public Collection<RecursoHumano> recuperarRecursosHumanosAtivos();
	
	public Collection<RecursoHumano> recuperarPorTemasItemCriadoAvaliadoValorado(List<Tema> temas, ItemConhecimento itemCriado, ItemConhecimento itemAvaliado, ItemConhecimento itemValorado);
	
	public Collection<RecursoHumano> recuperarPorCargo(KRecursoHumano kRecursoHumano);
	
}
