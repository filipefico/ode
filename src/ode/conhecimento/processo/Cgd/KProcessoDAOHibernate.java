package ode.conhecimento.processo.Cgd;

import java.util.Collection;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.CriteriaSpecification;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import nucleo.comuns.persistencia.ObjetoPagina;
import ode.conhecimento.processo.Cdp.KProcesso;

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

	@Override
	protected Class getClasseDominio() {
		return KProcesso.class;
	}
	
}
