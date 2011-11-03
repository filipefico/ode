package ode.alocacaoRecursoHumano.ciu;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode._controleProcesso.cdp.Atividade;
import ode._controleProcesso.cdp.ProcessoProjetoEspecifico;
import ode._controleProcesso.cgd.AtividadeDAO;
import ode._controleProcesso.cgd.DemandaRHDAO;
import ode._controleProcesso.cgd.ProcessoProjetoEspecificoDAO;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgd.ParticipacaoEquipeDAO;
import ode._controleRecursoHumano.cgt.AplDefinirEquipe;
import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.util.NucleoContexto;
/*
import ode.alocacaoRecursoHumano.cgt.AplAlocarRH;
import ode.alocacaoRecursoHumano.cgt.AplControlarAlocacaoRH;
import ode.alocacaoRecursoHumano.cgt.AplRegistrarEsforcoDespendido;
*/
import ode.atuacaoRecursoHumano.cgd.AtuacaoRHDAO;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.controleProjeto.cdp.Projeto;

@Controller
public class CtrlAlocacaoRecursoHumano extends CtrlBase {

	private static final long serialVersionUID = 1L;
	
	private JanAlocacaoRH jan;
	
	private Projeto projeto;
	
	/*
	@Autowired
	private AplRegistrarEsforcoDespendido aplRegistrarEsforcoDespendido;
	
	@Autowired
	private AplAlocarRH aplAlocarRH;
	
	@Autowired
	private AplControlarAlocacaoRH aplControlarAlocacaoRH;
	*/
		
	@Autowired
	private AplDefinirEquipe aplDefinirEquipe;
	
	
	@Autowired
	private AtuacaoRHDAO atuacaoRHDAO;
	
	@Autowired
	private DemandaRHDAO demandaRHDAO;
	
	@Autowired
	private ParticipacaoEquipeDAO participacaoEquipeDAO;
	
	@Autowired
	private ProcessoProjetoEspecificoDAO processoProjetoEspecificoDAO;

	@Autowired
	private AtividadeDAO atividadeDAO;
	
	@Override
	public void iniciar() {
		projeto = NucleoContexto.recuperarProjeto();
		
		jan = new JanAlocacaoRH(this);
		jan.mostrar();
	}

	public Projeto getProjeto() {
		return projeto;
	}
	
	public void definirEquipe(Set<RecursoHumano> objetosSelecionados, KRecursoHumano krh) {
		aplDefinirEquipe.definirEquipe(objetosSelecionados, krh, projeto);
	}
	
	public Collection<ProcessoProjetoEspecifico> listarProcessosProjetoEspecifico() {
		return processoProjetoEspecificoDAO.recuperarPorProjeto(projeto.getId());
	}

	public Collection<KRecursoHumano> listarKRecursosHumanosPorProjeto() {
		return demandaRHDAO.recuperarKRecursosHumanosPorProjeto(projeto.getId());
	}

	public Collection<RecursoHumano> listarRecursosHumanosPorPapel(KRecursoHumano krh) {
		return atuacaoRHDAO.recuperarAptosPorPapel(krh.getId());
	}
	
	public Collection<RecursoHumano> listarParticipacoesRecursosHumanosPorPapel(KRecursoHumano krh) {
		return participacaoEquipeDAO.recuperarRecursosHumanosComParticipacaoPapel(krh.getId(), projeto.getId());
	}

	public Collection<Atividade> listarAtividadesProcesso(ProcessoProjetoEspecifico processo) {
		return atividadeDAO.recuperarAtividadesPorProcessoProjetoEspecifico(processo.getId());
	}
}
