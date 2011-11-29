package ode.conhecimentoMedicao.cgd;

import java.util.Collection;

import ode.conhecimentoMedicao.cdp.KMedida;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoMedicao;
import ode._infraestruturaBase.cgd.DAOBase;

public interface KMedidaDAO extends DAOBase<KMedida>{

	Collection<KMedida> recuperarPorObjetivo(KObjetivoMedicao obj);

}
