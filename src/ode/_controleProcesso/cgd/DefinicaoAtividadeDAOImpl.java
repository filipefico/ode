package ode._controleProcesso.cgd;

import org.springframework.stereotype.Repository;

import ode._controleProcesso.cdp.DefinicaoAtividade;
import ode._infraestruturaBase.cgd.DAOBaseImpl;

@Repository
public class DefinicaoAtividadeDAOImpl extends DAOBaseImpl<DefinicaoAtividade> implements DefinicaoAtividadeDAO {

	@Override
	public DefinicaoAtividade recuperarPorAtividade(Long idAtividade) {
		return recuperarSinglePorQuery(entityManager.createQuery("from DefinicaoAtividade da where da.atividade.id = :idAtividade", DefinicaoAtividade.class).setParameter("idAtividade", idAtividade));		
	}
}
