package ode.conhecimentoMedicao.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import ode.conhecimentoMedicao.cdp.KUnidadeMedida;
import ode._infraestruturaBase.cgd.DAOBaseImpl;

@Repository
public class KUnidadeMedidaDAOImpl extends DAOBaseImpl<KUnidadeMedida> implements KUnidadeMedidaDAO{

	@Override
	public Collection<KUnidadeMedida> getKMedidasRelacionadas(KUnidadeMedida objeto) {
		return getEntityManager().createQuery("select med from KMedida med join med.unidadeMedida unidade where unidade.id="+objeto.getId()).getResultList();
	}

}
