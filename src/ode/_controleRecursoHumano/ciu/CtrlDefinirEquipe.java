package ode._controleRecursoHumano.ciu;

import java.util.Collection;
import java.util.Set;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgt.AplCadastrarRecursoHumano;
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
	
	private PainelDefinirEquipe painelDefinirEquipe;
	
	private JanelaSimples jan;
	
	@Autowired
	private AplDefinirEquipe aplDefinirEquipe;
	
	@Autowired
	private AplCadastrarRecursoHumano aplCadastrarRecursoHumano;
		
	private Projeto projeto;

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

	public Collection<RecursoHumano> listarRecursosHumanos() {
		return aplCadastrarRecursoHumano.recuperarTodos();
	}

	public void definirEquipe(Set<RecursoHumano> recursosSelecionados) {
		aplDefinirEquipe.definirEquipe(recursosSelecionados, projeto);
	}
	
	public Collection<RecursoHumano> listarRecursosHumanosEquipe() {
		return aplDefinirEquipe.obterMembrosPorProjeto(projeto.getId());
	}

}
