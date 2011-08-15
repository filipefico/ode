package ode.conhecimento.processo.Cgd;

import java.util.List;

import ode.conhecimento.processo.Cdp.KArtefato;
import ode.nucleo.crud.cgd.DAOBaseHibernate;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class KArtefatoDAOHibernate extends DAOBaseHibernate<KArtefato>
		implements KArtefatoDAO {

	@Override
	public KArtefato recuperarPorId(Long id) {
	    DetachedCriteria detaCriteria = DetachedCriteria.forClass(getClasseDominio());
	    detaCriteria.setFetchMode("subArtefatos", FetchMode.JOIN)
	        .setFetchMode("dependencias", FetchMode.JOIN)
	        .setFetchMode("tipo", FetchMode.JOIN)
	        .add( Restrictions.idEq(id) );

	    List resultado = getHibernateTemplate().findByCriteria(detaCriteria);
	    return (KArtefato)resultado.get(0);
	}

}
