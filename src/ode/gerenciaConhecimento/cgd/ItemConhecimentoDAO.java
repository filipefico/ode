package ode.gerenciaConhecimento.cgd;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;

public interface ItemConhecimentoDAO extends DAOBase<ItemConhecimento> {
	
	public Collection<ItemConhecimento> recuperarItensConhecimentoPendentesPorUsuarioAtual();
	
	public Collection<ItemConhecimento> recuperarItensCriados();
	
	public Collection<ItemConhecimento> recuperarItensConhecimentoValorados();
	
	public Collection<ItemConhecimento> recuperarItensConhecimentoAvaliados();

}
