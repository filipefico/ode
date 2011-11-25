package ode.alocacaoRecurso.cgd;

import java.util.Collection;

import ode._controleFerramenta.cdp.FerramentaSoftware;
import ode._infraestruturaBase.cgd.DAOBase;
import ode.alocacaoRecurso.cdp.AlocacaoFerramentaSoftware;

public interface AlocacaoFerramentaSoftwareDAO extends DAOBase<AlocacaoFerramentaSoftware> {

	public Collection<AlocacaoFerramentaSoftware> recuperarPorAtividadeKFerramentaSoftware(Long idAtividade, Long idKFerramentaSoftware);

	public AlocacaoFerramentaSoftware recuperarPorAtividadeFerramentaSoftware(Long idAtividade, Long idFerramentaSoftware);

	public Collection<AlocacaoFerramentaSoftware> obterPossiveisAlocacoesAutomaticas(Long idProjeto);

	public Collection<FerramentaSoftware> recuperarFerramentasAlocadasPorRH(Long idRecursoHumaono);
	
}
