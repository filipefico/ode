package ode.entidadeProblema.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.controleProjeto.cdp.Projeto;
import ode.entidadeProblema.cdp.ProjetoProblema;
@Repository
public class ProjetoProblemaDAOImpl extends DAOBaseImpl<ProjetoProblema> implements ProjetoProblemaDAO {
	public Collection<ProjetoProblema> obterPorProjeto (Projeto projeto){
		String query = "select p from ProjetoProblema p where p.projeto.id = " + projeto.getId();
		return getEntityManager().createQuery(query).getResultList();
	}
}
