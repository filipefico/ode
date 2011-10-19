package ode.conhecimentoMedicao.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import ode.conhecimentoMedicao.cdp.KObjetivoMedicao;
import ode.conhecimentoMedicao.cdp.KObjetivoSoftware;
import ode._infraestruturaBase.cgd.DAOBaseImpl;


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
