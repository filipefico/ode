package ode.conhecimentoMedicao.cgd;

import java.util.Collection;

import ode.conhecimentoMedicao.cdp.KEscala;
import ode.conhecimentoMedicao.cdp.KValorEscala;
import ode._infraestruturaBase.cgd.DAOBase;

public interface KValorEscalaDAO extends DAOBase<KValorEscala>{

	public Collection<KEscala> getRelacaoComEscala(KValorEscala objeto);

}
