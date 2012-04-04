package ode.gerenciaRequisitos.cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.util.NucleoContexto;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.gerenciaRequisitos.cgt.AplCadastrarRequisito;
import ode.gerenciaRequisitos.cih.PainelGerarRelatorioRastreabilidade;
import ode.principal.ciu.WindowPrincipal;

@SuppressWarnings("serial")
@Controller
public class CtrlGerarRelatorioRastreabilidade extends CtrlBase{

	private PainelGerarRelatorioRastreabilidade painelGerarRelatorioRastreabilidade;
	
	private JanelaSimples jan;
	
	@Autowired
	private AplCadastrarRequisito aplCadastrarRequisito;
	
	public AplCadastrarRequisito getAplCadastrarRequisito() {
		return aplCadastrarRequisito;
	}

	public void setAplCadastrarRequisito(AplCadastrarRequisito aplCadastrarRequisito) {
		this.aplCadastrarRequisito = aplCadastrarRequisito;
	}

	public void iniciar() {
		configurarComponentes();
		mostrarJanelaPrincipal();
	}

	public void configurarComponentes() {
		painelGerarRelatorioRastreabilidade = new PainelGerarRelatorioRastreabilidade();
	}
	
	public void mostrarJanelaPrincipal() {
		jan = factoryJanelaSimples();
		painelGerarRelatorioRastreabilidade.setParent(jan);
		jan.setTitle("Definir Relações de Rastreabilidade");
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

}