package ode.medicao.planejamentoMedicao.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import ode.conhecimentoMedicao.cdp.KMedida;
import ode.medicao.planejamentoMedicao.cdp.NecessidadeInformacao;
import ode._infraestruturaBase.cgd.DAOBaseImpl;

@Repository
public class NecessidadeInformacaoDAOImpl extends
		DAOBaseImpl<NecessidadeInformacao> implements
		NecessidadeInformacaoDAO {

	@Override
	public Collection<KMedida> getKMedidasRelacionadas(
			NecessidadeInformacao objeto) {
		return getEntityManager()
				.createQuery(
						"select medida from KMedida medida join medida.necessidadesInformacao nec where nec.id="
								+ objeto.getId()).getResultList();
	}

}
