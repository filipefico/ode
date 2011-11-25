package ode._controleFerramenta.cgd;

import java.util.Collection;

import ode._controleFerramenta.cdp.FerramentaSoftware;
import ode._infraestruturaBase.cgd.DAOBaseImpl;

import org.springframework.stereotype.Repository;

@Repository
public class FerramentaSoftwareDAOImpl extends
		DAOBaseImpl<FerramentaSoftware> implements FerramentaSoftwareDAO {

	@Override
	public Collection<FerramentaSoftware> recuperarPorKFerramentaSoftware(Long idKFerramentaSoftware) {
		return entityManager.createQuery("from FerramentaSoftware fs where fs.kFerramentaSoftware.id = :idKFerramentaSoftware", FerramentaSoftware.class).setParameter("idKFerramentaSoftware", idKFerramentaSoftware).getResultList();
	}

}
