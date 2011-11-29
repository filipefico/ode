package ode.medicao.planejamentoMedicao.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoSoftware;


@Repository
public class KObjetivoSoftwareDAOImpl extends DAOBaseImpl<KObjetivoSoftware> implements KObjetivoSoftwareDAO{

	@Override
	public Collection<KObjetivoMedicao> getObjetivosMedicao(
			KObjetivoSoftware objetoCadastro) {
		return getEntityManager().createQuery(
				"from KObjetivoMedicao med join med.objetivosSoftware as soft where soft.id = "
						+ objetoCadastro.getId()).getResultList();
	}
	
}
