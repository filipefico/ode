package ode._controleRecursoHumano.ciu;

import java.util.Collection;
import java.util.Set;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgd.ParticipacaoEquipeDAO;
import ode._controleRecursoHumano.cgd.RecursoHumanoDAO;
import ode._controleRecursoHumano.cgt.AplDefinirEquipe;
import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.util.NucleoContexto;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.controleProjeto.cdp.Projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlDefinirEquipe extends CtrlBase {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AplDefinirEquipe aplDefinirEquipe;
	
	@Autowired
	private ParticipacaoEquipeDAO participacaoEquipeDAO;
	
	@Autowired
	private RecursoHumanoDAO recursoHumanoDAO;
		
	private Projeto projeto;
	
	private PainelDefinirEquipe painelDefinirEquipe;
	
	private JanelaSimples jan;

	public void iniciar() {
		configurarComponentes();
		mostrarJanelaPrincipal();
	}

	public void configurarComponentes() {
		projeto = NucleoContexto.recuperarProjeto();		painelDefinirEquipe = new PainelDefinirEquipe(this);
	}
	
	public void mostrarJanelaPrincipal() {
		
		jan = factoryJanelaSimples();
		painelDefinirEquipe.setParent(jan);
		jan.setTitle("Definir Equipe - " + projeto.getNome());
		jan.setWidth("400px");

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
		return recursoHumanoDAO.recuperarTodos();
	}

	public Collection<RecursoHumano> listarRecursosHumanosEquipe() {
		return participacaoEquipeDAO.obterMembrosPorProjeto(projeto.getId());
	}

}
