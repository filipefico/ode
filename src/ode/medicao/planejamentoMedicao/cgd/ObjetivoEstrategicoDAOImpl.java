package ode.medicao.planejamentoMedicao.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoSoftware;

@Repository
public class ObjetivoEstrategicoDAOImpl extends
		DAOBaseImpl<ObjetivoEstrategico> implements
		ObjetivoEstrategicoDAO {

	public Collection<ObjetivoMedicao> getObjetivosMedicao(
			ObjetivoEstrategico obj) {
		return getEntityManager().createQuery(
				"select med from ObjetivoMedicao med join med.objetivosEstrategicos as estr where estr.id = "
						+ obj.getId()).getResultList();
	}
	
}
