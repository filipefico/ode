package ode._controleProcesso.cgd;

import java.util.List;

import ode._controleProcesso.cdp.Atividade;
import ode._infraestruturaBase.cgd.DAOBaseImpl;

import org.springframework.stereotype.Repository;

@Repository
public class AtividadeDAOImpl extends DAOBaseImpl<Atividade> implements AtividadeDAO {

	@Override
	public List<Atividade> recuperarPorProcessoProjetoEspecifico(Long idProcesso) {
		return entityManager.createQuery("select a from Atividade a" +
				" where a.processoProjetoEspecifico.id = :idProcesso" +
				" and not exists (from DefinicaoAtividade da where a in (select s.id from da.subAtividades s))" +
				" order by a.nome", Atividade.class).setParameter("idProcesso", idProcesso).getResultList();
	}
}
