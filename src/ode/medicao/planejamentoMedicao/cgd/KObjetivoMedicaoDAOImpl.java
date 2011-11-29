package ode.medicao.planejamentoMedicao.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.medicao.planejamentoMedicao.cdp.KNecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoMedicao;


@Repository
public class KObjetivoMedicaoDAOImpl extends DAOBaseImpl<KObjetivoMedicao>
																implements KObjetivoMedicaoDAO{

	@Override
	public Collection<KNecessidadeInformacao> getObjetivosSoftware(
			KObjetivoMedicao objeto) {
			return getEntityManager()
					.createQuery(
							"from KNecessidadeInformacao as neces join neces.objetivosMedicao as med where med.id = "
									+ objeto.getId()).getResultList();
	}
}
