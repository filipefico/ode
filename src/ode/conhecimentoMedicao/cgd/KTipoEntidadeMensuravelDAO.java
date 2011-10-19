package ode.conhecimentoMedicao.cgd;

import java.util.Collection;

import ode.conhecimentoMedicao.cdp.KElementoMensuravel;
import ode.conhecimentoMedicao.cdp.KTipoEntidadeMensuravel;
import ode._infraestruturaBase.cgd.DAOBase;

public interface KTipoEntidadeMensuravelDAO extends DAOBase<KTipoEntidadeMensuravel>{
	public Collection<KElementoMensuravel> recuperarPorTipo(
			KTipoEntidadeMensuravel tipo) ;
}
