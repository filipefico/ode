package ode.conhecimentoMedicao.cgd;

import java.util.Collection;

import ode.conhecimentoMedicao.cdp.KEscala;
import ode.conhecimentoMedicao.cdp.TipoEscala;
import ode._infraestruturaBase.cgd.DAOBase;

public interface KEscalaDAO extends DAOBase<KEscala>{

	Collection<KEscala> recuperarPorTipo(TipoEscala tipo);

}
