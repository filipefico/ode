package ode.atuacaoRecursoHumano.ciu;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import ode._controleProcesso.cgd.AtividadeDAO;
import ode._controleProcesso.cgd.DefinicaoAtividadeDAO;
import ode._controleProcesso.cgd.DemandaRHDAO;
import ode._controleProcesso.cgd.ProcessoProjetoEspecificoDAO;
import ode._controleRecursoHumano.cdp.ParticipacaoEquipe;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgd.ParticipacaoEquipeDAO;
import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.excecao.CtrlExcecoes;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoContexto;
import ode.atuacaoRecursoHumano.cgd.AtuacaoRHDAO;
import ode.atuacaoRecursoHumano.cgt.AplDefinirEquipe;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.controleProjeto.cdp.Projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("CtrlDefinirEquipeVersaoCompleta")
public class CtrlDefinirEquipe extends CtrlBase {

	private static final long serialVersionUID = 1L;
	
	private JanDefinirEquipe jan;
	
	private Projeto projeto;
		
	@Autowired
	private AplDefinirEquipe aplDefinirEquipe;
	
	@Autowired
	public AtuacaoRHDAO atuacaoRHDAO;
	
	@Autowired
	public DemandaRHDAO demandaRHDAO;
	
	@Autowired
	public ParticipacaoEquipeDAO participacaoEquipeDAO;
	
	@Autowired
	public ProcessoProjetoEspecificoDAO processoProjetoEspecificoDAO;

	@Autowired
	public AtividadeDAO atividadeDAO;

	@Autowired
	public DefinicaoAtividadeDAO definicaoAtividadeDAO;
	
	@Override
	public void iniciar() {
		projeto = NucleoContexto.recuperarProjeto();
		
		jan = new JanDefinirEquipe(this);
		jan.mostrar();
	}

	public Projeto getProjeto() {
		return projeto;
	}
	
	public void definirEquipe(Set<RecursoHumano> objetosSelecionados, KRecursoHumano krh) {
		try {
		aplDefinirEquipe.definirEquipe(objetosSelecionados, krh, projeto);
		}
		catch(NucleoRegraNegocioExcecao e) {
			CtrlExcecoes.tratarExcecao(e);
		}
	}
	
	public Collection<KRecursoHumano> listarKRecursosHumanosPorProjeto() {
		return demandaRHDAO.recuperarKRecursosHumanosPorProjeto(projeto.getId());
	}

	public Collection<RecursoHumano> listarParticipacoesRecursosHumanosPorPapel(KRecursoHumano krh) {
		return atuacaoRHDAO.recuperarComParticipacaoPapel(krh.getId(), projeto.getId());
	}
	
	public List<ParticipacaoEquipe> listarHistoricoEquipe() {
		return participacaoEquipeDAO.obterHistoricoPorProjeto(projeto.getId());
	}
	
}