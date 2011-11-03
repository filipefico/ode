package ode._controleProcesso.cgd;

import java.util.List;

import org.springframework.stereotype.Repository;

import ode._controleProcesso.cdp.ProcessoProjetoEspecifico;
import ode._infraestruturaBase.cgd.DAOBaseImpl;

@Repository
public class ProcessoProjetoEspecificoDAOImpl extends DAOBaseImpl<ProcessoProjetoEspecifico> implements ProcessoProjetoEspecificoDAO {

	@Override
	public List<ProcessoProjetoEspecifico> recuperarPorProjeto(Long idProjeto) {
		return entityManager.createQuery("from ProcessoProjetoEspecifico p where p.processoProjetoGeral.projeto.id = :idProjeto order by p.nome", ProcessoProjetoEspecifico.class).setParameter("idProjeto", idProjeto).getResultList();
	}

}