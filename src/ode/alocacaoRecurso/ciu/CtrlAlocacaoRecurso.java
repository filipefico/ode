package ode.alocacaoRecurso.ciu;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import ode._controleFerramenta.cdp.FerramentaSoftware;
import ode._controleFerramenta.cgd.FerramentaSoftwareDAO;
import ode._controleProcesso.cdp.Atividade;
import ode._controleProcesso.cdp.DemandaRH;
import ode._controleProcesso.cgd.AtividadeDAO;
import ode._controleProcesso.cgd.DefinicaoAtividadeDAO;
import ode._controleProcesso.cgd.DemandaRHDAO;
import ode._controleProcesso.cgd.ProcessoProjetoEspecificoDAO;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgd.ParticipacaoEquipeDAO;
import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.util.NucleoContexto;
import ode.alocacaoRecurso.cdp.AlocacaoFerramentaSoftware;
import ode.alocacaoRecurso.cdp.AlocacaoRH;
import ode.alocacaoRecurso.cdp.EstadoAlocacaoRH;
import ode.alocacaoRecurso.cgd.AlocacaoFerramentaSoftwareDAO;
import ode.alocacaoRecurso.cgd.AlocacaoRHDAO;
import ode.alocacaoRecurso.cgd.CancelamentoAlocacaoRHDAO;
import ode.alocacaoRecurso.cgt.AplAlocarRecurso;
import ode.alocacaoRecurso.cgt.AplControlarAlocacaoRH;
import ode.atuacaoRecursoHumano.cgd.AtuacaoRHDAO;
import ode.conhecimento.processo.cdp.KFerramentaSoftware;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.controleProjeto.cdp.Projeto;
import ode.planejamentoCompetencia.cgd.DemandaCompetenciaDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;

@Controller
public class CtrlAlocacaoRecurso extends CtrlBase {

	private static final long serialVersionUID = 1L;
	
	private JanAlocacaoRecurso jan;
	
	private Projeto projeto;
	
	@Autowired
	private AplAlocarRecurso aplAlocarRecurso;
		
	@Autowired
	private AplControlarAlocacaoRH aplControlarAlocacaoRH;
	
	@Autowired
	public AtuacaoRHDAO atuacaoRHDAO;
	
	@Autowired
	public DemandaRHDAO demandaRHDAO;
	
	@Autowired
	public DemandaCompetenciaDAO demandaCompetenciaDAO;
	
	@Autowired
	public ParticipacaoEquipeDAO participacaoEquipeDAO;
	
	@Autowired
	public ProcessoProjetoEspecificoDAO processoProjetoEspecificoDAO;

	@Autowired
	public AtividadeDAO atividadeDAO;

	@Autowired
	public DefinicaoAtividadeDAO definicaoAtividadeDAO;

	@Autowired
	public AlocacaoRHDAO alocacaoRHDAO;
	
	@Autowired
	public AlocacaoFerramentaSoftwareDAO alocacaoFerramentaSoftwareDAO;

	@Autowired
	public CancelamentoAlocacaoRHDAO cancelamentoAlocacaoRHDAO;

	@Autowired
	public FerramentaSoftwareDAO ferramentaSoftwareDAO;
	
	private AlocacaoRH alocacaoSelecionada;
	
	public void setAlocacaoSelecionada(AlocacaoRH alocacaoRH) {
		this.alocacaoSelecionada = alocacaoRH;
	}
	
	public AlocacaoRH getAlocacaoSelecionada() {
		return alocacaoSelecionada;
	}
	
	@Override
	public void iniciar() {
		projeto = NucleoContexto.recuperarProjeto();
		
		jan = new JanAlocacaoRecurso(this);
		jan.mostrar();
	}

	public Projeto getProjeto() {
		return projeto;
	}
	
	public boolean existeEquipeDefinida() {
		for(KRecursoHumano krh : listarKRecursosHumanosPorProjeto())
			if(listarParticipacoesRecursosHumanosPorPapel(krh).size()>0)
				return true;
		return false;
	}
	
	public Collection<KRecursoHumano> listarKRecursosHumanosPorProjeto() {
		return demandaRHDAO.recuperarKRecursosHumanosPorProjeto(projeto.getId());
	}

	public Collection<RecursoHumano> listarParticipacoesRecursosHumanosPorPapel(KRecursoHumano krh) {
		return atuacaoRHDAO.recuperarComParticipacaoPapel(krh.getId(), projeto.getId());
	}

	public Collection<AlocacaoRH> listarAlocacoesRHPorDemandaRH(DemandaRH demandaRH) {
		return alocacaoRHDAO.recuperarPorAtividadeKRecursoHumano(demandaRH.getDefinicaoAtividade().getAtividade().getId(), demandaRH.getkRecursoHumano().getId());
	}
	
	public Collection<AlocacaoFerramentaSoftware> listarAlocacoesFerramentaSoftwarePorAtividadeKFerramentaSoftware(Atividade atividade, KFerramentaSoftware kFerramentaSoftware) {
		return alocacaoFerramentaSoftwareDAO.recuperarPorAtividadeKFerramentaSoftware(atividade.getId(), kFerramentaSoftware.getId());
	}

	public void alocarRecursosHumanos(DemandaRH demandaRH, Set<RecursoHumano> objetosSelecionados, Set<RecursoHumano> objetosNaoSelecionados) {
		aplAlocarRecurso.alocarRecursosHumanos(demandaRH, objetosSelecionados, objetosNaoSelecionados);
	}

	public void cancelarAlocacaoRH(String motivo) {
		aplControlarAlocacaoRH.cancelarAlocacaoRH(alocacaoSelecionada, motivo);
		jan.preencherBoxAlocacaoRH();
	}

	public void anularCancelamentoAlocacaoRH() {
		aplControlarAlocacaoRH.anularCancelamentoAlocacaoRH(alocacaoSelecionada);
		jan.preencherBoxAlocacaoRH();
	}

	public void editarAlocacao(Integer dedicacao, Date dtInicioPrevista, Date dtFimPrevista) {
		aplAlocarRecurso.editarAlocacao(alocacaoSelecionada, dedicacao, dtInicioPrevista, dtFimPrevista);
	}
	
	public void avaliarAlocacaoRH(AlocacaoRH alocacaoRH, EstadoAlocacaoRH estado) {
		aplControlarAlocacaoRH.avaliarParticipacaodeRH(alocacaoRH, estado);
	}

	public Collection<FerramentaSoftware> listarFerramentasSoftwarePorKFerramentaSoftware(
			KFerramentaSoftware kFerramentaSoftware) {
		return ferramentaSoftwareDAO.recuperarPorKFerramentaSoftware(kFerramentaSoftware.getId());
	}

	public void alocarFerramentasSoftware(Atividade atividade,
			Set<FerramentaSoftware> objetosSelecionados,
			Set<FerramentaSoftware> objetosNaoSelecionados) {
		aplAlocarRecurso.alocarFerramentasSoftware(atividade, objetosSelecionados, objetosNaoSelecionados);
	}

	public void preencherPainelAlocarRecursos() {
		if(ehPossivelAlocarAutomaticamente()) {		
			try {
				confirmaSimNao("Um ou mais recursos requeridos podem ser atendidos por apenas um recurso.\r\n"
								+ "Deseja alocar tais recursos automaticamente?", new EventListener() {

					public void onEvent(Event event)
							throws InterruptedException {
						if (((Integer) event.getData()).intValue() == Messagebox.YES) {
							aplAlocarRecurso.alocarAutomaticamente(projeto);
						}
						jan.preencherArvore();
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else
			jan.preencherArvore();
	}

	public boolean ehPossivelAlocarAutomaticamente() {
		return aplAlocarRecurso.verificarPossibilidadeAlocarAutomaticamente(projeto);
	}

	
}