package ode.alocacaoRecurso.cgd;

import java.util.Collection;

import ode._controleFerramenta.cdp.FerramentaSoftware;
import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.alocacaoRecurso.cdp.AlocacaoFerramentaSoftware;

import org.springframework.stereotype.Repository;

@Repository
public class AlocacaoFerramentaSoftwareDAOImpl extends DAOBaseImpl<AlocacaoFerramentaSoftware> implements AlocacaoFerramentaSoftwareDAO {

	@Override
	public Collection<AlocacaoFerramentaSoftware> recuperarPorAtividadeKFerramentaSoftware(Long idAtividade, Long idKFerramentaSoftware) {
		return entityManager.createQuery("select a from AlocacaoFerramentaSoftware a where a.atividade.id = :idAtividade and a.ferramentaSoftware.kFerramentaSoftware.id = :idKFerramentaSoftware", AlocacaoFerramentaSoftware.class).setParameter("idAtividade", idAtividade).setParameter("idKFerramentaSoftware", idKFerramentaSoftware).getResultList();
	}

	@Override
	public AlocacaoFerramentaSoftware recuperarPorAtividadeFerramentaSoftware(Long idAtividade, Long idFerramentaSoftware) {
		return recuperarSinglePorQuery(entityManager.createQuery("select a from AlocacaoFerramentaSoftware a where a.atividade.id = :idAtividade and a.ferramentaSoftware.id = :idFerramentaSoftware", AlocacaoFerramentaSoftware.class).setParameter("idAtividade", idAtividade).setParameter("idFerramentaSoftware", idFerramentaSoftware));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<AlocacaoFerramentaSoftware> obterPossiveisAlocacoesAutomaticas(Long idProjeto) {
		return entityManager.createQuery("select new AlocacaoFerramentaSoftware(da.atividade,fs) from DefinicaoAtividade da, FerramentaSoftware fs join da.kRecursos kfs" +
				" where da.atividade.processoProjetoEspecifico.processoProjetoGeral.projeto.id = :idProjeto" +
				" and (select count(*) from FerramentaSoftware where kFerramentaSoftware = kfs) = 1" +
				" and fs.kFerramentaSoftware = kfs" +
				" and not exists (from AlocacaoFerramentaSoftware afs where afs.atividade = da.atividade and afs.ferramentaSoftware = fs)").setParameter("idProjeto", idProjeto).getResultList();
	}

	@Override
	public Collection<FerramentaSoftware> recuperarFerramentasAlocadasPorRH(Long idRecursoHumano) {
		return entityManager.createQuery("select distinct fs from AlocacaoFerramentaSoftware afs join afs.ferramentaSoftware fs" +
				" where exists (from AlocacaoRH arh where arh.recursoHumano.id = :idRecursoHumano" +
				" and arh.atividade = afs.atividade)", FerramentaSoftware.class).setParameter("idRecursoHumano", idRecursoHumano).getResultList();
	}
}
