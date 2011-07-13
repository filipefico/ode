package ode.controleProjeto.cci;

import nucleo.global.visao.WindowMenu;
import ode.controleProjeto.cgt.AplCadastrarProjeto;
import ode.controleProjeto.cih.PainelSelecionarProjeto;
import ode.nucleo.cci.CtrlBase;
import ode.nucleo.crud.cih.JanelaSimples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller(CtrlSelecionarProjeto.NOME)
public class CtrlSelecionarProjeto extends CtrlBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String NOME = "CtrlSelecionarProjeto";
	
	private WindowMenu windowMenu;
	
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

	public void iniciar(WindowMenu windowMenu) {
		
		this.windowMenu = windowMenu;
		
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
		windowMenu.atualizaBarraInformacoes();
		windowMenu.atualizarPermissoesAcesso();
	}

	public void finalizar(){
		jan.detach();
		this.detach();
	}

}
