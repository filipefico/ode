package ode.medicao.planejamentoMedicao.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoSoftware;

@Repository
public class KObjetivoEstrategicoDAOImpl extends
		DAOBaseImpl<KObjetivoEstrategico> implements
		KObjetivoEstrategicoDAO {

	public Collection<KObjetivoMedicao> getObjetivosMedicao(
			KObjetivoEstrategico obj) {
		return getEntityManager().createQuery(
				"select med from KObjetivoMedicao med join med.objetivosEstrategicos as estr where estr.id = "
						+ obj.getId()).getResultList();
	}
	
}
