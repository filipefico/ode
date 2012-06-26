package ode.entidadeProblema.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.conhecimento.processo.cdp.KArtefato;
import ode.entidadeProblema.cdp.ArtefatoProblema;

@Repository
public class ArtefatoProblemaDAOImpl extends DAOBaseImpl<ArtefatoProblema> implements ArtefatoProblemaDAO{ 
	public Collection<ArtefatoProblema> obterPorArtefato (KArtefato artefato){
		String query = "select a from ArtefatoProblema a where a.kartefato.id = " + artefato.getId();
		return getEntityManager().createQuery(query).getResultList();
	}
}
