package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KProcesso;
import ode.nucleo.cgd.NucleoDAOBaseHibernate;
import ode.nucleo.cgd.ObjetoPagina;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

@Repository
public class KProcessoDAOHibernate extends NucleoDAOBaseHibernate<KProcesso> implements
KProcessoDAO{

		
	@Override
	public Collection<KProcesso> recuperarTodosPaginado(ObjetoPagina pagina) {
		DetachedCriteria detaCriteria = getDetachedCriteria(pagina);
		detaCriteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		Collection<KProcesso> result = null;
		if (pagina.isPaginada()) {
			result = getHibernateTemplate().findByCriteria(detaCriteria,
					pagina.getFirstResults(), pagina.getMaxResults());

		} else {
			result = getHibernateTemplate().findByCriteria(detaCriteria);
		}

		return result;

	}
	
}
