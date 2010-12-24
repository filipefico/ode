package ode.conhecimento.processo.Cgd;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import ode.conhecimento.processo.Cdp.KArtefato;

public class KArtefatoDAOHibernate extends NucleoDAOBaseHibernate<KArtefato>
		implements KArtefatoDAO {
	
	private SessionFactory sessions = null;

	@Override
	protected Class<KArtefato> getClasseDominio() {
		return KArtefato.class;
	}

	@Override
	public KArtefato recuperarPorId(Long id) {
	    DetachedCriteria detaCriteria = DetachedCriteria.forClass(getClasseDominio());
	    detaCriteria.setFetchMode("subArtefatos", FetchMode.JOIN)
	        .setFetchMode("dependencias", FetchMode.JOIN)
	        .setFetchMode("tipo", FetchMode.JOIN)
	        .add( Restrictions.idEq(id) );

	    //List resultado = getHibernateTemplate().findByCriteria(detaCriteria);
	    List resultado = getHibernateTemplate().findByCriteria(detaCriteria, 0, 1);
		return (KArtefato)resultado.get(0);
	}

}
