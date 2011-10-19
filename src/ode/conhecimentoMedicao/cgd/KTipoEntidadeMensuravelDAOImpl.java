package ode.conhecimentoMedicao.cgd;

import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.impl.CriteriaImpl;
import org.springframework.stereotype.Repository;

import ode.conhecimentoMedicao.cdp.KElementoMensuravel;
import ode.conhecimentoMedicao.cdp.KTipoEntidadeMensuravel;
import ode._infraestruturaBase.cgd.DAOBaseImpl;

@Repository
public class KTipoEntidadeMensuravelDAOImpl extends
		DAOBaseImpl<KTipoEntidadeMensuravel> implements
		KTipoEntidadeMensuravelDAO {

	public Collection<KElementoMensuravel> recuperarPorTipo(
			KTipoEntidadeMensuravel tipo) {

		return getEntityManager()
				.createQuery(
						"select elem from KElementoMensuravel as elem join elem.tipoEntidadeMensuravel as tipo where tipo.id="
								+ tipo.getId()).getResultList();
	}
}
