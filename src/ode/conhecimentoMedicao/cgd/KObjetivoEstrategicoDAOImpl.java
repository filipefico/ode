package ode.conhecimentoMedicao.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import ode.conhecimentoMedicao.cdp.KObjetivoEstrategico;
import ode.conhecimentoMedicao.cdp.KObjetivoMedicao;
import ode.conhecimentoMedicao.cdp.KObjetivoSoftware;
import ode._infraestruturaBase.cgd.DAOBaseImpl;

@Repository
public class KObjetivoEstrategicoDAOImpl extends
		DAOBaseImpl<KObjetivoEstrategico> implements
		KObjetivoEstrategicoDAO {

	public Collection<KObjetivoSoftware> getObjetivosSoftware(
			KObjetivoEstrategico obj) {
		return getEntityManager()
				.createQuery(
						"from KObjetivoSoftware as soft join soft.objetivosEstrategicos as estr where estr.id = "
								+ obj.getId()).getResultList();
	}

	public Collection<KObjetivoMedicao> getObjetivosMedicao(
			KObjetivoEstrategico obj) {
		return getEntityManager().createQuery(
				"from KObjetivoMedicao med join med.objetivosEstrategicos as estr where estr.id = "
						+ obj.getId()).getResultList();
	}

}
