package ode.conhecimento.processo.Cci;

import org.zkoss.zkplus.spring.SpringUtil;

import nucleo.comuns.aplicacao.NucleoAplCadastroBase;
import nucleo.comuns.crud.controlador.CtrlCRUD;
import nucleo.comuns.crud.visao.FormularioDadosCRUD;
import nucleo.comuns.crud.visao.PainelCRUD;
import ode.conhecimento.processo.Cdp.KFerramentaSoftware;
import ode.conhecimento.processo.Cgt.AplCadastrarKFerramentaSoftware;
import ode.conhecimento.processo.Cih.FormDadosFerramentaSoftware;
import ode.conhecimento.processo.Cih.PainelCrudFerramentaSoftware;

public class CtrlFerramentaSoftwareCRUD extends CtrlCRUD<KFerramentaSoftware> {
	@Override
	public void iniciar() {
		super.iniciar();
		alturaJanDados = "300px";
		larguraJandados = "400px";
		
	}

	//lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public NucleoAplCadastroBase definirNucleoAplCadastroBase() {
		return (AplCadastrarKFerramentaSoftware) SpringUtil
				.getBean("aplCadastrarKFerramentaSoftware");
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudFerramentaSoftware();
		
	}


	@Override
	public KFerramentaSoftware factoryObjetoDados() {
		return new KFerramentaSoftware();
	}

	@Override
	public FormDadosFerramentaSoftware definirFormularioCadastro() {
		return new FormDadosFerramentaSoftware();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Domínio de Conhecimento";
	}
	
	@Override
	public String definirTituloJanelaPrincipal() {
		return "Cadastro de Domínio de Conhecimento";
	}
}
