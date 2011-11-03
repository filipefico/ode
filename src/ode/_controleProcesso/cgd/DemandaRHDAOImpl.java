package ode._controleProcesso.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import ode._controleProcesso.cdp.DemandaRH;
import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.conhecimento.processo.cdp.KRecursoHumano;

@Repository
public class DemandaRHDAOImpl extends DAOBaseImpl<DemandaRH> implements DemandaRHDAO {

	@Override
	public Collection<KRecursoHumano> recuperarKRecursosHumanosPorProjeto(Long idProjeto) {
		return entityManager.createQuery("select distinct d.KRecursoHumano from DemandaRH d where d.definicaoAtividade.atividade.processoProjetoEspecifico.processoProjetoGeral.projeto.id = :idProjeto", KRecursoHumano.class).setParameter("idProjeto", idProjeto).getResultList();
	}

}
