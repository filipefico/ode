package ode.conhecimentoMedicao.cgd;

import java.util.Collection;

import ode.conhecimentoMedicao.cdp.KObjetivoMedicao;
import ode.conhecimentoMedicao.cdp.KObjetivoSoftware;
import ode._infraestruturaBase.cgd.DAOBase;

public interface KObjetivoSoftwareDAO extends DAOBase<KObjetivoSoftware>{

	Collection<KObjetivoMedicao> getObjetivosMedicao(
			KObjetivoSoftware objetoCadastro);
	
}
