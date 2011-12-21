package ode.medicao.planejamentoMedicao.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoSoftware;


@Repository
public class ObjetivoSoftwareDAOImpl extends DAOBaseImpl<ObjetivoSoftware> implements ObjetivoSoftwareDAO{

	@Override
	public Collection<ObjetivoMedicao> getObjetivosMedicao(
			ObjetivoSoftware objetoCadastro) {
		return getEntityManager().createQuery(
				"from ObjetivoMedicao med join med.objetivosSoftware as soft where soft.id = "
						+ objetoCadastro.getId()).getResultList();
	}
	
}
