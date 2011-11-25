package ode._controleProcesso.cgd;

import java.util.Collection;

import ode._controleProcesso.cdp.DemandaRH;
import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.conhecimento.processo.cdp.KRecursoHumano;

import org.springframework.stereotype.Repository;

@Repository
public class DemandaRHDAOImpl extends DAOBaseImpl<DemandaRH> implements DemandaRHDAO {

	@Override
	public Collection<KRecursoHumano> recuperarKRecursosHumanosPorProjeto(Long idProjeto) {
		return entityManager.createQuery("select distinct krh from DemandaRH d join d.kRecursoHumano krh" +
				" where d.definicaoAtividade.atividade.processoProjetoEspecifico.processoProjetoGeral.projeto.id = :idProjeto" +
				" order by krh.nome", KRecursoHumano.class).setParameter("idProjeto", idProjeto).getResultList();
	}

	@Override
	public Collection<DemandaRH> recuperarPorAtividade(Long idAtividade) {
		return entityManager.createQuery("select d from DemandaRH d join d.kRecursoHumano krh" +
				" where d.definicaoAtividade.atividade.id = :idAtividade" +
				" order by krh.nome", DemandaRH.class).setParameter("idAtividade", idAtividade).getResultList();
	}

}
