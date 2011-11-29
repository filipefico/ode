package ode.conhecimentoMedicao.cgd;

import java.util.Collection;

import ode.conhecimentoMedicao.cdp.KElementoMensuravel;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cdp.TipoEntidadeMensuravel;
import ode._infraestruturaBase.cgd.DAOBase;

public interface KElementoMensuravelDAO extends DAOBase<KElementoMensuravel>{

	public Collection<KMedida> getRelacionamentoMedida(KElementoMensuravel objeto);


}
