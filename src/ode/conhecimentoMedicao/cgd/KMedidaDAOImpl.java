package ode.conhecimentoMedicao.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import ode.conhecimentoMedicao.cdp.KMedida;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoMedicao;
import ode._infraestruturaBase.cgd.DAOBaseImpl;

@Repository
public class KMedidaDAOImpl extends DAOBaseImpl<KMedida> implements KMedidaDAO{

	@Override
	public Collection<KMedida> recuperarPorObjetivo(KObjetivoMedicao obj) {
		return getEntityManager().createQuery("select med from KMedida med, KNecessidadeInformacao, KObjetivoMedicao join med.necessidadesInformacao nec join nec.objetivosMedicao obj where obj.id = "+obj.getId()).getResultList();
	}

	
}
