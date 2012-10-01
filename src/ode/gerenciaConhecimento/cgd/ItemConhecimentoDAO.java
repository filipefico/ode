package ode.gerenciaConhecimento.cgd;

import java.util.Collection;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cgd.DAOBase;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;

public interface ItemConhecimentoDAO extends DAOBase<ItemConhecimento> {
	
	public Collection<ItemConhecimento> recuperarItensConhecimentoPendentesPorUsuarioAtual();
	
	public Collection<ItemConhecimento> recuperarItensCriados();
	
	public Collection<ItemConhecimento> recuperarItensConhecimentoValorados();
	
	public Collection<ItemConhecimento> recuperarItensConhecimentoAvaliados();
	
	public Collection<ItemConhecimento> recuperarItensCriadosPorUsuarioAtual(RecursoHumano rh);
	
	public Collection<ItemConhecimento> recuperarItensAvaliadosPorUsuarioAtual(RecursoHumano rh);
	
	public Collection<ItemConhecimento> recuperarItensValoradosPorUsuarioAtual(RecursoHumano rh);

}
