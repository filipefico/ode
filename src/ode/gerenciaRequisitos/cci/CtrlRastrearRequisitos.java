package ode.gerenciaRequisitos.cci;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.util.NucleoContexto;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.gerenciaRequisitos.cdp.Requisito;
import ode.gerenciaRequisitos.cgt.AplRastrearRequisitos;
import ode.gerenciaRequisitos.cih.PainelRastrearRequisitos;
import ode.gerenciaRequisitos.cih.PainelVisualizarRequisito;
import ode.principal.ciu.WindowPrincipal;
import ode.uml.cdp.CasoUso;
import ode.uml.cdp.Classe;
import ode.uml.cdp.Pacote;

@Controller
public class CtrlRastrearRequisitos extends CtrlBase{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -911903256042947042L;

	private PainelRastrearRequisitos painelRastrearRequisitos;
	
	private PainelVisualizarRequisito painelVisualizarRequisito;
	
	private JanelaSimples jan, jan2;
	
	@Autowired
	private AplRastrearRequisitos aplRastrearRequisitos;
	
	public AplRastrearRequisitos getAplRastrearRequisitos() {
		return aplRastrearRequisitos;
	}

	public void setAplRastrearRequisitos(AplRastrearRequisitos aplRastrearRequisitos) {
		this.aplRastrearRequisitos = aplRastrearRequisitos;
	}

	public CtrlRastrearRequisitos() {
	}

	public void iniciar() {
		configurarComponentes();
		mostrarJanelaPrincipal();
	}
	
	public void iniciarVerRequisito(Requisito requisito){
		painelVisualizarRequisito = new PainelVisualizarRequisito(requisito);
		jan2 = factoryJanelaSimples();
		jan2.setWidth("800px");
		painelVisualizarRequisito.setParent(jan2);
		jan2.setTitle("Visualizar Requisito");
		jan2.mostrar();
	}

	public void configurarComponentes() {
		painelRastrearRequisitos = new PainelRastrearRequisitos();
	}
	
	public void mostrarJanelaPrincipal() {
		
		jan = factoryJanelaSimples();
		painelRastrearRequisitos.setParent(jan);
		jan.setTitle("Rastrear Requisitos");
		jan.setWidth("600px");

		jan.mostrar();
	}
	
	public void atualizarWindowPrincipal(){
		WindowPrincipal windowMenu = NucleoContexto.recuperarJanelaPrincipal();
		windowMenu.atualizarBarraInformacoes();
		windowMenu.atualizarMenubar();
	}

	public void finalizar(){
		jan.detach();
		this.detach();
	}
	
	public Collection<Requisito> obterPorCasoUso (CasoUso casoUso){
		return aplRastrearRequisitos.obterPorCasoUso(casoUso);
	}
	
	public Collection<Requisito> obterPorClasse (Classe classe){
		return aplRastrearRequisitos.obterPorClasse(classe);
	}
	
	public Collection<Requisito> obterPorPacote (Pacote pacote){
		return aplRastrearRequisitos.obterPorPacote(pacote);
	}
	
	public Collection<Requisito> obterPorResponsavel (RecursoHumano responsavel){
		return aplRastrearRequisitos.obterPorResponsavel(responsavel);
	}
	
	public Collection<Requisito> obterPorInteressado (RecursoHumano interessado){
		return aplRastrearRequisitos.obterPorInteressado(interessado);
	}
}
