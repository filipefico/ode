package ode.medicao.planejamentoMedicao.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.medicao.planejamentoMedicao.cdp.NecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;


@Repository
public class ObjetivoMedicaoDAOImpl extends DAOBaseImpl<ObjetivoMedicao>
																implements ObjetivoMedicaoDAO{

	@Override
	public Collection<NecessidadeInformacao> getObjetivosSoftware(
			ObjetivoMedicao objeto) {
			return getEntityManager()
					.createQuery(
							"from NecessidadeInformacao as neces join neces.objetivosMedicao as med where med.id = "
									+ objeto.getId()).getResultList();
	}
}
