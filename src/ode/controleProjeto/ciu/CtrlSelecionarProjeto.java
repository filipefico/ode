package ode.controleProjeto.ciu;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.util.NucleoContexto;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.controleProjeto.cgt.AplCadastrarProjeto;
import ode.principal.ciu.WindowPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller(CtrlSelecionarProjeto.NOME)
public class CtrlSelecionarProjeto extends CtrlBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String NOME = "CtrlSelecionarProjeto";
	
	private PainelSelecionarProjeto painelSelecionarProjeto;
	
	private JanelaSimples jan;
	
	@Autowired
	private AplCadastrarProjeto aplCadastrarProjeto;
	
	public AplCadastrarProjeto getAplCadastrarProjeto() {
		return aplCadastrarProjeto;
	}

	public void setAplCadastrarProjeto(AplCadastrarProjeto aplCadastrarProjeto) {
		this.aplCadastrarProjeto = aplCadastrarProjeto;
	}

	public CtrlSelecionarProjeto(){
	}

	public void iniciar() {
		configurarComponentes();
		mostrarJanelaPrincipal();
	}

	public void configurarComponentes() {
		painelSelecionarProjeto = new PainelSelecionarProjeto(this);
	}
	
	public void mostrarJanelaPrincipal() {
		
		jan = factoryJanelaSimples();
		painelSelecionarProjeto.setParent(jan);
		jan.setTitle("Selecionar Projeto");
		jan.setWidth("400px");

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
