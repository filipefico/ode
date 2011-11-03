package ode._controleProcesso.cgd;

import java.util.List;

import org.springframework.stereotype.Repository;

import ode._controleProcesso.cdp.Atividade;
import ode._infraestruturaBase.cgd.DAOBaseImpl;

@Repository
public class AtividadeDAOImpl extends DAOBaseImpl<Atividade> implements AtividadeDAO {

	@Override
	public List<Atividade> recuperarAtividadesPorProcessoProjetoEspecifico(Long idProcesso) {
		return entityManager.createQuery("select a from Atividade a where a.processoProjetoEspecifico.id = :idProcesso", Atividade.class).setParameter("idProcesso", idProcesso).getResultList();
	}

}
