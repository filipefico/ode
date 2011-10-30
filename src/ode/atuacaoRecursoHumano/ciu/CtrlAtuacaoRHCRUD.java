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
import ode.atuacaoRecursoHumano.cgt.AplCadastrarAtuacaoRH;
import ode.conhecimento.organizacao.cdp.KCompetencia;
import ode.conhecimento.organizacao.cgt.AplCadastrarKCompetencia;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.conhecimento.processo.cgt.AplCadastrarKRecursoHumano;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlAtuacaoRHCRUD extends CtrlCRUD<RecursoHumano> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AplCadastrarAtuacaoRH aplCadastrarAtuacaoRH;
	
	@Autowired
	private AplCadastrarKCompetencia aplCadastrarKCompetencia;
	
	@Autowired
	private AplCadastrarKRecursoHumano aplCadastrarKRecursoHumano;

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

	public Collection<KRecursoHumano> listarKRecursosHumanos() {
		return aplCadastrarKRecursoHumano.recuperarTodos();
	}

	public Collection<KCompetencia> listarKCompetencias() {
		return aplCadastrarKCompetencia.recuperarTodos();
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

	public AtuacaoRH getAtuacaoRH(RecursoHumano rh) {
		return aplCadastrarAtuacaoRH.recuperarAtuacaoRHPorRH(rh);
	}

	public void salvar(RecursoHumano rh, AtuacaoRH atuacaoRH, Collection<CompetenciaRH> competencias) {
		aplCadastrarAtuacaoRH.salvar(rh, atuacaoRH, competencias);
	}

	public Collection<CompetenciaRH> obterCompetenciasRH(AtuacaoRH atuacaoRH) {
		return aplCadastrarAtuacaoRH.recuperarCompetenciasPorAtuacaoRH(atuacaoRH);
	}
	

}
