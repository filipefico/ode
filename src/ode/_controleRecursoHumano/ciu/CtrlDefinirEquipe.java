package ode._controleRecursoHumano.ciu;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import ode._controleRecursoHumano.cdp.ParticipacaoEquipe;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgd.ParticipacaoEquipeDAO;
import ode._controleRecursoHumano.cgd.RecursoHumanoDAO;
import ode._controleRecursoHumano.cgt.AplDefinirEquipe;
import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.util.NucleoContexto;
import ode.controleProjeto.cdp.Projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlDefinirEquipe extends CtrlBase {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AplDefinirEquipe aplDefinirEquipe;
	
	@Autowired
	public ParticipacaoEquipeDAO participacaoEquipeDAO;
	
	@Autowired
	private RecursoHumanoDAO recursoHumanoDAO;
		
	private Projeto projeto;
	
	private JanDefinirEquipe jan;

	public void iniciar() {
		configurarComponentes();
		mostrarJanelaPrincipal();
	}

	public void configurarComponentes() {
		projeto = NucleoContexto.recuperarProjeto();
	}
	
	public void mostrarJanelaPrincipal() {
		jan = new JanDefinirEquipe(this);
		jan.mostrar();
	}

	public void finalizar(){
		jan.detach();
		this.detach();
	}
	
	public void definirEquipe(Set<RecursoHumano> recursosSelecionados) {
		aplDefinirEquipe.definirEquipe(recursosSelecionados, projeto);
	}

	public Collection<RecursoHumano> listarRecursosHumanos() {
		return recursoHumanoDAO.recuperarTodosComOrdenacao("cargo.nome, nome");
	}

	public Collection<RecursoHumano> listarRecursosHumanosEquipe() {
		return participacaoEquipeDAO.obterMembrosPorProjeto(projeto.getId());
	}
	
	public List<ParticipacaoEquipe> listarHistoricoEquipe() {
		return participacaoEquipeDAO.obterHistoricoPorProjeto(projeto.getId());
	}

	public Projeto getProjeto() {
		return projeto;
	}

}
