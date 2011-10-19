package ode.conhecimentoMedicao.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cdp.KNecessidadeInformacao;
import ode._infraestruturaBase.cgd.DAOBaseImpl;

@Repository
public class KNecessidadeInformacaoDAOImpl extends
		DAOBaseImpl<KNecessidadeInformacao> implements
		KNecessidadeInformacaoDAO {

	@Override
	public Collection<KMedida> getKMedidasRelacionadas(
			KNecessidadeInformacao objeto) {
		return getEntityManager()
				.createQuery(
						"select medida from KMedida medida join medida.necessidadesInformacao nec where nec.id="
								+ objeto.getId()).getResultList();
	}

}
