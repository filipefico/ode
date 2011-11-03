package ode.atuacaoRecursoHumano.ciu;

import java.util.Collection;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.ciu.PainelCRUDRecursoHumano;
import ode._infraestruturaBase.excecao.CtrlExcecoes;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.atuacaoRecursoHumano.cdp.AtuacaoRH;
import ode.atuacaoRecursoHumano.cdp.CompetenciaRH;
import ode.atuacaoRecursoHumano.cgd.AtuacaoRHDAO;
import ode.atuacaoRecursoHumano.cgt.AplCadastrarAtuacaoRH;
import ode.conhecimento.organizacao.cdp.KCompetencia;
import ode.conhecimento.organizacao.cgd.KCompetenciaDAO;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.conhecimento.processo.cgd.KRecursoHumanoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlAtuacaoRHCRUD extends CtrlCRUD<RecursoHumano> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AplCadastrarAtuacaoRH aplCadastrarAtuacaoRH;
		
	@Autowired
	private AtuacaoRHDAO atuacaoRHDAO;

	@Autowired
	private KRecursoHumanoDAO kRecursoHumanoDAO;

	@Autowired
	private KCompetenciaDAO kCompetenciaDAO;
	
	@Override
	public String definirTituloJanelaDados() {
		return "Recurso Humano";
	}

	public AplCRUD<RecursoHumano> definirAplCRUD() {
		return this.aplCadastrarAtuacaoRH;
	}

	@Override
	public PainelCRUD<RecursoHumano> definirPainelCRUD() {
		return new PainelCRUDRecursoHumano();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Recursos Humanos";
	}

	@Override
	public FormularioDadosCRUD<RecursoHumano> definirFormularioCadastro() {
		return new FormDadosAtuacaoRH();
	}

	@Override
	public RecursoHumano factoryObjetoDados() {
		return new RecursoHumano();
	}

	@Override
	public void acaoSalvar() {
		try {
			((FormDadosAtuacaoRH)formularioDados).salvar();
			atualizarPesquisa();
			janDados.onClose();

		} catch (Exception e) {
			CtrlExcecoes.tratarExcecao(e);
		}

	}

	public void salvar(RecursoHumano rh, AtuacaoRH atuacaoRH, Collection<CompetenciaRH> competencias) {
		aplCadastrarAtuacaoRH.salvar(rh, atuacaoRH, competencias);
	}
	
	public Collection<KRecursoHumano> listarKRecursosHumanos() {
		return kRecursoHumanoDAO.recuperarTodos();
	}

	public Collection<KCompetencia> listarKCompetencias() {
		return kCompetenciaDAO.recuperarTodos();
	}
	
	public AtuacaoRH getAtuacaoRH(RecursoHumano rh) {
		return atuacaoRHDAO.recuperarAtuacaoRHPorRH(rh.getId());
	}	

	public Collection<CompetenciaRH> obterCompetenciasRH(AtuacaoRH atuacaoRH) {
		return atuacaoRHDAO.recuperarCompetenciasPorAtuacaoRH(atuacaoRH.getId());
	}
	

}
