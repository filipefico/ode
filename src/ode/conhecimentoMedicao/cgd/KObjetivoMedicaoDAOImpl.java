package ode.conhecimentoMedicao.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import ode.conhecimentoMedicao.cdp.KNecessidadeInformacao;
import ode.conhecimentoMedicao.cdp.KObjetivoEstrategico;
import ode.conhecimentoMedicao.cdp.KObjetivoMedicao;
import ode.conhecimentoMedicao.cdp.KObjetivoSoftware;
import ode._infraestruturaBase.cgd.DAOBaseImpl;


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
