package ode.conhecimentoMedicao.cgd;

import java.util.Collection;

import ode.conhecimentoMedicao.cdp.KObjetivoEstrategico;
import ode.conhecimentoMedicao.cdp.KObjetivoMedicao;
import ode.conhecimentoMedicao.cdp.KObjetivoSoftware;
import ode._infraestruturaBase.cgd.DAOBase;

public interface KObjetivoEstrategicoDAO extends DAOBase<KObjetivoEstrategico> {

	public Collection<KObjetivoSoftware> getObjetivosSoftware(KObjetivoEstrategico obj);
	
	public Collection<KObjetivoMedicao> getObjetivosMedicao(KObjetivoEstrategico obj);
}
