package ode.controleProjeto.cci;

import ode.controleProjeto.cgt.AplCadastrarProjeto;
import ode.controleProjeto.cih.PainelSelecionarProjeto;
import ode.nucleo.cci.CtrlBase;
import ode.nucleo.crud.cih.JanelaSimples;
import ode.nucleo.util.NucleoContexto;
import ode.principal.cih.WindowPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zul.Window;

@Controller(CtrlSelecionarProjeto.NOME)
public class CtrlSelecionarProjeto extends CtrlBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String NOME = "CtrlSelecionarProjeto";
	
	private WindowPrincipal windowMenu;
	
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

	@Override
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
	
	public void atualizarWindowMenu(){
		WindowPrincipal windowMenu = NucleoContexto.recuperarJanelaPrincipal();
//		windowMenu.atualizaBarraInformacoes();
//		windowMenu.atualizarPermissoesAcesso();
	}

	public void finalizar(){
		jan.detach();
		this.detach();
	}

}
